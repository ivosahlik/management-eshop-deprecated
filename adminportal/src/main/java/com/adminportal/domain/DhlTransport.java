package com.adminportal.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Intellij Idea
 * Created by ivosahlik on 2019-04-21
 */
@Data
@Entity
public class DhlTransport {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private double weight;

    private double zone1;

    private double zone2;

    private double zone3;

    private double zone4;

}
