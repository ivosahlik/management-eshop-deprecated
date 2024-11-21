package com.adminportal.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Intellij Idea
 * Created by ivosahlik on 2019-04-22
 */
@Data
@Entity
public class TariffZone {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String country;

    private int zone;

    private boolean active=true;

}
