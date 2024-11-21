package com.eshopweb.service.impl;

import com.eshopweb.domain.DhlTransport;
import com.eshopweb.repository.DhlTransportRepository;
import com.eshopweb.service.DhlTransportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Intellij Idea
 * Created by ivosahlik on 2019-04-22
 */
@RequiredArgsConstructor
@Service
public class DhlTransportServiceImpl implements DhlTransportService {

    private final DhlTransportRepository dhlTransportRepository;

    @Override
    public DhlTransport save(DhlTransport dhlTransport) {
        return dhlTransportRepository.save(dhlTransport);
    }

    @Override
    public List<DhlTransport> findAll() {
        return StreamSupport.stream(dhlTransportRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public void removeTransport(int parseInt) {
        dhlTransportRepository.delete(parseInt);
    }

    @Override
    public DhlTransport findOneByWight(double grandWidthTotal) {
        return dhlTransportRepository.findOneByWight(grandWidthTotal);
    }


}
