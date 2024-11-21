package com.eshopweb.service.impl;

import com.eshopweb.domain.Support;
import com.eshopweb.repository.SupportRepository;
import com.eshopweb.service.SupportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.StreamSupport;

/**
 * Intellij Idea
 * Created by ivosahlik on 2019-11-03
 */
@Service
@RequiredArgsConstructor
public class SupportServiceImpl implements SupportService {

    private final SupportRepository supportRepository;

    @Override
    public Optional<Support> getOneActiveSupport() {
        return StreamSupport.stream(supportRepository.findAll().spliterator(), false)
                .filter(Support::getActive)
                .filter(support -> support.getApp().contains(DataSlowDataAccessor.getApp()))
                .findFirst();
    }
}
