package com.eshopweb.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    private String description;

    private String streamUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    private VideoBanner videoBanner;

    private boolean active;

    private boolean newVideo;

}
