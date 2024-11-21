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
 * Created by ivosahlik on 06/01/2019
 */
@NoArgsConstructor
@Data
@Entity
public class RequestPricing {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String lastname;

    private String firstname;

    private String email;

    private String phone;

    private String companyName;

    private String city;

    private String country;

    @Column(columnDefinition="text")
    private String description;

    private LocalDateTime created;

    private boolean answered = false;

    private LocalDateTime answeredWhen;

    private boolean active;
}
