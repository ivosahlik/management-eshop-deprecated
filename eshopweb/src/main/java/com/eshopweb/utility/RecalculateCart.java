package com.eshopweb.utility;

import com.eshopweb.domain.CartItem;
import com.eshopweb.domain.DhlTransport;
import com.eshopweb.enumType.EnumLtcState;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Intellij Idea
 * Created by ivosahlik on 2019-05-06
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RecalculateCart {

    public static BigDecimal getPricesByWightAndZone(int tariffZone, DhlTransport dhlTransport) {
        double pricesByWightAndZone;
        switch (tariffZone) {
            case 1:
                pricesByWightAndZone = dhlTransport.getZone1();
                break;
            case 2:
                pricesByWightAndZone = dhlTransport.getZone2();
                break;
            case 3:
                pricesByWightAndZone = dhlTransport.getZone3();
                break;
            case 4:
                pricesByWightAndZone = dhlTransport.getZone4();
                break;
            default:
                pricesByWightAndZone = 0;
        }

        return BigDecimal.valueOf(pricesByWightAndZone);
    }

    public static BigDecimal getSumSubTotalLtcLw(List<CartItem> cartItemList) {
        List<BigDecimal> subTotalLtcLwList = new ArrayList<>();

        cartItemList.forEach(product -> {
            if (product.getProduct().getLtc() != null && product.getProduct().getLtc().equals(EnumLtcState.LTC_LW.getLtc())) {
                subTotalLtcLwList.add(product.getSubtotal());
            }
        });

        return subTotalLtcLwList.stream().reduce(BigDecimal.ZERO, BigDecimal::add);

    }

}
