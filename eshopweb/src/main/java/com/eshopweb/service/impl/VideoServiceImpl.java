package com.eshopweb.service.impl;

import com.eshopweb.domain.VideoBanner;
import com.eshopweb.repository.VideoRepository;
import com.eshopweb.service.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Intellij Idea
 * Created by ivosahlik on 2019-10-13
 */
@RequiredArgsConstructor
@Service
public class VideoServiceImpl implements VideoService {

    private final VideoRepository videoRepository;

    @Override
    public List<VideoBanner> findAll() {
        return StreamSupport.stream(videoRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }
}
