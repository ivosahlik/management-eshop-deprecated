package com.adminportal.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

/**
 * Intellij Idea
 * Created by ivosahlik on 2019-09-28
 */
@Data
@Entity
public class Banners {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String bannerId;

    private String title;

    private boolean active;

    private String description;

    private LocalDateTime created;
    private String userCreated;
    private LocalDateTime updated;

}
