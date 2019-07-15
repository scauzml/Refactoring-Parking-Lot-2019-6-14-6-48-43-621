package com.thoughtworks.tdd;

import com.sun.deploy.util.StringUtils;
import com.thoughtworks.tdd.messageenum.ErrorMessage;
import com.thoughtworks.tdd.messageenum.ParingBoyName;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ParkingBoy {
     private List<ParkingLot> parkingLotList;
     private String errorMessage;
     private String name;


    public ParkingBoy(List<ParkingLot> parkingLotList, String name) {
        this.parkingLotList = parkingLotList;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ParkingLot> getParkingLotList() {
        return parkingLotList;
    }

    public void setParkingLotList(List<ParkingLot> parkingLotList) {
        this.parkingLotList = parkingLotList;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public ParkingBoy(List<ParkingLot> parkingLotList) {
        this.parkingLotList = parkingLotList;
    }

    public ParkingBoy() {
    }

    public ParkTicket park(Car car) {

        ParkTicket parkTicket =null;

        if (car != null) {
            //查找car是否已经停过

            boolean isParkedCar = false;
            for (ParkingLot e:this.parkingLotList
                 ) {
                isParkedCar= e.isContainCar(car);

            }
            if (!isParkedCar) {
                ParkingLot currentParingLot=null;
                boolean isCapacityEnough = false;
                for (ParkingLot e1:this.parkingLotList
                    ) {
                        isCapacityEnough=e1.isCapacityEnough();
                        if (isCapacityEnough) {
                            currentParingLot=e1;
                            break;
                        }
                    }

                if (isCapacityEnough) {
                    if (this.name == ParingBoyName.SMART_PARKING_BOY.getValue()) {
                        currentParingLot = findMoreEmptyOptionParkingLOt();
                    }else if(this.name == ParingBoyName.SUPER_SMART_PARKING_BOY.getValue()){
                        currentParingLot=findLargerPositonRatePraringLot();
                    }
                    parkTicket=new ParkTicket();
                    //关联ticket,与car,而且停车场添加ticket
                    parkTicket.setCarNumber(car.getCarNumber());
                    currentParingLot.addParTicket(parkTicket);
                    currentParingLot.addCar(car);
                }else {
                    this.errorMessage=ErrorMessage.NOT_ENOUGH_CAPACITY_MESSAGE.getValue();
                }


            }

        }


        return parkTicket;
    }

    public List<ParkTicket> parkCarList(List<Car> carList) {
        List<ParkTicket> list = new ArrayList<>();
        carList.forEach(e->{
            ParkTicket parkTicket = this.park(e);
            list.add(parkTicket);
        });
        return list;
    }

    public Car fetchRightCar(ParkTicket parkTicket) {
        Car car=null;
        //验证pakTicket是wrong
        if (parkTicket != null) {
            boolean isRightTicket=false;
            ParkingLot currentParkingLot=null;
            for (ParkingLot p :this.parkingLotList) {
                isRightTicket=p.isContainParkTicket(parkTicket);
                if (isRightTicket) {
                    currentParkingLot=p;
                    break;
                }
            }

            if (isRightTicket) {
                if (!parkTicket.isUsed()) {
                    //没有被使用则获取正确car

                   car= currentParkingLot.getCars().stream().
                           filter(e->e.getCarNumber()==parkTicket.getCarNumber()).findFirst().get();
                   currentParkingLot.getCars().remove(car);
                  parkTicket.setUsed(true);

                }else {
                    this.errorMessage= ErrorMessage.WRONG_TICKET_MESSAGE.getValue();
                }
            }else {
                this.errorMessage= ErrorMessage.WRONG_TICKET_MESSAGE.getValue();
            }

        }else {
            this.errorMessage=ErrorMessage.NOT_PROVIDE_TOKET_MESSAGE.getValue();
        }

        return car;
    }

    public ParkingLot findMoreEmptyOptionParkingLOt() {

        return this.parkingLotList.stream().max(Comparator.comparingInt(ParkingLot::getAllowance)).get();

    }

    public ParkingLot findLargerPositonRatePraringLot() {

        return this.parkingLotList.stream().max(Comparator.comparingDouble(ParkingLot::getPositionrate)).get();
    }

}
