package com.eshopweb.service;

import com.eshopweb.domain.DhlTransport;

import java.math.BigDecimal;
import java.util.List;

/**
 * Intellij Idea
 * Created by ivosahlik on 2019-04-22
 */
public interface DhlTransportService {

    DhlTransport save(DhlTransport dhlTransport);

    List<DhlTransport> findAll();

    void removeTransport(int parseInt);

    DhlTransport findOneByWight(double grandWidthTotal);
}
