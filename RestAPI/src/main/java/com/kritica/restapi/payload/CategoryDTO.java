package com.kritica.restapi.payload;

public class CategoryDTO {

    private int Id;
    private String categoryName;

    public CategoryDTO() {

    }

    public CategoryDTO(int id, String categoryName) {
        this.Id = id;
        this.categoryName = categoryName;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String name) {
        this.categoryName = name;
    }
}
