package com.adminportal.service.impl;

import com.adminportal.domain.VideoStreams;
import com.adminportal.repository.VideoStreamRepository;
import com.adminportal.service.VideoStreamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Intellij Idea
 * Created by ivosahlik on 2019-10-28
 */
@Service
public class VideoStreamServiceImpl implements VideoStreamService {

    @Autowired
    private VideoStreamRepository videoStreamRepository;

    @Override
    public List<VideoStreams> getVideoStreams() {
        return StreamSupport.stream(videoStreamRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public VideoStreams save(VideoStreams videoStreams) {
        return videoStreamRepository.save(videoStreams);
    }

    @Override
    public VideoStreams findById(Integer id) {
        return videoStreamRepository.findOne(id);
    }

    @Override
    public void deleteById(Integer id) {
        videoStreamRepository.delete(id);
    }
}
