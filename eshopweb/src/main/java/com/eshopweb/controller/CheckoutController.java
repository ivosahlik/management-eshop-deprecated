package com.eshopweb.controller;

import com.eshopweb.domain.BillingAddress;
import com.eshopweb.domain.CartItem;
import com.eshopweb.domain.Countries;
import com.eshopweb.domain.Order;
import com.eshopweb.domain.Payment;
import com.eshopweb.domain.ShippingAddress;
import com.eshopweb.domain.ShoppingCart;
import com.eshopweb.domain.User;
import com.eshopweb.domain.UserBilling;
import com.eshopweb.domain.UserPayment;
import com.eshopweb.domain.UserShipping;
import com.eshopweb.service.BillingAddressService;
import com.eshopweb.service.CartItemService;
import com.eshopweb.service.CheckoutService;
import com.eshopweb.service.CountriesService;
import com.eshopweb.service.OrderService;
import com.eshopweb.service.PaymentService;
import com.eshopweb.service.ShippingAddressService;
import com.eshopweb.service.ShoppingCartService;
import com.eshopweb.service.UserPaymentService;
import com.eshopweb.service.UserService;
import com.eshopweb.service.UserShippingService;
import com.eshopweb.utility.MailConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

/**
 * Intellij Idea
 * Created by ivosahlik on 05/12/2018
 */
@Slf4j
@Controller
public class CheckoutController {

	public static final String COUNTRIES_LIST = "countriesList";
	public static final String BAD_REQUEST_PAGE = "badRequestPage";
	public static final String USER_SHIPPING_LIST = "userShippingList";
	public static final String USER_PAYMENT_LIST = "userPaymentList";
	public static final String EMPTY_PAYMENT_LIST = "emptyPaymentList";
	public static final String EMPTY_SHIPPING_LIST = "emptyShippingList";
	public static final String CHECKOUT = "checkout";
	public static final String SHIPPING_ADDRESS = "shippingAddress";
	public static final String PAYMENT = "payment";
	public static final String BILLING_ADDRESS = "billingAddress";
	public static final String CART_ITEM_LIST = "cartItemList";
	public static final String SHOPPING_CART = "shoppingCart";

	@Value("${percent.include.vat}")
	private BigDecimal percentIncludeVat;

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private MailConstructor mailConstructor;

	@Autowired
	private UserService userService;

	@Autowired
	private CartItemService cartItemService;

	@Autowired
	private ShoppingCartService shoppingCartService;

	@Autowired
	private ShippingAddressService shippingAddressService;

	@Autowired
	private BillingAddressService billingAddressService;

	@Autowired
	private PaymentService paymentService;

	@Autowired
	private UserShippingService userShippingService;

	@Autowired
	private UserPaymentService userPaymentService;

	@Autowired
	private OrderService orderService;

	@Autowired
	private CountriesService countriesService;

	@Autowired
	private CheckoutService checkoutService;

	private ShippingAddress shippingAddress;
	private BillingAddress billingAddress;
	private Payment payment;


	@GetMapping("/checkout")
	public String shopCartCheckout(@RequestParam("id") Long cartId,
						   @RequestParam(value = "missingRequiredField", required = false) boolean missingRequiredField,
						   Model model,
						   Principal principal) {
		User user;
		shippingAddress = new ShippingAddress();
		billingAddress = new BillingAddress();
		payment = new Payment();

		if (principal != null) {
			user = userService.findByUsername(principal.getName());
		} else {
			String sessionId = RequestContextHolder.currentRequestAttributes().getSessionId();
			user = userService.findBySessionId(sessionId);
		}

		if (!cartId.equals(user.getShoppingCart().getId())) {
			return BAD_REQUEST_PAGE;
		}

		List<CartItem> cartItemList = cartItemService.findByShoppingCart(user.getShoppingCart());
		user.getShoppingCart().setCartItemList(cartItemList);

		if (cartItemList.isEmpty()) {
			model.addAttribute("emptyCart", true);
			return "forward:/shoppingCart/cart";
		}

		for (CartItem cartItem : cartItemList) {
			if (cartItem.getProduct().getInStockNumber() < cartItem.getQty()) {
				model.addAttribute("notEnoughStock", true);
				return "forward:/shoppingCart/cart";
			}
		}

		List<UserShipping> userShippingList = user.getUserShippingList();
		List<UserPayment> userPaymentList = user.getUserPaymentList();

		model.addAttribute(USER_SHIPPING_LIST, userShippingList);
		model.addAttribute(USER_PAYMENT_LIST, userPaymentList);
		model.addAttribute(EMPTY_PAYMENT_LIST, false);
		model.addAttribute(EMPTY_SHIPPING_LIST, false);

		if (userPaymentList.isEmpty()) {
			model.addAttribute(EMPTY_PAYMENT_LIST, true);
		}

		if (userShippingList.isEmpty()) {
			model.addAttribute(EMPTY_SHIPPING_LIST, true);
		}

		for (UserShipping userShipping : userShippingList) {
			if (userShipping.isUserShippingDefault()) {
				shippingAddressService.setByUserShipping(userShipping, shippingAddress);
			}
		}

		for (UserPayment userPayment : userPaymentList) {
			if (userPayment.isDefaultPayment()) {
				paymentService.setByUserPayment(userPayment, payment);
				billingAddressService.setByUserBilling(userPayment.getUserBilling(), billingAddress);
			}
		}

		checkoutService.calculateTotalOrderWithShippingAndVat(model, user.getShoppingCart(), shippingAddress.getShippingAddressVat(),
				shippingAddress.getShippingAddressState(), percentIncludeVat);

		model.addAttribute("principalUsername", user.getUsername());
		model.addAttribute(SHIPPING_ADDRESS, shippingAddress);
		model.addAttribute(PAYMENT, payment);
		model.addAttribute(BILLING_ADDRESS, billingAddress);
		model.addAttribute(CART_ITEM_LIST, cartItemList);
		model.addAttribute(SHOPPING_CART, user.getShoppingCart());
		model.addAttribute(COUNTRIES_LIST, getCountryList());
		model.addAttribute("classActiveShipping", true);
		model.addAttribute("cartQty", cartItemList.stream().mapToInt(CartItem::getQty).sum());

		if (missingRequiredField) {
			model.addAttribute("missingRequiredField", true);
		}

		return CHECKOUT;
	}

	@PostMapping("/checkout")
	public String checkoutPost(
	        @ModelAttribute(SHIPPING_ADDRESS) ShippingAddress shippingAddress,
			@ModelAttribute(BILLING_ADDRESS) BillingAddress billingAddress,
            @ModelAttribute(PAYMENT) Payment payment,
			@ModelAttribute("billingSameAsShipping") String billingSameAsShipping,
			@ModelAttribute("shippingMethod") String shippingMethod,
			Principal principal,
			HttpServletRequest request,
			HttpSession session,
			Model model) {

		ShoppingCart shoppingCart;
		User user;
		user = getUser(principal);
		shoppingCart = user.getShoppingCart();

		shoppingCart.setGrandTotal(shoppingCart.getSumTotalOrderWithShipping());
		List<CartItem> cartItemList = cartItemService.findByShoppingCart(shoppingCart);
		if (cartItemList.isEmpty()) {
			log.info("Count items in cart is O!");
			return "redirect:/";
		}
		if (Boolean.parseBoolean(billingSameAsShipping)) {
			billingAddress.setBillingAddressName(shippingAddress.getShippingAddressName());
			billingAddress.setBillingAddressStreet1(shippingAddress.getShippingAddressStreet1());
			billingAddress.setBillingAddressStreet2(shippingAddress.getShippingAddressStreet2());
			billingAddress.setBillingAddressCity(shippingAddress.getShippingAddressCity());
			billingAddress.setBillingAddressState(shippingAddress.getShippingAddressState());
			billingAddress.setBillingAddressCountry(shippingAddress.getShippingAddressCountry());
			billingAddress.setBillingAddressZipcode(shippingAddress.getShippingAddressZipcode());
			billingAddress.setBillingAddressVat(shippingAddress.getShippingAddressVat());
			billingAddress.setBillingAddressPhone(shippingAddress.getShippingAddressPhone());
			billingAddress.setBillingAddressEmail(shippingAddress.getShippingAddressEmail());
		}
		if (shippingAddress.getShippingAddressStreet1().isEmpty()
				|| shippingAddress.getShippingAddressCity().isEmpty()
				|| shippingAddress.getShippingAddressState().isEmpty()
				|| shippingAddress.getShippingAddressName().isEmpty()
				|| shippingAddress.getShippingAddressZipcode().isEmpty()
				|| billingAddress.getBillingAddressCity().isEmpty()
				|| billingAddress.getBillingAddressState().isEmpty()
				|| billingAddress.getBillingAddressName().isEmpty()
				|| billingAddress.getBillingAddressZipcode().isEmpty()
				|| billingAddress.getBillingAddressPhone().isEmpty()
				|| billingAddress.getBillingAddressEmail().isEmpty()
				) {
            return "redirect:/checkout?id=" + shoppingCart.getId() + "&missingRequiredField=true";
        }

		Order order = orderService.createOrder(shoppingCart, shippingAddress, billingAddress, payment, shippingMethod, user);

		String shippingAddressEmail = shippingAddress.getShippingAddressEmail();
		String shippingAddressPhone = shippingAddress.getShippingAddressPhone();

		user.setEmail(shippingAddressEmail);
		user.setPhone(shippingAddressPhone);

		mailSender.send(mailConstructor.constructOrderConfirmationEmail(user, order));

		shoppingCartService.clearShoppingCart(shoppingCart);
		LocalDate today = LocalDate.now();
		LocalDate estimatedDeliveryDate = today.plusDays(3);

		if (shippingMethod.equals("groundShipping")) {
			estimatedDeliveryDate = today.plusDays(5);
		}

		model.addAttribute("estimatedDeliveryDate", estimatedDeliveryDate);
		model.addAttribute(CART_ITEM_LIST, cartItemList);

		invalidateSession(session);
		getNewSession(request);

		return "orderSubmittedPage";
	}

	private User getUser(Principal principal) {
		User user;
		if (principal != null) {
			user = userService.findByUsername(principal.getName());
		} else {
			String sessionId = RequestContextHolder.currentRequestAttributes().getSessionId();
			user = userService.findBySessionId(sessionId);
		}
		return user;
	}

	private static void getNewSession(HttpServletRequest request) {
		request.getSession().getId();
	}

	private static void invalidateSession(HttpSession session) {
		session.invalidate();
	}

	@RequestMapping("/setShippingAddress")
	public String setShippingAddress(@RequestParam("userShippingId") Long userShippingId,
									 Principal principal,
									 Model model) {

		shippingAddress = new ShippingAddress();
		billingAddress = new BillingAddress();
		payment = new Payment();

		User user = userService.findByUsername(principal.getName());
		UserShipping userShipping = userShippingService.findById(userShippingId);

		if (!userShipping.getUser().getId().equals(user.getId())) {
			return BAD_REQUEST_PAGE;
		} else {
			shippingAddressService.setByUserShipping(userShipping, shippingAddress);

			List<CartItem> cartItemList = cartItemService.findByShoppingCart(user.getShoppingCart());
			List<UserShipping> userShippingList = user.getUserShippingList();
			List<UserPayment> userPaymentList = user.getUserPaymentList();

			checkoutService.calculateTotalOrderWithShippingAndVat(model, user.getShoppingCart(), shippingAddress.getShippingAddressVat(),
					shippingAddress.getShippingAddressState(), percentIncludeVat);

			model.addAttribute(SHIPPING_ADDRESS, shippingAddress);
			model.addAttribute(PAYMENT, payment);
			model.addAttribute(BILLING_ADDRESS, billingAddress);
			model.addAttribute(CART_ITEM_LIST, cartItemList);
			model.addAttribute(SHOPPING_CART, user.getShoppingCart());
			model.addAttribute(COUNTRIES_LIST, getCountryList());
			model.addAttribute(USER_SHIPPING_LIST, userShippingList);
			model.addAttribute(USER_PAYMENT_LIST, userPaymentList);
			model.addAttribute(SHIPPING_ADDRESS, shippingAddress);
			model.addAttribute("classActiveShipping", true);
			model.addAttribute(EMPTY_PAYMENT_LIST, userPaymentList.isEmpty());
			model.addAttribute(EMPTY_SHIPPING_LIST, false);

			return CHECKOUT;
		}
	}

	@RequestMapping("/setPaymentMethod")
	public String setPaymentMethod(@RequestParam("userPaymentId") Long userPaymentId,
								   Principal principal,
								   Model model) {

		shippingAddress = new ShippingAddress();
		billingAddress = new BillingAddress();
		payment = new Payment();

		User user = userService.findByUsername(principal.getName());
		UserPayment userPayment = userPaymentService.findById(userPaymentId);
		UserBilling userBilling = userPayment.getUserBilling();

		if (!userPayment.getUser().getId().equals(user.getId())) {
			return BAD_REQUEST_PAGE;
		} else {
			paymentService.setByUserPayment(userPayment, payment);

			List<CartItem> cartItemList = cartItemService.findByShoppingCart(user.getShoppingCart());
			List<UserShipping> userShippingList = user.getUserShippingList();
			List<UserPayment> userPaymentList = user.getUserPaymentList();

			billingAddressService.setByUserBilling(userBilling, billingAddress);

			model.addAttribute(SHIPPING_ADDRESS, shippingAddress);
			model.addAttribute(PAYMENT, payment);
			model.addAttribute(BILLING_ADDRESS, billingAddress);
			model.addAttribute(CART_ITEM_LIST, cartItemList);
			model.addAttribute(SHOPPING_CART, user.getShoppingCart());
			model.addAttribute(COUNTRIES_LIST, getCountryList());
			model.addAttribute(USER_SHIPPING_LIST, userShippingList);
			model.addAttribute(USER_PAYMENT_LIST, userPaymentList);
			model.addAttribute(SHIPPING_ADDRESS, shippingAddress);
			model.addAttribute("classActivePayment", true);
			model.addAttribute(EMPTY_PAYMENT_LIST, false);
			model.addAttribute(EMPTY_SHIPPING_LIST, false);
			if (userShippingList.isEmpty()) {
				model.addAttribute(EMPTY_SHIPPING_LIST, true);
			}

			return CHECKOUT;
		}
	}

	private List<Countries> getCountryList() {
		return countriesService.getCountryList();
	}

}
