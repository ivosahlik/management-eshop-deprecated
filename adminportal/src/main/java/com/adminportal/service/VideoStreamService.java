package com.adminportal.service;

import com.adminportal.domain.VideoStreams;

import java.util.List;

/**
 * Intellij Idea
 * Created by ivosahlik on 2019-10-28
 */
public interface VideoStreamService {

    List<VideoStreams> getVideoStreams();

    VideoStreams save(VideoStreams videoStreams);

    VideoStreams findById(Integer id);

    void deleteById(Integer id);
}
