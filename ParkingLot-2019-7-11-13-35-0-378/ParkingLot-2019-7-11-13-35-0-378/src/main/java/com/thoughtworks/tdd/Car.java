package com.thoughtworks.tdd;

public class Car {
    private String carNumber;

    public Car(String carNumber) {
        this.carNumber = carNumber;
    }

    public Car() {
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }
}
