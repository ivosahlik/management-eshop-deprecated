package com.adminportal.service.impl;

import com.adminportal.domain.DhlTransport;
import com.adminportal.repository.DhlTransportRepository;
import com.adminportal.service.DhlTransportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Intellij Idea
 * Created by ivosahlik on 2019-04-22
 */
@Service
public class DhlTransportServiceImpl implements DhlTransportService {

    @Autowired
    private DhlTransportRepository dhlTransportRepository;

    @Override
    public DhlTransport save(DhlTransport dhlTransport) {
        return dhlTransportRepository.save(dhlTransport);
    }

    @Override
    public List<DhlTransport> findAll() {
        return StreamSupport.stream(dhlTransportRepository.findAll().spliterator(), false)
                .sorted(Comparator.comparing(DhlTransport::getWeight))
                .collect(Collectors.toList());
    }

    @Override
    public void removeTransport(int parseInt) {
        dhlTransportRepository.delete(parseInt);
    }

}
