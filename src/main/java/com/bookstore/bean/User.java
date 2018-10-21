package com.bookstore.bean;

import com.bookstore.enums.FreezeEnum;
import com.bookstore.enums.RoleTypeEnum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(value = {"handler"})
public class User {

    private Long id;
    private String username;
    private String mobile_phone;
    private String password;
    private String email;
    private List<Order> orders;//用户创建订单列表
    private RoleTypeEnum roleTypeEnum;
    private FreezeEnum freezeEnum;

    public User() {
    }

    public User(Long id, String username, String mobile_phone, String password, String email, List<Order> orders) {
        this.id = id;
        this.username = username;
        this.mobile_phone = mobile_phone;
        this.password = password;
        this.email = email;
        this.orders = orders;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMobile_phone() {
        return mobile_phone;
    }

    public void setMobile_phone(String mobile_phone) {
        this.mobile_phone = mobile_phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public RoleTypeEnum getRoleTypeEnum() {
        return roleTypeEnum;
    }

    public void setRoleTypeEnum(RoleTypeEnum roleTypeEnum) {
        this.roleTypeEnum = roleTypeEnum;
    }

    public FreezeEnum getFreezeEnum() {
        return freezeEnum;
    }

    public void setFreezeEnum(FreezeEnum freezeEnum) {
        this.freezeEnum = freezeEnum;
    }
}



