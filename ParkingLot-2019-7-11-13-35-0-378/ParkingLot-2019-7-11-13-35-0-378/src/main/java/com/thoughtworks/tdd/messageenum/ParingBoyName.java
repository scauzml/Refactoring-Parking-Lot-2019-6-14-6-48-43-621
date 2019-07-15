package com.thoughtworks.tdd.messageenum;

public enum ParingBoyName {
    SMART_PARKING_BOY(0,"SMART PARKING BOY"),
    SUPER_SMART_PARKING_BOY(1,"SUPER SMART PARKING BOY");
    private int code;
    private String value;

    ParingBoyName(int code, String value) {
        this.code = code;
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
