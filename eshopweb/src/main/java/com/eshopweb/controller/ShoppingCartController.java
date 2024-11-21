package com.eshopweb.controller;

import com.eshopweb.domain.CartItem;
import com.eshopweb.domain.Product;
import com.eshopweb.domain.ShoppingCart;
import com.eshopweb.domain.User;
import com.eshopweb.domain.security.Role;
import com.eshopweb.domain.security.UserRole;
import com.eshopweb.service.CartItemService;
import com.eshopweb.service.ProductService;
import com.eshopweb.service.ShoppingCartService;
import com.eshopweb.service.UserService;
import com.eshopweb.utility.SecurityUtility;
import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * Intellij Idea
 * Created by ivosahlik on 05/12/2018
 */
@Slf4j
@Controller
@RequestMapping("/shoppingCart")
public class ShoppingCartController {

	@Autowired
	private UserService userService;

	@Autowired
	private CartItemService cartItemService;

	@Autowired
	private ProductService productService;

	@Autowired
	private ShoppingCartService shoppingCartService;

	@RequestMapping("/cart")
	public String shoppingCart(Model model,
							   Principal principal) {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String name = authentication.getName();
		User user;
		if (principal != null) {
			user = userService.findByUsername(name);
		} else {
			String sessionId = RequestContextHolder.currentRequestAttributes().getSessionId();
			user = userService.findBySessionId(sessionId);
		}

		if (user != null) {
			ShoppingCart shoppingCart = user.getShoppingCart();
			List<CartItem> cartItemList = cartItemService.findByShoppingCart(shoppingCart);
			shoppingCartService.updateShoppingCart(shoppingCart);
			model.addAttribute("cartItemList", cartItemList);
			model.addAttribute("cartQty", cartItemList.stream().mapToInt(CartItem::getQty).sum());
			model.addAttribute("cartSum", cartItemList.stream()
					.map(CartItem::getSubtotal)
					.reduce(BigDecimal.ZERO, BigDecimal::add));
			if (Objects.nonNull(shoppingCart)) {
				model.addAttribute("shoppingCart", shoppingCart);
			}
		} else {
			model.addAttribute("cartItemList", Collections.emptyList());
		}

		return "shoppingCart";
	}

	@PostMapping("/addItem")
	public String addItem(@ModelAttribute("product") Product product,
						  @ModelAttribute("qty") String qty,
						  @ModelAttribute("urlForward") String urlForward,
						  Principal principal,
						  RedirectAttributes redirectAttributes,
						  @RequestParam(value = "productId", required = false) Long productId) throws Exception {

		String redirect = "redirect:/" + (urlForward.startsWith("/") ? urlForward.substring(1) : urlForward);

		if (Strings.isNullOrEmpty(qty)) {
			redirectAttributes.addFlashAttribute("isQtyEqualsNullOrEmpty", true);
			return redirect;
		}

		if (Integer.parseInt(qty) == 0) {
			redirectAttributes.addFlashAttribute("isQtyEqualsZero", true);
			return redirect;
		}

		if (Objects.nonNull(productId)) {
			product = productService.findOne(productId);
		} else {
			product = productService.findOne(product.getId());
		}

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String name = authentication.getName();

		User user = getUser(name, principal);

		cartItemService.addProductToCartItem(product, user, Integer.parseInt(qty));

		redirectAttributes.addFlashAttribute("addProductSuccess", true);

		return redirect;
	}

	private User getUser(String name, Principal principal) throws Exception {
		User user;
		if (principal != null) {
			user = userService.findByUsername(name);
		} else {
			String sessionId = RequestContextHolder.currentRequestAttributes().getSessionId();

			User userGuest = new User();
			userGuest.setFirstName(name);
			userGuest.setLastName(name);
			userGuest.setUsername(sessionId);
			userGuest.setEmail(sessionId + "@guest.cz");
			userGuest.setSessionId(sessionId);

			String password = SecurityUtility.randomPassword();
			String encryptedPassword = SecurityUtility.passwordEncoder().encode(password);
			userGuest.setPassword(encryptedPassword);

			Role role = new Role();
			role.setRoleId(1);
			role.setName("GUEST_ROLE");
			Set<UserRole> userRoles = new HashSet<>();
			userRoles.add(new UserRole(userGuest, role));
			userService.createUser(userGuest, userRoles);

			user = userService.findBySessionId(sessionId);
		}
		return user;
	}

	@RequestMapping("/updateCartItem")
	public String updateShoppingCart(@ModelAttribute("id") Long cartItemId,
									 @ModelAttribute("qty") int qty) {
		CartItem cartItem = cartItemService.findById(cartItemId);
		cartItem.setQty(qty);
		cartItemService.updateCartItem(cartItem);

		return "forward:/shoppingCart/cart";
	}

	@RequestMapping("/removeItem")
	public String removeItem(@RequestParam("id") Long id) {
		if (Objects.isNull(cartItemService.findById(id))) {
			return "redirect:/";
		}
		cartItemService.removeCartItem(cartItemService.findById(id));

		return "forward:/shoppingCart/cart";
	}
}
