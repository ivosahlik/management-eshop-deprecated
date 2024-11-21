package com.eshopweb.utility;

import com.eshopweb.domain.CartItem;
import com.eshopweb.domain.Order;
import com.eshopweb.domain.RequestPricing;
import com.eshopweb.domain.User;
import com.eshopweb.service.CartItemService;
import com.eshopweb.service.TariffZoneService;
import com.eshopweb.service.UserService;
import com.eshopweb.service.impl.DataSlowDataAccessor;
import com.google.common.base.Strings;
import com.lowagie.text.DocumentException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static com.eshopweb.utility.Constants.LESS_THEN_CRATE_LTC;

/**
 * Intellij Idea
 * Created by ivosahlik on 05/12/2018
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class MailConstructor {

    @Value("${support.email}")
    private String supportEmail;

    private final UserService userService;
    private final TemplateEngine templateEngine;
    private final CartItemService cartItemService;
    private final TariffZoneService tariffZoneService;

    /**
     * Method reset client password, client get new token, client forget password
     *
     * @param user
     * @param contextPath
     * @return
     */
    public MimeMessagePreparator constructResetTokenEmail(User user,
                                                          String contextPath) {
        String token = UUID.randomUUID().toString();
        Context context = getContext(user, contextPath, token);
        String text = templateEngine.process("resetTokenEmailTemplate", context);

        return mimeMessage -> {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
            constructEmailBodyHelper(text, helper, user.getEmail(), "Restart password – automatic answer", null);
        };
    }

    private Context getContext(User user,
                               String contextPath,
                               String token) {
        userService.createPasswordResetTokenForUser(user, token);

        Context context = new Context();
        context.setVariable("url", contextPath + "/newUser?token=" + token);
        return context;
    }

    /**
     * Method create new client, registration new client, client has to confirm link in mail and then he is login
     *
     * @param user
     * @param contextPath
     * @return
     */
    public MimeMessagePreparator constructRegistrationNewUserEmail(User user,
                                                                   String contextPath) {
        Context context = getContext(user, contextPath);
        String text = templateEngine.process("newUserRegistrationEmailTemplate", context);

        return mimeMessage -> {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
            constructEmailBodyHelper(text, helper, user.getEmail(), "Registration – automatic answer", null);
        };
    }

    private void confirmEmailHelper(String text,
                                    MimeMessageHelper helper,
                                    String email,
                                    String s,
                                    String cc,
                                    Context context) throws MessagingException, IOException, URISyntaxException {

        helper.setTo(email);
        helper.setSubject(s);
        String inlineImage = "<img src=\"cid:logo\" style=\"height: 40px;\"></img><br/>";
        helper.setText(text + inlineImage, true);
        helper.addInline("logo", getResourceDirectory());

        context.setVariable("image", getFile());

        final Order order = (Order) context.getVariables().get("order");
        List<CartItem> cartItemList = cartItemService.findByOrder(order);
        if (RecalculateCart.getSumSubTotalLtcLw(cartItemList).compareTo(LESS_THEN_CRATE_LTC) < 0 && !RecalculateCart.getSumSubTotalLtcLw(cartItemList).equals(BigDecimal.ZERO)) {
            context.setVariable("lessThenCrateLtc", true);
        }
        context.setVariable("orderShippingAndOrderGrandWidthTotal", tariffZoneService.getTariffZoneAndGrandWidthTotal(order));

        String orderPdfTemplateString = templateEngine.process("orderPdfTemplate", context);
        final byte[] generatePdfFromHtml = generatePdfFromHtml(orderPdfTemplateString);
        helper.addAttachment("DCS_OC_" + order.getId() + ".pdf", new ByteArrayResource(generatePdfFromHtml));

        if (!Strings.isNullOrEmpty(cc)) {
            helper.addCc(cc);
        }
        helper.setFrom(new InternetAddress(supportEmail));
    }

    private void constructEmailBodyHelper(String text,
                                          MimeMessageHelper helper,
                                          String email,
                                          String s,
                                          String cc) throws MessagingException {
        helper.setTo(email);
        helper.setSubject(s);
        String inlineImage = "<img src=\"cid:logo\" style=\"height: 40px;\"></img><br/>";
        helper.setText(text + inlineImage, true);
        helper.addInline("logo", getResourceDirectory());
        if (!Strings.isNullOrEmpty(cc)) {
            helper.addCc(cc);
        }
        helper.setFrom(new InternetAddress(supportEmail));
    }

    public File getFile() throws URISyntaxException {
        URI uri = Objects.requireNonNull(getClass().getResource("/static/images/")).toURI();
        return new File(uri + DataSlowDataAccessor.getApp() + "logo.png");
    }

    private Context getContext(User user,
                               String contextPath) {
        String token = UUID.randomUUID().toString();
        return getContext(user, contextPath, token);
    }

    private byte[] generatePdfFromHtml(String html) throws IOException {
        try(ByteArrayOutputStream os = new ByteArrayOutputStream()) {
            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocumentFromString(html);
            renderer.layout();
            renderer.createPDF(os);
            return os.toByteArray();
        } catch (IOException | DocumentException e) {
            log.error("Generated pdf from html failed {}", e.getMessage());
            throw new IOException("Generated pdf from html failed");
        }
    }

    /**
     * Method send email confirmation order
     *
     * @param user
     * @param order
     * @return
     */
    public MimeMessagePreparator constructOrderConfirmationEmail(User user,
                                                                 Order order) {
        List<CartItem> cartItemList = cartItemService.findByOrder(order);
        Context context = new Context();
        context.setVariable("order", order);
        context.setVariable("user", user);
        context.setVariable("cartItemList", order.getCartItemList());
        context.setVariable("orderShippingAndOrderGrandWidthTotal", tariffZoneService.getTariffZoneAndGrandWidthTotal(order));
        if (RecalculateCart.getSumSubTotalLtcLw(cartItemList).compareTo(LESS_THEN_CRATE_LTC) < 0 && !RecalculateCart.getSumSubTotalLtcLw(cartItemList).equals(BigDecimal.ZERO)) {
            context.setVariable("lessThenCrateLtc", true);
        }

        String text = templateEngine.process("orderConfirmationEmailTemplate", context);

        return mimeMessage -> {
            MimeMessageHelper helper = new MimeMessageHelper(
                    mimeMessage,
                    MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name()
            );
            confirmEmailHelper(text, helper, user.getEmail(), "Order Confirmation - " + order.getId(), supportEmail, context);
        };
    }

    /**
     * Method do request pricing for client
     *
     * @param requestPricing
     * @return
     */
    public MimeMessagePreparator constructRequestPricing(RequestPricing requestPricing) {
        Context context = new Context();
        String text = templateEngine.process("requestForQuotationEmailTemplate", context);

        return mimeMessage -> {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
            constructEmailBodyHelper(text, helper, requestPricing.getEmail(), "Request for quotation – automatic answer", null);
            helper.setBcc(supportEmail);
            helper.setCc(supportEmail);
            helper.setValidateAddresses(true);
        };
    }

    public ClassPathResource getResourceDirectory() {
        return new ClassPathResource("static/images/" + DataSlowDataAccessor.getApp() + "logo.png");
    }
}
