package com.adminportal.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

/**
 * Intellij Idea
 * Created by ivosahlik on 2019-11-06
 */
@Data
@Entity
public class CustomSettings {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String title;

    private boolean active;

    @Column(columnDefinition="text")
    private String html;

    @Column(columnDefinition="text")
    private String footerHtmlFragment;

    @Column(columnDefinition="text")
    private String copyrigtHtmlFragment;

    private LocalDateTime created;
    private String userCreated;
    private LocalDateTime updated;
    private String userUpdated;

    private String app;


}
