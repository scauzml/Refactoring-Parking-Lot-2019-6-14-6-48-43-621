package com.thoughtworks.tdd.messageenum;

public enum ErrorMessage {
    WRONG_TICKET_MESSAGE(0,"Unrecognized parking ticket."),
    NOT_PROVIDE_TOKET_MESSAGE(1, "Please provide your parking ticket."),
    NOT_ENOUGH_CAPACITY_MESSAGE(2,"Not enough position.");
        private Integer num;
        private String value;

    ErrorMessage(Integer num, String value) {
        this.num = num;
        this.value = value;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
