package com.kritica.restapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;

@Entity(name="categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @NotEmpty(message="Please enter category.Field can't be blank")
    //@Size(min=2, max=15)
    //@Size(min = 2)
    //@Size(min = 2, message="Please enter minimum 5 character")
    private String categoryName;

    public Category(int id, String categoryName) {
        this.id = id;
        this.categoryName = categoryName;
    }

    public Category() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
