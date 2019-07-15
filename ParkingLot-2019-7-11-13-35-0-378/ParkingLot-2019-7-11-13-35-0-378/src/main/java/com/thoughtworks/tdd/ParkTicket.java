package com.thoughtworks.tdd;

import java.util.Objects;

public class ParkTicket {
    private boolean isUsed=false;
    private String carNumber;

    public ParkTicket() {
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public ParkTicket(String carNumber) {
        this.carNumber = carNumber;
    }

    public boolean isUsed() {
        return isUsed;
    }

    public void setUsed(boolean used) {
        isUsed = used;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ParkTicket)) return false;
        ParkTicket that = (ParkTicket) o;
        return isUsed() == that.isUsed() &&
                getCarNumber().equals(that.getCarNumber());
    }

    @Override
    public int hashCode() {
        return Objects.hash(isUsed(), getCarNumber());
    }
}
