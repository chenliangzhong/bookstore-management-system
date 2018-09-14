package com.bookstore.enums;

public enum  UserSex {

    MAN(1,"男"),
    WOMAN(2,"女");

    private int code;
    private String name;

    private UserSex (int code, String name) {
        this.code = new Integer(code);
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static UserSex valueOf(int code) {
        for (UserSex userSex : UserSex.values())
            if (userSex.getCode() == code) return userSex;
        return null;
    }
}
