package com.eshopweb.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Intellij Idea
 * Created by ivosahlik on 2019-11-12
 */
@Data
@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer messageId;

    private String title;

    private String textMessage;

    private boolean showAlert;

    private boolean active;

    private String backgroundColor;

    private String color;

    private String fontWeight;

    private String fontSize;

    private String app;

}
