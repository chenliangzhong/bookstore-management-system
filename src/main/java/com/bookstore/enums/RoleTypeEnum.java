package com.bookstore.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@JsonIgnoreProperties(value = {"declaring_class"})
public enum  RoleTypeEnum {

    ADMIN(1, RoleType.ADMIN),
    USER(2,RoleType.USER);

    private final int value;
    private final String desc;

    RoleTypeEnum(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public int getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }

    public static RoleTypeEnum valueOf(int value) {
        for (RoleTypeEnum roleTypeEnum : RoleTypeEnum.values()) {
            if (roleTypeEnum.value == value) return roleTypeEnum;
        }
        return null;
    }
}
