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
 * Created by ivosahlik on 2019-11-03
 */
@Data
@Entity
public class Support {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String title;

    @Column(columnDefinition="text")
    private String description;

    private Boolean active;

    private LocalDateTime created;

    private String userCreated;

    private LocalDateTime updated;

    private String app;

}
