package com.kritica.springbatchjpa.model;

import jakarta.persistence.*;
@Entity
public class Product {
    @Id
    private Long productId;
    private String title;
    private String description;
    private Double price;
    private Double discount;
    private Double finalAmount;

    public Product() {
    }

    public Product(Long productId, String title, String description, Double price, Double discount, Double finalAmount) {
        this.productId = productId;
        this.title = title;
        this.description = description;
        this.price = price;
        this.discount = discount;
        this.finalAmount = finalAmount;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Double getFinalAmount() {
        return finalAmount;
    }

    public void setFinalAmount(Double finalAmount) {
        this.finalAmount = finalAmount;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", discount=" + discount +
                ", finalAmount=" + finalAmount +
                '}';
    }
}

