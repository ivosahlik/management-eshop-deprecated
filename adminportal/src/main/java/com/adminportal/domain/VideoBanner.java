package com.adminportal.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class VideoBanner {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String title;

    private String description;

    private boolean active;

    @OneToMany(
            cascade = CascadeType.ALL
    )
    @JoinColumn(name = "video_banner_id")
    private List<VideoStreams> videoStreams;

    private LocalDateTime created;

    private String userCreated;

    private LocalDateTime updated;

}
