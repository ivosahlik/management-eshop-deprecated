package com.eshopweb.interceptor;

import com.eshopweb.domain.CartItem;
import com.eshopweb.domain.CustomSettings;
import com.eshopweb.domain.ShoppingCart;
import com.eshopweb.domain.User;
import com.eshopweb.service.CartItemService;
import com.eshopweb.service.MessageService;
import com.eshopweb.service.ShoppingCartService;
import com.eshopweb.service.UserService;
import com.eshopweb.service.impl.DataSlowDataAccessor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Intellij Idea
 * Created by ivosahlik on 24/12/2018
 */
@Slf4j
public class CoreInterceptor implements HandlerInterceptor {

    @Autowired
    private UserService userService;

    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private DataSlowDataAccessor dataSlowDataAccessor;

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) {
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) {

        if (Objects.nonNull(modelAndView)) {
            boolean isRedirectView = modelAndView.getView() instanceof RedirectView;
            boolean viewNameStartsWithRedirect = Objects.nonNull(modelAndView.getViewName()) &&
                    modelAndView.getViewName().startsWith(UrlBasedViewResolver.REDIRECT_URL_PREFIX);
            if (modelAndView.hasView() && !isRedirectView && !viewNameStartsWithRedirect) {
                addCommonModelData(modelAndView);
            }
        }
    }

    private void addCommonModelData(ModelAndView modelAndView) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User user;
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            user = userService.findByUsername(currentUserName);
        } else {
            String sessionId = RequestContextHolder.currentRequestAttributes().getSessionId();
            user = userService.findBySessionId(sessionId);
        }

        if (user != null) {
            ShoppingCart shoppingCart = user.getShoppingCart();
            List<CartItem> cartItemList = cartItemService.findByShoppingCart(shoppingCart);
            shoppingCartService.updateShoppingCart(shoppingCart);
            modelAndView.getModelMap().addAttribute("cartQty", cartItemList.stream().mapToInt(CartItem::getQty).sum());
        } else {
            modelAndView.getModelMap().addAttribute("cartQty", 0);
        }

        modelAndView.getModelMap().addAttribute("navigation", dataSlowDataAccessor.getNavigationMap());
        modelAndView.getModelMap().addAttribute("app", !DataSlowDataAccessor.getApp().equals("dcsolutions"));
        modelAndView.getModelMap().addAttribute("appText", DataSlowDataAccessor.getApp());

        Optional<CustomSettings> customSettings = dataSlowDataAccessor.getOneByActiveCustomSettings();
        customSettings.ifPresent(settings -> modelAndView.getModelMap().addAttribute("htmlToInject", settings));

        modelAndView.getModelMap().addAttribute("messages", messageService.getMessages().orElse(null));
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        // TODO document why this method is empty
    }
}
