package com.bookstore.bean;

/**
 * Created by ${邹} on 2018/9/19.
 * 属性表
 */
public class Property {
    private Long id;
    private String name;
    private Category category;

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Property{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category=" + category +
                '}';
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
