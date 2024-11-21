package com.eshopweb.service;

import com.eshopweb.domain.CartItem;
import com.eshopweb.domain.Order;
import com.eshopweb.domain.TariffZone;

import java.util.List;

/**
 * Intellij Idea
 * Created by ivosahlik on 2019-04-22
 */
public interface TariffZoneService {

    TariffZone save(TariffZone tariffZone);

    List<TariffZone> findAll();

    void removeTariffZone(Integer id);

    Integer findTariffZoneByCountry(String shippingAddressCountry);

    boolean getTariffZoneAndGrandWidthTotal(Order order);

    void getListFreeShipping(List<CartItem> cartItemList, List<Double> widthTotalList);
}
