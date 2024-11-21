package com.eshopweb.service.impl;

import com.eshopweb.domain.CartItem;
import com.eshopweb.domain.Order;
import com.eshopweb.domain.TariffZone;
import com.eshopweb.repository.TariffZoneRepository;
import com.eshopweb.service.TariffZoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static com.eshopweb.utility.Constants.MAX_WEIGHT;

/**
 * Intellij Idea
 * Created by ivosahlik on 2019-04-22
 */
@RequiredArgsConstructor
@Service
public class TariffZoneServiceImpl implements TariffZoneService {

    private final TariffZoneRepository tariffZoneRepository;

    @Override
    public TariffZone save(TariffZone tariffZone) {
        return tariffZoneRepository.save(tariffZone);
    }

    @Override
    public List<TariffZone> findAll() {
        return StreamSupport.stream(tariffZoneRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public void removeTariffZone(Integer id) {
        tariffZoneRepository.delete(id);
    }

    @Override
    public Integer findTariffZoneByCountry(String country) {
        return tariffZoneRepository.findTariffZoneByCountry(country);
    }

    @Override
    public boolean getTariffZoneAndGrandWidthTotal(Order order) {
        Integer tariffZone = findTariffZoneByCountry(order.getShippingAddress().getShippingAddressState());
        List<Double> widthTotalList = new ArrayList<>();

        getListFreeShipping(order.getCartItemList(), widthTotalList);

        double grandWidthTotal = widthTotalList.stream().mapToDouble(Double::doubleValue).sum();

        return tariffZone != null && grandWidthTotal <= MAX_WEIGHT;
    }

    @Override
    public void getListFreeShipping(List<CartItem> cartItemList,
                                    List<Double> widthTotalList) {
       cartItemList.forEach(product -> {
                // free shipping
                if (!product.getProduct().isFreeShipping() ) {
                    widthTotalList.add(product.getProduct().getShippingWeight() * product.getQty());
                }
            }
        );
    }
}
