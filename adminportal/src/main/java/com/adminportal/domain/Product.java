package com.adminportal.domain;

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
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String productName;

    @Column(columnDefinition="text")
    private String productDescription;

    private String type;
    private String typePath;
    private long typeId;

    private String category;
    private String categoryPath;
    private long categoryId;

    private double shippingWeight; // přepravní váha
    private double concurentPrice;
    private double ourPrice;
    private int inStockNumber;
    private boolean active=true;
    private LocalDateTime created;
    private String userCreated;
    private LocalDateTime updated;
    private String userUpdated;

    @Lob
    private byte[] image;
    private String originalFileName;
    private String contentType;
    private long size;

    private String productNamePath;

    private boolean getAQuote;

    private boolean addToCart;

    private boolean displayPrice;

    private String estimatedDelivery;

    private boolean freeShipping;

    private String ltc;

    private boolean banner1;

    private boolean banner2;

    private boolean banner3;

    private boolean banner4;

}
