package com.adminportal.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Intellij Idea
 * Created by ivosahlik on 2019-10-22
 */
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class VideoStreams {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String title;

    private String description;

    private String streamUrl;

    private boolean active;

    private boolean newVideo;

    private Integer video_banner_id;

}
