package com.eshopweb.controller;

import com.eshopweb.domain.Banners;
import com.eshopweb.domain.CartItem;
import com.eshopweb.domain.Countries;
import com.eshopweb.domain.CustomSettings;
import com.eshopweb.domain.Order;
import com.eshopweb.domain.RequestPricing;
import com.eshopweb.domain.User;
import com.eshopweb.domain.UserBilling;
import com.eshopweb.domain.UserPayment;
import com.eshopweb.domain.UserShipping;
import com.eshopweb.domain.VideoBanner;
import com.eshopweb.domain.dto.ProductDto;
import com.eshopweb.domain.security.PasswordResetToken;
import com.eshopweb.domain.security.Role;
import com.eshopweb.domain.security.UserRole;
import com.eshopweb.service.BannersService;
import com.eshopweb.service.CartItemService;
import com.eshopweb.service.CountriesService;
import com.eshopweb.service.OrderService;
import com.eshopweb.service.RequestPricingService;
import com.eshopweb.service.TariffZoneService;
import com.eshopweb.service.UserPaymentService;
import com.eshopweb.service.UserService;
import com.eshopweb.service.UserShippingService;
import com.eshopweb.service.impl.DataSlowDataAccessor;
import com.eshopweb.service.impl.RecaptchaService;
import com.eshopweb.service.impl.UserSecurityService;
import com.eshopweb.utility.MailConstructor;
import com.eshopweb.utility.RecalculateCart;
import com.eshopweb.utility.SecurityUtility;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static com.eshopweb.utility.Constants.LESS_THEN_CRATE_LTC;

/**
 * Intellij Idea
 * Created by ivosahlik on 05/12/2018
 */
@Slf4j
@Controller
public class HomeController {

	private static final String ROLE_USER = "ROLE_USER";

	public static final String PHONE_NUMBER_REGEX = "^\\+(?:[0-9] ?){6,14}[0-9]$";

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    private static final String REDIRECT = "redirect:";
	public static final String COUNTRIES_LIST = "countriesList";
	public static final String BREADCRUMB = "breadcrumb";


	@Value("${app.url.domain}")
	private String appUrlDomain;


	@Value("${spring.profiles.active}")
	private String activeProfile;

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private MailConstructor mailConstructor;

	@Autowired
	private UserService userService;

	@Autowired
	private UserSecurityService userSecurityService;

	@Autowired
	private UserPaymentService userPaymentService;

	@Autowired
	private UserShippingService userShippingService;

	@Autowired
	private CartItemService cartItemService;

	@Autowired
	private OrderService orderService;

	@Autowired
	private RequestPricingService requestPricingService;

	@Autowired
	private RecaptchaService recaptchaService;

	@Autowired
	private CountriesService countriesService;

	@Autowired
	private TariffZoneService tariffZoneService;

	@Autowired
	private DataSlowDataAccessor dataSlowDataAccessor;

	@Autowired
	private BannersService bannersService;


    @GetMapping("/")
	public String index(Model model) {

		List<ProductDto> productDtoList = dataSlowDataAccessor.getSeoListByProduct();

		model.addAttribute("banner1", getBanner1(productDtoList));
		model.addAttribute("banner2", getBanner2(productDtoList));
		model.addAttribute("banner3", getBanner3(productDtoList));
        model.addAttribute("banner4", getBanner4(productDtoList));
		model.addAttribute("videoStream", getVideoBanner());

		model.addAttribute("app", DataSlowDataAccessor.getApp().equals("dcsolutions"));

		model.addAttribute("htmlToInject", Optional.ofNullable(dataSlowDataAccessor.getOneByActiveCustomSettings())
				.map(Optional::get)
				.map(CustomSettings::getCopyrigtHtmlFragment)
				.orElse(""));

		return "index";
	}

	private Banners getBanner1(List<ProductDto> productDtoList) {
		Banners banners = bannersService.getBanner(1);
		if (banners == null) {
			return null;
		}
		List<ProductDto> list = productDtoList.stream()
				.filter(ProductDto::isBanner1)
				.collect(Collectors.toList());

		return getBanners(banners, list);
	}

	private Banners getBanner2(List<ProductDto> productDtoList) {
		Banners banners = bannersService.getBanner(2);

		if (banners == null) {
			return null;
		}
		List<ProductDto> list = productDtoList.stream()
				.filter(ProductDto::isBanner2)
				.collect(Collectors.toList());

		return getBanners(banners, list);
	}

	private Banners getBanner3(List<ProductDto> productDtoList) {
		Banners banners = bannersService.getBanner(3);
		if (banners == null) {
			return null;
		}
		List<ProductDto> list = productDtoList.stream()
				.filter(ProductDto::isBanner3)
				.collect(Collectors.toList());

		return getBanners(banners, list);
	}

	private Banners getBanner4(List<ProductDto> productDtoList) {
		Banners banners = bannersService.getBanner(4);
		if (banners == null) {
			return null;
		}
		List<ProductDto> list = productDtoList.stream()
				.filter(ProductDto::isBanner4)
				.collect(Collectors.toList());

		return getBanners(banners, list);
	}

	private Banners getBanners(Banners banners, List<ProductDto> list) {
		Banners banners1 = new Banners();
		banners1.setTitle(banners.getTitle());
		banners1.setCreated(banners.getCreated());
		banners1.setUpdated(banners.getUpdated());
		banners1.setUserCreated(banners.getUserCreated());
		banners1.setActive(banners.isActive());
		banners1.setId(banners.getId());
		banners1.setDescription(banners.getDescription());
		banners1.setBannerId(banners.getBannerId());
		banners1.setProductDtoList(list);
		return banners1;
	}

	private List<VideoBanner> getVideoBanner() {
		return dataSlowDataAccessor.getVideoBannerList().stream()
				.filter(VideoBanner::isActive)
				.collect(Collectors.toList());
	}

	@GetMapping("/testivo")
	public String testivo() {
		return "index";
	}

	@GetMapping("/login")
	public String login(Model model) {
		model.addAttribute("showLogin", true);
		return "myAccount";
	}

	@GetMapping("/hours")
	public String hours() {
		return "hours";
	}

	@GetMapping("/faq")
	public String faq() {
		return "faq";
	}

	@GetMapping("/forgetPassword")
	public String forgetPassword(Model model) {
		model.addAttribute("showForgetPassword", true);
		return "myAccount";
	}

	@PostMapping("/forgetPassword")
	public String forgetPasswordPost(
			@RequestParam(name="g-recaptcha-response") String recaptchaResponse,
	        HttpServletRequest request,
            @ModelAttribute("email") String email, Model model, RedirectAttributes redirectAttributes) {

		if (getCaptchaVerify(recaptchaResponse, request, redirectAttributes)) {
			return REDIRECT + "forgetPassword";
		}

		model.addAttribute("classActiveForgetPassword", true);

		User user = userService.findByEmail(email);

        model.addAttribute("showForgetPassword", true);

		if (Objects.isNull(user)) {
			model.addAttribute("emailNotExist", true);
			return "myAccount";
		}

		String password = SecurityUtility.randomPassword();
		String encryptedPassword = SecurityUtility.passwordEncoder().encode(password);
		user.setPassword(encryptedPassword);
		userService.save(user);
		mailSender.send(mailConstructor.constructResetTokenEmail(user, appUrlDomain));

		model.addAttribute("forgetPasswordEmailSent", "true");

		return "myAccount";
	}

	private boolean getCaptchaVerify(@RequestParam(name = "g-recaptcha-response") String recaptchaResponse,
									 HttpServletRequest request,
									 RedirectAttributes redirectAttributes) {
		String ip = request.getRemoteAddr();
		String captchaVerifyMessage = recaptchaService.verifyRecaptcha(ip, recaptchaResponse);

		if ( StringUtils.isNotEmpty(captchaVerifyMessage)) {
			Map<String, Object> response = new HashMap<>();
			response.put("message", captchaVerifyMessage);
			redirectAttributes.addFlashAttribute("captchaVerifyMessage", captchaVerifyMessage);
			return true;
		}
		return false;
	}

	@GetMapping("/myProfile")
	public String myProfile(@RequestParam(value = "order", required = false) boolean order, Model model, Principal principal) {

		User user = userService.findByUsername(principal.getName());
		model.addAttribute("user", user);
		model.addAttribute("userShippingList", user.getUserShippingList());
		model.addAttribute("orderList", user.getOrderList());

		UserShipping userShipping = new UserShipping();
		model.addAttribute("userShipping", userShipping);

		model.addAttribute("listOfCreditCards", true);
		model.addAttribute("listOfShippingAddresses", true);

		model.addAttribute(COUNTRIES_LIST, getCountryList());

		if (order) {
			model.addAttribute("classActiveEdit", false);
			model.addAttribute("classActiveShipping", false);
			model.addAttribute("classActiveOrders", true);
		} else {
			model.addAttribute("classActiveEdit", true);
		}

		return "myProfile";
	}

	@GetMapping("/listOfCreditCards")
	public String listOfCreditCards(Model model, Principal principal) {
		User user = userService.findByUsername(principal.getName());

		model.addAttribute("uaddNewShippingAddressser", user);
		model.addAttribute("userPaymentList", user.getUserPaymentList());
		model.addAttribute("userShippingList", user.getUserShippingList());
		model.addAttribute("orderList", user.getOrderList());

		model.addAttribute("listOfCreditCards", true);
		model.addAttribute("classActiveBilling", true);
		model.addAttribute("listOfShippingAddresses", true);

		return "myProfile";
	}

	@GetMapping("/listOfShippingAddresses")
	public String listOfShippingAddresses(Model model, Principal principal) {
		User user = userService.findByUsername(principal.getName());

		model.addAttribute("user", user);
		model.addAttribute("userPaymentList", user.getUserPaymentList());
		model.addAttribute("userShippingList", user.getUserShippingList());
		model.addAttribute("orderList", user.getOrderList());

		model.addAttribute("classActiveShipping", true);
		model.addAttribute("listOfShippingAddresses", true);

		return "myProfile";
	}

	@GetMapping("/addNewCreditCard")
	public String addNewCreditCard(Model model, Principal principal){
		User user = userService.findByUsername(principal.getName());

		model.addAttribute("user", user);
		model.addAttribute("addNewCreditCard", true);
		model.addAttribute("classActiveBilling", true);
		model.addAttribute("listOfShippingAddresses", true);

		UserBilling userBilling = new UserBilling();
		UserPayment userPayment = new UserPayment();


		model.addAttribute("userBilling", userBilling);
		model.addAttribute("userPayment", userPayment);

		model.addAttribute(COUNTRIES_LIST, getCountryList());

		model.addAttribute("userPaymentList", user.getUserPaymentList());
		model.addAttribute("userShippingList", user.getUserShippingList());
		model.addAttribute("orderList", user.getOrderList());

		return "myProfile";
	}

	@GetMapping("/addNewShippingAddress")
	public String addNewShippingAddress(Model model, Principal principal){
		User user = userService.findByUsername(principal.getName());
		model.addAttribute("user", user);

		model.addAttribute("addNewShippingAddress", true);
		model.addAttribute("classActiveShipping", true);

		UserShipping userShipping = new UserShipping();

		model.addAttribute("userShipping", userShipping);

		model.addAttribute(COUNTRIES_LIST, getCountryList());

		model.addAttribute("userShippingList", user.getUserShippingList());
		model.addAttribute("orderList", user.getOrderList());

		return "myProfile";
	}

	@PostMapping("/addNewCreditCard")
	public String addNewCreditCard(@ModelAttribute("userPayment") UserPayment userPayment,
								   @ModelAttribute("userBilling") UserBilling userBilling, Principal principal, Model model){
		User user = userService.findByUsername(principal.getName());
		userService.updateUserBilling(userBilling, userPayment, user);

		model.addAttribute("user", user);
		model.addAttribute("userPaymentList", user.getUserPaymentList());
		model.addAttribute("userShippingList", user.getUserShippingList());
		model.addAttribute("listOfCreditCards", true);
		model.addAttribute("classActiveBilling", true);
		model.addAttribute("listOfShippingAddresses", true);
		model.addAttribute("orderList", user.getOrderList());

		return "myProfile";
	}

	@PostMapping("/addNewShippingAddress")
	public String addNewShippingAddressPost(@ModelAttribute("userShipping") UserShipping userShipping,
											Principal principal,
											Model model, RedirectAttributes redirectAttributes){
		User user = userService.findByUsername(principal.getName());
		if (!phoneNumberValidation(userShipping.getUserShippingPhone())) {
			redirectAttributes.addFlashAttribute("phoneNumberValidation", true);
			return "redirect:/addNewShippingAddress";
		}
		userService.updateUserShipping(userShipping, user);

		model.addAttribute("user", user);
		model.addAttribute("userPaymentList", user.getUserPaymentList());
		model.addAttribute("userShippingList", user.getUserShippingList());
		model.addAttribute("listOfShippingAddresses", true);
		model.addAttribute("classActiveShipping", true);
		model.addAttribute("listOfCreditCards", true);
		model.addAttribute("orderList", user.getOrderList());

		return "myProfile";
	}

	private boolean phoneNumberValidation(String phoneNumber) {
		return Pattern.compile(PHONE_NUMBER_REGEX).matcher(phoneNumber).matches();
	}


	@GetMapping("/updateCreditCard")
	public String updateCreditCard(@ModelAttribute("id") Long creditCardId, Principal principal, Model model) {
		User user = userService.findByUsername(principal.getName());
		UserPayment userPayment = userPaymentService.findById(creditCardId);

		if(!user.getId().equals(userPayment.getUser().getId())) {
			return "badRequestPage";
		} else {
			model.addAttribute("user", user);
			UserBilling userBilling = userPayment.getUserBilling();
			model.addAttribute("userPayment", userPayment);
			model.addAttribute("userBilling", userBilling);

			model.addAttribute(COUNTRIES_LIST, getCountryList());

			model.addAttribute("addNewCreditCard", true);
			model.addAttribute("classActiveBilling", true);
			model.addAttribute("listOfShippingAddresses", true);

			model.addAttribute("userPaymentList", user.getUserPaymentList());
			model.addAttribute("userShippingList", user.getUserShippingList());
			model.addAttribute("orderList", user.getOrderList());

			return "myProfile";
		}
	}

	@GetMapping("/updateUserShipping")
	public String updateUserShipping(@ModelAttribute("id") Long shippingAddressId, Principal principal, Model model) {
		User user = userService.findByUsername(principal.getName());
		UserShipping userShipping = userShippingService.findById(shippingAddressId);

		if(!user.getId().equals(userShipping.getUser().getId())) {
			return "badRequestPage";
		} else {
			model.addAttribute("user", user);

			model.addAttribute("userShipping", userShipping);

			model.addAttribute(COUNTRIES_LIST, getCountryList());

			model.addAttribute("addNewShippingAddress", true);
			model.addAttribute("classActiveShipping", true);
			model.addAttribute("listOfCreditCards", true);

			model.addAttribute("userPaymentList", user.getUserPaymentList());
			model.addAttribute("userShippingList", user.getUserShippingList());
			model.addAttribute("orderList", user.getOrderList());

			return "myProfile";
		}
	}

	@PostMapping(value="/setDefaultPayment")
	public String setDefaultPayment(@ModelAttribute("defaultUserPaymentId") Long defaultPaymentId, Principal principal, Model model) {
		User user = userService.findByUsername(principal.getName());
		userService.setUserDefaultPayment(defaultPaymentId, user);

		model.addAttribute("user", user);
		return userModel(model, user);
	}

	@PostMapping(value="/setDefaultShippingAddress")
	public String setDefaultShippingAddress(@ModelAttribute("defaultShippingAddressId") Long defaultShippingId, Principal principal, Model model) {
		User user = userService.findByUsername(principal.getName());
		userService.setUserDefaultShipping(defaultShippingId, user);

		model.addAttribute("user", user);
		model.addAttribute("listOfCreditCards", true);
		model.addAttribute("classActiveShipping", true);
		model.addAttribute("listOfShippingAddresses", true);

		model.addAttribute("userPaymentList", user.getUserPaymentList());
		model.addAttribute("userShippingList", user.getUserShippingList());
		model.addAttribute("orderList", user.getOrderList());

		return "myProfile";
	}

	@GetMapping("/removeCreditCard")
	public String removeCreditCard(@ModelAttribute("id") Long creditCardId, Principal principal, Model model){
		User user = userService.findByUsername(principal.getName());
		UserPayment userPayment = userPaymentService.findById(creditCardId);

		if(!user.getId().equals(userPayment.getUser().getId())) {
			return "badRequestPage";
		} else {
			model.addAttribute("user", user);
			userPaymentService.removeById(creditCardId);

			return userModel(model, user);
		}
	}

	private String userModel(Model model, User user) {
		model.addAttribute("listOfCreditCards", true);
		model.addAttribute("classActiveBilling", true);
		model.addAttribute("listOfShippingAddresses", true);

		model.addAttribute("userPaymentList", user.getUserPaymentList());
		model.addAttribute("userShippingList", user.getUserShippingList());
		model.addAttribute("orderList", user.getOrderList());

		return "myProfile";
	}

	@GetMapping("/removeUserShipping")
	public String removeUserShipping(@ModelAttribute("id") Long userShippingId, Principal principal, Model model){
		User user = userService.findByUsername(principal.getName());
		UserShipping userShipping = userShippingService.findById(userShippingId);

		if(!user.getId().equals(userShipping.getUser().getId())) {
			return "badRequestPage";
		} else {
			model.addAttribute("user", user);

			userShippingService.removeById(userShippingId);

			model.addAttribute("listOfShippingAddresses", true);
			model.addAttribute("classActiveShipping", true);

			model.addAttribute("userPaymentList", user.getUserPaymentList());
			model.addAttribute("userShippingList", user.getUserShippingList());
			model.addAttribute("orderList", user.getOrderList());

			return "myProfile";
		}
	}

	@GetMapping("/newUserAccount")
	public String newUser(Model model) {
		model.addAttribute("showNewUserAccount", true);
		return "myAccount";
	}

    public static boolean validate(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(emailStr);
        return matcher.find();
    }

	@PostMapping(value="/newUser")
	public String newUserPost(
			HttpServletRequest request,
			@ModelAttribute("email") String userEmail,
			@ModelAttribute("username") String username,
			@RequestParam(name="g-recaptcha-response") String recaptchaResponse, RedirectAttributes redirectAttributes,
			Model model) throws Exception{

		if (getCaptchaVerify(recaptchaResponse, request, redirectAttributes)) {
			return REDIRECT + "newUserAccount";
		}

		if (!validate(userEmail)) {
            redirectAttributes.addFlashAttribute("validEmailAddressRegex", true);
		    return REDIRECT + "newUserAccount";
        }

		model.addAttribute("email", userEmail);
		model.addAttribute("username", username);
		model.addAttribute("showNewUserAccount", true);

		if (Objects.nonNull(userService.findByUsername(username))) {
			model.addAttribute("usernameExists", true);
			return "myAccount";
		}

		if (Objects.nonNull(userService.findByEmail(userEmail))) {
			model.addAttribute("emailExists", true);
			return "myAccount";
		}

		User user = new User();
		user.setUsername(username);
		user.setEmail(userEmail);

		String password = SecurityUtility.randomPassword();

		String encryptedPassword = SecurityUtility.passwordEncoder().encode(password);
		user.setPassword(encryptedPassword);

		Role role = new Role();
		role.setRoleId(1);
		role.setName(ROLE_USER);
		Set<UserRole> userRoles = new HashSet<>();
		userRoles.add(new UserRole(user, role));
		userService.createUser(user, userRoles);
		mailSender.send(mailConstructor.constructRegistrationNewUserEmail(user, appUrlDomain));

		model.addAttribute("emailSent", "true");
		model.addAttribute("orderList", user.getOrderList());

		return "myAccount";
	}

	@GetMapping("/newUser")
	public String newUser(Locale locale, @RequestParam("token") String token, Model model,
						  RedirectAttributes redirectAttributes) {
		PasswordResetToken passToken = userService.getPasswordResetToken(token);

		if (Objects.isNull(passToken)) {
			redirectAttributes.addFlashAttribute("message", "Invalid token!");
			return REDIRECT + "newUserAccount";
		}

		if (System.currentTimeMillis() > passToken.getExpiryDate().getTime()) {
			log.info("current date: {} and expirated date: {}", System.currentTimeMillis(),  passToken.getExpiryDate().getTime());
			redirectAttributes.addFlashAttribute("message", "Token was expirated!");
			return REDIRECT + "newUserAccount";
		}

		User user = passToken.getUser();
		String username = user.getUsername();

		UserDetails userDetails = userSecurityService.loadUserByUsername(username);

		Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(),
				userDetails.getAuthorities());

		SecurityContextHolder.getContext().setAuthentication(authentication);

		model.addAttribute("user", user);
		model.addAttribute("classActiveEdit", true);

		return "myProfile";
	}

	@PostMapping(value="/updateUserInfo")
	public String updateUserInfo(
			@ModelAttribute("user") User user,
			@ModelAttribute("newPassword") String newPassword,
			Model model
			) throws Exception {

		User currentUser = userService.findById(user.getId());

		if(Objects.isNull(currentUser)) {
			throw new Exception ("User not found");
		}

		/*check email already exists*/
		if (Objects.nonNull(userService.findByEmail(user.getEmail()))) {
			if(!userService.findByEmail(user.getEmail()).getId().equals(currentUser.getId())) {
				model.addAttribute("emailExists", true);
				return "myProfile";
			}
		}

		/*check username already exists*/
		if (Objects.nonNull(userService.findByUsername(user.getUsername()))) {
			if(!userService.findByUsername(user.getUsername()).getId().equals(currentUser.getId())) {
				model.addAttribute("usernameExists", true);
				return "myProfile";
			}
		}

		/*update password*/
		if (Objects.nonNull(newPassword) && !newPassword.isEmpty()){
			BCryptPasswordEncoder passwordEncoder = SecurityUtility.passwordEncoder();
			currentUser.setPassword(passwordEncoder.encode(newPassword));
		} else {
			model.addAttribute("incorrectPassword", true);
			return "myProfile";
		}

		currentUser.setFirstName(user.getFirstName());
		currentUser.setLastName(user.getLastName());
		currentUser.setUsername(user.getUsername());
		currentUser.setEmail(user.getEmail());
		currentUser.setCompanyName(user.getCompanyName());

		userService.save(currentUser);

		model.addAttribute("updateSuccess", true);
		model.addAttribute("user", currentUser);
		model.addAttribute("classActiveEdit", true);

		model.addAttribute("listOfShippingAddresses", true);
		model.addAttribute("listOfCreditCards", true);

		UserDetails userDetails = userSecurityService.loadUserByUsername(currentUser.getUsername());

		Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(),
				userDetails.getAuthorities());

		SecurityContextHolder.getContext().setAuthentication(authentication);
		model.addAttribute("orderList", user.getOrderList());

		return "myProfile";
	}

	@GetMapping("/orderDetail")
	public String orderDetail(@RequestParam("id") Long orderId, Principal principal, Model model){

		User user = userService.findByUsername(principal.getName());
		Order order = orderService.findOne(orderId);

		if(!order.getUser().getId().equals(user.getId())) {
			return "badRequestPage";
		} else {
			List<CartItem> cartItemList = cartItemService.findByOrder(order);
			if (RecalculateCart.getSumSubTotalLtcLw(cartItemList).compareTo(LESS_THEN_CRATE_LTC) < 0 && !RecalculateCart.getSumSubTotalLtcLw(cartItemList).equals(BigDecimal.ZERO)) {
				model.addAttribute("lessThenCrateLtc", true);
			}
			model.addAttribute("cartItemList", cartItemList);
			model.addAttribute("user", user);
			model.addAttribute("order", order);

			model.addAttribute("userPaymentList", user.getUserPaymentList());
			model.addAttribute("userShippingList", user.getUserShippingList());
			model.addAttribute("orderList", user.getOrderList());

			UserShipping userShipping = new UserShipping();
			model.addAttribute("userShipping", userShipping);

			model.addAttribute(COUNTRIES_LIST, getCountryList());

			model.addAttribute("listOfShippingAddresses", true);
			model.addAttribute("classActiveOrders", true);
			model.addAttribute("displayOrderDetail", true);
			model.addAttribute("orderShippingAndOrderGrandWidthTotal", tariffZoneService.getTariffZoneAndGrandWidthTotal(order));

            return "myProfile";
		}
	}

	@GetMapping(value = {"/getAQoute", "/getAQoute/thankYou"})
	public String getAQuote(@RequestParam(value = "id", required = false) Long id, Model model) {

		log.info("get a quote");

		model.addAttribute(BREADCRUMB, "/productshelf?category=all");
		model.addAttribute("breadcrumbTtext", "Get a quote");
		RequestPricing requestPricing = new RequestPricing();
		model.addAttribute("requestPricing", requestPricing);
		model.addAttribute(COUNTRIES_LIST, getCountryList());


		return "getAQuote";
	}

	@PostMapping("/getAQoute")
	public String getAQuotePost(@ModelAttribute("requestPricing") RequestPricing requestPricing,
								@RequestParam(name="g-recaptcha-response") String recaptchaResponse,
								HttpServletRequest request,
								Model model,
								RedirectAttributes redirectAttributes) {

		if (Objects.isNull(requestPricing)) {
			log.info("Request pricing is empty.");
			return null;
		}

		if (getCaptchaVerify(recaptchaResponse, request, redirectAttributes)) {
			return REDIRECT + "getAQoute";
		}

		requestPricing.setCreated(LocalDateTime.now());
		requestPricing.setActive(true);
		requestPricingService.save(requestPricing);
		log.info("Save data to db");

		mailSender.send(mailConstructor.constructRequestPricing(requestPricing));
		log.info("Email was send");

		model.addAttribute(BREADCRUMB, "/productshelf?category=all");
		model.addAttribute("breadcrumbTtext", "Get a quote");
		redirectAttributes.addFlashAttribute("emailWasSend", "Email was send!");
		redirectAttributes.addFlashAttribute("emailWasSendThankYou", "Thank you, we will contact you as soon as posible.");

		return REDIRECT + "/getAQoute/thankYou";
	}

	private List<Countries> getCountryList() {
		return countriesService.getCountryList();
	}

}
