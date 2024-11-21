package com.eshopweb.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Intellij Idea
 * Created by ivosahlik on 2019-09-28
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class VideoBanner {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String title;

    private String description;

    private boolean active;

    @OneToMany(
            mappedBy = "videoBanner",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            orphanRemoval = true
    )
    private List<VideoStreams> videoStreams;

    private LocalDateTime created;

    private String userCreated;

    private LocalDateTime updated;

}
