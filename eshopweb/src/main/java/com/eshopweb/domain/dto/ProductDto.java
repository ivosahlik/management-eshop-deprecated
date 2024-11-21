package com.eshopweb.domain.dto;

import com.eshopweb.domain.Banners;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Intellij Idea
 * Created by ivosahlik on 05/12/2018
 */
@NoArgsConstructor
@Data
@Entity
public class ProductDto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String productName;

    @Column(columnDefinition="text")
    private String productDescription;

    private double shippingWeight;

    private double concurentPrice;

    private double ourPrice;

    private int inStockNumber;

    private boolean active;

    private String type;

    private String productNamePath;

    private String typeTitlePath;

    private String subCategorytitlePath;

    private String categoryTitlePath;

    private boolean getAQuote;

    private boolean addToCart;

    private boolean displayPrice;

    private String estimatedDelivery;

    private boolean banner1;

    private boolean banner2;

    private boolean banner3;

    private boolean banner4;

}
