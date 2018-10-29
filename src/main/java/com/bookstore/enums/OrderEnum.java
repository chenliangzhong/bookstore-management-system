package com.bookstore.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by heanxing on 2018/10/23.
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@JsonIgnoreProperties(value = {"declaring_class"})
public enum OrderEnum {

    PAYMENT(1, "待支付"),
    DELIVER(2,"待发货"),
    RECEVICE(3,"待收货"),
    EVALUATE(4,"待评价"),
    FINISH(5,"完成");

    private int value;
    private String desc;

    OrderEnum(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public int getValue() {return value;}

    public String getDesc() {return desc;}

    public static  OrderEnum valueOf(int value) {
        for ( OrderEnum orderEnum :  OrderEnum.values()) {
            if (orderEnum.value == value) return orderEnum;
        }
        return null;
    }



}
