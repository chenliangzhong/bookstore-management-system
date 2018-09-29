package com.bookstore.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;
import java.util.List;

/**
 * Created by heanxing on 2018/9/25.
 */
@JsonIgnoreProperties(value = {"handler"})
public class Order {
    private Long id;
    private Long user_id;
    private String order_code;
    private String address;
    private String user_message;
    private String receivre;
    private String mobile;
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date create_date;
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date pay_date;
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date delivery_date;
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date confirm_date;
    private int status;
    private User user;//用户信息
    private List<OrderItem> orderItems;///订单详细
    public Order() {
    }
    public Order(Long id, Long user_id, String order_code, String address, String user_message, String receivre, String mobile, List<OrderItem> orderItems, Date create_date, Date pay_date, Date delivery_date, Date confirm_date, int status, User user, List<OrderItem> orderItems1) {
        this.id = id;
        this.user_id = user_id;
        this.order_code = order_code;
        this.address = address;
        this.user_message = user_message;
        this.receivre = receivre;
        this.mobile = mobile;
        this.orderItems = orderItems;
        this.create_date = create_date;
        this.pay_date = pay_date;
        this.delivery_date = delivery_date;
        this.confirm_date = confirm_date;
        this.status = status;
        this.user = user;
        this.orderItems = orderItems1;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getOrder_code() {
        return order_code;
    }

    public void setOrder_code(String order_code) {
        this.order_code = order_code;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUser_message() {
        return user_message;
    }

    public void setUser_message(String user_message) {
        this.user_message = user_message;
    }

    public String getReceivre() {
        return receivre;
    }

    public void setReceivre(String receivre) {
        this.receivre = receivre;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    public Date getPay_date() {
        return pay_date;
    }

    public void setPay_date(Date pay_date) {
        this.pay_date = pay_date;
    }
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    public Date getDelivery_date() {
        return delivery_date;
    }

    public void setDelivery_date(Date delivery_date) {
        this.delivery_date = delivery_date;
    }
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    public Date getConfirm_date() {
        return confirm_date;
    }

    public void setConfirm_date(Date confirm_date) {
        this.confirm_date = confirm_date;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
