package com.adminportal.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

/**
 * Intellij Idea
 * Created by ivosahlik on 27/12/2018
 */
@NoArgsConstructor
@Data
@Entity
public class Settings {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    private boolean active=true;

    @Column(columnDefinition="text")
    private String html;

    private LocalDateTime created;
    private String userCreated;
    private LocalDateTime updated;
    private String userUpdated;
}
