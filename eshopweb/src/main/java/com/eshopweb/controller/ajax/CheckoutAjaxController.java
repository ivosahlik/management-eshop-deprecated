package com.eshopweb.controller.ajax;

import com.eshopweb.domain.ShoppingCart;
import com.eshopweb.service.CheckoutService;
import com.eshopweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.security.Principal;

/**
 * Intellij Idea
 * Created by ivosahlik on 2019-05-06
 */
@RequestMapping("/ajax")
@Controller
public class  CheckoutAjaxController {

    @Value("${percent.include.vat}")
    private BigDecimal percentIncludeVat;

    @Autowired
    private CheckoutService checkoutService;

    @Autowired
    private UserService userService;

    @GetMapping("/checkout")
    public ModelAndView getOrderSummary(@RequestParam("username") String username,
                                        @RequestParam("shippingState") String shippingState,
                                        @RequestParam("shippingVat") String shippingVat,
                                        Model model,
                                        Principal principal) {
        ShoppingCart shoppingCart;
        if (principal != null) {
            shoppingCart = userService.findByUsername(username).getShoppingCart();
        } else {
            String sessionId = RequestContextHolder.currentRequestAttributes().getSessionId();
            shoppingCart = userService.findBySessionId(sessionId).getShoppingCart();
        }
        model.addAttribute("shoppingCart", shoppingCart);
        checkoutService.calculateTotalOrderWithShippingAndVat(model, shoppingCart, shippingVat, shippingState, percentIncludeVat);

        return new ModelAndView("common/macros :: order-summary");
    }
}
