package com.eshopweb.domain.dto;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

/**
 * Intellij Idea
 * Created by ivosahlik on 05/12/2018
 */
@Entity
public class ProductToCategoryDto {

    @Id
    private Long id;

    private String productName;

    private String productDescription;

    private String category;

    private String subCategory;

    private String type;

    private double shippingWeight; // přepravní váha

    private double concurentPrice;

    private double ourPrice;

    private int inStockNumber;

    private boolean active=true;

    private LocalDateTime created;

    private String userCreated;

    private String productNamePath;

    private String categoryTitlePath;

    private String description;


    public ProductToCategoryDto() {
    }

    public ProductToCategoryDto(Long id, String productName, String productDescription, String category, String subCategory, String type, double shippingWeight, double concurentPrice, double ourPrice, int inStockNumber, boolean active, LocalDateTime created, String userCreated, String productNamePath, String categoryTitlePath, String description) {
        this.id = id;
        this.productName = productName;
        this.productDescription = productDescription;
        this.category = category;
        this.subCategory = subCategory;
        this.type = type;
        this.shippingWeight = shippingWeight;
        this.concurentPrice = concurentPrice;
        this.ourPrice = ourPrice;
        this.inStockNumber = inStockNumber;
        this.active = active;
        this.created = created;
        this.userCreated = userCreated;
        this.productNamePath = productNamePath;
        this.categoryTitlePath = categoryTitlePath;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getShippingWeight() {
        return shippingWeight;
    }

    public void setShippingWeight(double shippingWeight) {
        this.shippingWeight = shippingWeight;
    }

    public double getConcurentPrice() {
        return concurentPrice;
    }

    public void setConcurentPrice(double concurentPrice) {
        this.concurentPrice = concurentPrice;
    }

    public double getOurPrice() {
        return ourPrice;
    }

    public void setOurPrice(double ourPrice) {
        this.ourPrice = ourPrice;
    }

    public int getInStockNumber() {
        return inStockNumber;
    }

    public void setInStockNumber(int inStockNumber) {
        this.inStockNumber = inStockNumber;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public String getUserCreated() {
        return userCreated;
    }

    public void setUserCreated(String userCreated) {
        this.userCreated = userCreated;
    }

    public String getProductNamePath() {
        return productNamePath;
    }

    public void setProductNamePath(String productNamePath) {
        this.productNamePath = productNamePath;
    }

    public String getCategoryTitlePath() {
        return categoryTitlePath;
    }

    public void setCategoryTitlePath(String categoryTitlePath) {
        this.categoryTitlePath = categoryTitlePath;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
