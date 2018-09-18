package com.bookstore.bean;

/**
 * Created by ${邹} on 2018/9/11.
 */
public class Category {
    private Long id;
    private String theory;
    private String philosophy;
    private String sciences;
    private String law;
    private String military;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTheory() {
        return theory;
    }

    public void setTheory(String theory) {
        this.theory = theory;
    }

    public String getPhilosophy() {
        return philosophy;
    }

    public void setPhilosophy(String philosophy) {
        this.philosophy = philosophy;
    }

    public String getSciences() {
        return sciences;
    }

    public void setSciences(String sciences) {
        this.sciences = sciences;
    }

    public String getLaw() {
        return law;
    }

    public void setLaw(String law) {
        this.law = law;
    }

    public String getMilitary() {
        return military;
    }

    public void setMilitary(String military) {
        this.military = military;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", theory='" + theory + '\'' +
                ", philosophy='" + philosophy + '\'' +
                ", sciences='" + sciences + '\'' +
                ", law='" + law + '\'' +
                ", military='" + military + '\'' +
                '}';
    }
}
