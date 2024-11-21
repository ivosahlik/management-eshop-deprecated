package com.eshopweb.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import java.time.LocalDateTime;

/**
 * Intellij Idea
 * Created by ivosahlik on 05/12/2018
 */
@Data
@NoArgsConstructor
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    private String language;

    private boolean active;

    private boolean mainNavigation;

    @Column(columnDefinition="text")
    private String description;

    private LocalDateTime created;
    private String userCreated;
    private LocalDateTime updated;
    private String userUpdated;

    @Lob
    private byte[] image;
    private String originalFileName;
    private String contentType;
    private long size;

    private String categoryTitlePath;

    private int orderBy;

    private String app;
}
