package com.bookstore.bean;

/**
 * Created by ${邹} on 2018/9/19.
 * 属性表
 */
public class Property {
    private Long id;
    private String name;

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    private Long categoryId;

    public void setCategory_id(Long category_id) {
        this.categoryId = category_id;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Property{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category_id=" + categoryId +
                '}';
    }
}
