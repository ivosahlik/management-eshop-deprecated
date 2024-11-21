package com.eshopweb.controller.rest;

import com.eshopweb.domain.CartItem;
import com.eshopweb.domain.DBFile;
import com.eshopweb.domain.Order;
import com.eshopweb.domain.User;
import com.eshopweb.exceptions.OrderNotFoundException;
import com.eshopweb.service.CartItemService;
import com.eshopweb.service.DBFileService;
import com.eshopweb.service.OrderService;
import com.eshopweb.service.TariffZoneService;
import com.eshopweb.service.UserService;
import com.eshopweb.service.impl.PdfService;
import com.eshopweb.utility.MailConstructor;
import com.eshopweb.utility.RecalculateCart;
import com.lowagie.text.DocumentException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.eshopweb.utility.Constants.LESS_THEN_CRATE_LTC;

/**
 * Intellij Idea
 * Created by ivosahlik on 2019-01-19
 */
@Slf4j
@RestController
public class PdfController {

    @Autowired
    private DBFileService dbFileService;

    @Autowired
    PdfService pdfService;

    @Autowired
    private UserService userService;

    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private TariffZoneService tariffZoneService;

    @Autowired
    private MailConstructor mailConstructor;


    @GetMapping({
            "category/{categorySeo}/subCategory/{subCategorySeo}/type/{typeSeo}/product/{productSeo}/pdf/{pdfSeo:.+}",
            "category/{categorySeo}/product/{productSeo}/pdf/{pdfSeo:.+}"
    })
    public ResponseEntity<byte[]> getPdf(@PathVariable("pdfSeo") String pdfSeo) {
        String[] firstPart = StringUtils.split(pdfSeo, ".");
        String fileName = String.valueOf(firstPart[0]);
        DBFile dbFile = dbFileService.findOneByName(fileName);
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("Content-Type", dbFile.getFileType() + "; charset=UTF-8");
        return new ResponseEntity<>(dbFile.getData(), headers, HttpStatus.OK);
    }


    @GetMapping("/admin/generate-pdf-invoice/{id}")
    public ResponseEntity<byte[]> createPdf(@PathVariable("id") String orderId,
                                            Principal principal) throws DocumentException, IOException, URISyntaxException {

        User user = userService.findByUsername(principal.getName());
        Order order = orderService.findOne(Long.parseLong(orderId));
        List<CartItem> cartItemList = cartItemService.findByOrder(order);

        if (!order.getUser().getId().equals(user.getId())) {
            throw new OrderNotFoundException("Order Id not found. For user: " + user.getUsername() + ".");
        } else {
            Map<String, Object> data = new HashMap<>();
            data.put("orderId", orderId);
            data.put("cartItemList", cartItemList);
            data.put("order", order);
            data.put("user", user);
            data.put("image", mailConstructor.getFile());

            if (RecalculateCart.getSumSubTotalLtcLw(cartItemList).compareTo(LESS_THEN_CRATE_LTC) < 0 && !RecalculateCart.getSumSubTotalLtcLw(cartItemList).equals(BigDecimal.ZERO)) {
                data.put("lessThenCrateLtc", true);
            }

            data.put("orderShippingAndOrderGrandWidthTotal", tariffZoneService.getTariffZoneAndGrandWidthTotal(order));

            String processedHtml = pdfService.createPdf("orderPdfTemplate", data);

            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(pdfService
                            .render(processedHtml)
                            .toByteArray());
        }
    }
}
