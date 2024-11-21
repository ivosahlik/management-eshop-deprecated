package com.eshopweb.service.impl;

import com.eshopweb.domain.DhlTransport;
import com.eshopweb.domain.ShoppingCart;
import com.eshopweb.enumType.EnumLtcState;
import com.eshopweb.repository.ShoppingCartRepository;
import com.eshopweb.service.CheckoutService;
import com.eshopweb.service.DhlTransportService;
import com.eshopweb.service.TariffZoneService;
import com.eshopweb.utility.Constants;
import com.eshopweb.utility.RecalculateCart;
import com.google.common.base.Strings;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import static com.eshopweb.utility.Constants.LESS_THEN_CRATE_LTC_ADD_300_EURO;
import static com.eshopweb.utility.Constants.MAX_WEIGHT;

/**
 * Intellij Idea
 * Created by ivosahlik on 2019-05-04
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class CheckoutServiceImpl implements CheckoutService{

    public static final String CZECH_REPUBLIC = "Czech Republic";
    public static final String LESS_THEN_CRATE_LTC = "lessThenCrateLtc";

    private final TariffZoneService tariffZoneService;
    private final DhlTransportService dhlTransportService;
    private final ShoppingCartRepository shoppingCartRepository;

    @Override
    public void calculateTotalOrderWithShippingAndVat(Model model,
                                                      ShoppingCart shoppingCart,
                                                      String shippingAddressVat,
                                                      String shippingAddressState,
                                                      BigDecimal percentIncludeVat) {
        BigDecimal grandTotal = shoppingCart.getGrandTotal();
        Integer tariffZone = tariffZoneService.findTariffZoneByCountry(shippingAddressState);

        List<BigDecimal> shippingList = new ArrayList<>();
        List<Double> widthTotalList = new ArrayList<>();
        List<BigDecimal> subTotalLtcLwList = new ArrayList<>();

        shoppingCart.getCartItemList().forEach(product -> {
            tariffZoneService.getListFreeShipping(shoppingCart.getCartItemList(), widthTotalList);
            // is ltc
            if (product.getProduct().getLtc() != null && product.getProduct().getLtc().equals(EnumLtcState.LTC_LW.getLtc())) {
                subTotalLtcLwList.add(product.getSubtotal());
            }
        });

        double grandWidthTotal = widthTotalList.stream().mapToDouble(Double::doubleValue).sum();

        if (tariffZone != null && grandWidthTotal <= MAX_WEIGHT && grandWidthTotal != 0) {
            DhlTransport dhlTransport = dhlTransportService.findOneByWight(grandWidthTotal);
            shippingList.add(RecalculateCart.getPricesByWightAndZone(tariffZone, dhlTransport));
        } else {
            shippingList.add(BigDecimal.ZERO);
            if (tariffZone == null || grandWidthTotal >= MAX_WEIGHT) {
                model.addAttribute("isZoneDefined", true);
            }
        }

        BigDecimal shipping = shippingList.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal sumSubTotalLtcLw = subTotalLtcLwList.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal sumTotalOrderWithShipping;

        final boolean b = sumSubTotalLtcLw.compareTo(Constants.LESS_THEN_CRATE_LTC) < 0 && !sumSubTotalLtcLw.equals(BigDecimal.ZERO);
        if (b) {
            sumTotalOrderWithShipping = grandTotal.add(shipping)
                    .add(LESS_THEN_CRATE_LTC_ADD_300_EURO)
                    .setScale(0, RoundingMode.HALF_UP);
            model.addAttribute(LESS_THEN_CRATE_LTC, true);
        } else {
            sumTotalOrderWithShipping = grandTotal.add(shipping).setScale(0, RoundingMode.HALF_UP);
        }

        shoppingCart.setSumTotalOrderWithShipping(sumTotalOrderWithShipping);
        shoppingCart.setShipping(shipping);

        BigDecimal priceIncludeVat;
        if (b) {
            priceIncludeVat = grandTotal.add(LESS_THEN_CRATE_LTC_ADD_300_EURO)
                    .multiply(percentIncludeVat)
                    .setScale(0, RoundingMode.HALF_UP);
        } else {
            priceIncludeVat = grandTotal
                    .multiply(percentIncludeVat)
                    .setScale(0, RoundingMode.HALF_UP);
        }

        if (Strings.isNullOrEmpty(shippingAddressVat) || shippingAddressState.equals(CZECH_REPUBLIC)) {
            shoppingCart.setGrandTotalVat(priceIncludeVat);
            shoppingCart.setSumTotalOrderWithShippingAndIncludeVat(
                    sumTotalOrderWithShipping.add(priceIncludeVat)
                            .setScale(2, RoundingMode.HALF_UP)
            );
        } else {
            shoppingCart.setGrandTotalVat(BigDecimal.ZERO);
            shoppingCart.setSumTotalOrderWithShippingAndIncludeVat(
                    sumTotalOrderWithShipping.setScale(2, RoundingMode.HALF_UP)
            );
        }

        boolean tariffZoneAndGrandWidthTotal = tariffZone != null && grandWidthTotal <= MAX_WEIGHT;
        model.addAttribute("tariffZone", tariffZone);
        model.addAttribute("tariffZoneAndGrandWidthTotal", tariffZoneAndGrandWidthTotal);
        model.addAttribute("percentIncludeVat", percentIncludeVat);

        shoppingCartRepository.save(shoppingCart);
    }

}
