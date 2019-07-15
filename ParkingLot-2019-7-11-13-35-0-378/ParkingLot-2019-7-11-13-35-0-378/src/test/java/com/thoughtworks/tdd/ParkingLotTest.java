package com.thoughtworks.tdd;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ParkingLotTest {

    @Test
    public void should_return_true_when_add_a_car() {
        //given
        ParkingLot parlingLot=new ParkingLot();
        Car car = new Car();
        //when
        boolean isSuccess=parlingLot.addCar(car);
        //then
        Assertions.assertEquals(true,isSuccess);

    }

    @Test
    public void should_return_true_when_capacity_is_enough() {
        //given
        ParkingLot parlingLot=new ParkingLot(10);
        //when
        boolean isSuccess=parlingLot.isCapacityEnough();
        //then
        Assertions.assertEquals(true,isSuccess);
    }

    @Test
    public void should_return_true_when_ticket_in_parkingLot() {
        //given
        ParkingLot parkingLot=new ParkingLot();
        ParkTicket parkTicket=new ParkTicket();
        parkingLot.addParTicket(parkTicket);

        //when
        boolean isSuccess=parkingLot.isContainParkTicket(parkTicket);
        //then
        Assertions.assertEquals(true,isSuccess);
    }
    @Test
    public void should_return_false_when_ticket_notin_parkingLot() {
        //given
        ParkingLot parkingLot=new ParkingLot();
        ParkTicket parkTicket=new ParkTicket();


        //when
        boolean isSuccess=parkingLot.isContainParkTicket(parkTicket);
        //then
        Assertions.assertEquals(false,isSuccess);
    }
}
