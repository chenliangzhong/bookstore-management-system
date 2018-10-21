package com.bookstore.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@JsonIgnoreProperties(value = {"declaring_class"})
public enum  FreezeEnum {

    FROST(1, "冻结"),
    UNHITCH(2, "解冻");

    private int value;
    private String desc;

    FreezeEnum(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public int getValue() {return value;}

    public String getDesc() {return desc;}

    public static FreezeEnum valueOf(int value) {
        for (FreezeEnum freezeEnum : FreezeEnum.values()) {
            if (freezeEnum.value == value) return freezeEnum;
        }
        return null;
    }
}
