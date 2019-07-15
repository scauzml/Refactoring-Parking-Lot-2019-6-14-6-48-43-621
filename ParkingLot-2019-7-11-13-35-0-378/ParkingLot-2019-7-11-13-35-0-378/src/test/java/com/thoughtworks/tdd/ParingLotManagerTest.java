package com.thoughtworks.tdd;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ParingLotManagerTest {
    @Test
    public void should_add_a_parking_boy_into_managementList_when_call_method() {
        //given
        ParkingLotManager parkingManger = new ParkingLotManager();
        ParkingBoy parkingBoy = new ParkingBoy();
        //when
        parkingManger.addParkingBogyIntoManagementList(parkingBoy);
        //then
        Assertions.assertTrue(parkingManger.getParkingBoys().contains(parkingBoy));

    }
    @Test
    public void should_specify_a_parking_boy_to_park_or_fetch_when_call_method() {
        //given
        ParkingLot parkingLot = new ParkingLot(10);
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot);
        ParkingLotManager parkingManger = new ParkingLotManager();
        String parkingBoyname="boy1";
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        parkingBoy.setName(parkingBoyname);
        parkingManger.getParkingBoys().add(parkingBoy);
        Car car = new Car("444");
        //when
       ParkTicket parkTicket= parkingManger.specifyBoyToPark(parkingBoyname,car);

        //then
        ParkTicket parkTicket1 = new ParkTicket(car.getCarNumber());

        Assertions.assertEquals(parkTicket1,parkTicket);

    }

    @Test
    public void should_return_a_ticket_when_parking_a_car_by_manger() {
        //given
        ParkingLot parkingLot=new ParkingLot(10);
        ParkingLot parkingLot1=new ParkingLot(10);
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot);
        parkingLots.add(parkingLot1);
        ParkingLotManager parkingLotManager = new ParkingLotManager(parkingLots);
        Car car = new Car("333");
        //when
        ParkTicket parkTicket=parkingLotManager.park(car);
        //then
        ParkTicket parkTicket1 = new ParkTicket();
        parkTicket1.setCarNumber(car.getCarNumber());
        Assertions.assertEquals(parkTicket1,parkTicket);
    }

    @Test
    public void should_return_right_car_when_get_a_correspond_ticket () {
        //given
        ParkingLot parkingLot=new ParkingLot(10);
        ParkingLot parkingLot1=new ParkingLot(10);
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot);
        parkingLots.add(parkingLot1);
        ParkingLotManager parkingLotManager = new ParkingLotManager(parkingLots);
        ParkTicket parkTicket=new ParkTicket();
        parkingLot.addParTicket(parkTicket);
        Car car = new Car("123");
        parkingLot.addCar(car);
        parkTicket.setCarNumber(car.getCarNumber());
        parkingLot.addParTicket(parkTicket);
        //when
        Car car1 = parkingLotManager.fetchRightCar(parkTicket);
        //then
        Assertions.assertEquals(car,car1);

    }

    @Test
    public void should_return_no_ticket_when_parkingLot_capacity_isNotEnough_by_specify_parkboy() {
        //given
        ParkingLot parkingLot=new ParkingLot(2);
        ParkingLot parkingLot1=new ParkingLot(2);
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot);
        parkingLots.add(parkingLot1);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        parkingBoy.setName("parkboy1");
        Car car = new Car();
        Car car1 = new Car();
        Car car2 = new Car();
        Car car11 = new Car();
        Car car22 = new Car();
        parkingLot.addCar(car1);
        parkingLot.addCar(car2);
        parkingLot1.addCar(car11);
        parkingLot1.addCar(car22);
        List<ParkingBoy> parkingBoyList = new ArrayList<>();
        parkingBoyList.add(parkingBoy);

        ParkingLotManager parkingLotManager=new ParkingLotManager(parkingBoyList,parkingLots);
        //when
        ParkTicket parkTicket=parkingLotManager.specifyBoyToPark("parkboy1",car);
        String message=parkingLotManager.getErrorMessage();
        //then
        Assertions.assertEquals(null,parkTicket);
        Assertions.assertEquals("Not enough position.",message);
    }

    @Test
    public void should_return_no_car_when_get_a_wrong_ticket () {
        //given
        ParkingLot parkingLot=new ParkingLot(10);
        ParkingLot parkingLot1=new ParkingLot(10);
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot);
        parkingLots.add(parkingLot1);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        ParkTicket parkTicket=new ParkTicket();
        List<ParkingBoy> parkingBoyList = new ArrayList<>();
        parkingBoy.setName("parkboy1");
        parkingBoyList.add(parkingBoy);
        Car car = new Car("55555");
        ParkingLotManager parkingLotManager=new ParkingLotManager(parkingBoyList,parkingLots);

        //when
        Car car1=parkingLotManager.specifyBoyTofetch("parkboy1",parkTicket);
        String errorMessage=parkingLotManager.getErrorMessage();
        //then
        Assertions.assertEquals(null,car1);
        Assertions.assertEquals("Unrecognized parking ticket.",errorMessage);

    }

    @Test
    public void should_return_no_car_when_get_a_wrong_ticket_is_null() {
        //given
        ParkingLot parkingLot=new ParkingLot(10);
        ParkingLot parkingLot1=new ParkingLot(10);
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot);
        parkingLots.add(parkingLot1);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        ParkTicket parkTicket=null;
        List<ParkingBoy> parkingBoyList = new ArrayList<>();
        parkingBoy.setName("parkboy1");
        parkingBoyList.add(parkingBoy);
        ParkingLotManager parkingLotManager=new ParkingLotManager(parkingBoyList,parkingLots);

        //when
        Car car1=parkingLotManager.specifyBoyTofetch("parkboy1",parkTicket);
        String errorMessage=parkingLotManager.getErrorMessage();
        //then
        Assertions.assertEquals(null,car1);
        Assertions.assertEquals("Please provide your parking ticket.",errorMessage);

    }
}
