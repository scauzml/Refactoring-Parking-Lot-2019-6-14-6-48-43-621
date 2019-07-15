package com.thoughtworks.tdd;

import com.thoughtworks.tdd.messageenum.ErrorMessage;

import java.util.ArrayList;
import java.util.List;

public class ParkingLotManager {
    private List<ParkingBoy> parkingBoys = new ArrayList<ParkingBoy>();
    private List<ParkingLot> parkingLotList=new ArrayList<ParkingLot>();
   private  String errorMessage;


    public ParkingLotManager() {
    }

    public ParkingLotManager(List<ParkingLot> parkingLotList) {
        this.parkingLotList = parkingLotList;
    }

    public ParkingLotManager(List<ParkingBoy> parkingBoyList, List<ParkingLot> parkingLots) {
        this.parkingBoys=parkingBoyList;
        this.parkingLotList=parkingLots;
    }

    public List<ParkingLot> getParkingLotList() {
        return parkingLotList;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public void setParkingLotList(List<ParkingLot> parkingLotList) {
        this.parkingLotList = parkingLotList;
    }

    public List<ParkingBoy> getParkingBoys() {
        return parkingBoys;
    }

    public void setParkingBoys(List<ParkingBoy> parkingBoys) {
        this.parkingBoys = parkingBoys;
    }

    public void addParkingBogyIntoManagementList(ParkingBoy boy) {
        parkingBoys.add(boy);
    }


    public ParkTicket specifyBoyToPark(String parkingBoyname, Car car) {
        ParkingBoy parkingBoy=this.getParkingBoys().stream().filter(e->e.getName()==parkingBoyname).map(e->(e)).findFirst().get();
        if (parkingBoy == null) {
            return null;
        }
        ParkTicket parkTicket = parkingBoy.park(car);

        if (parkTicket!=null) {
            return parkTicket;
        }else {
            this.errorMessage=parkingBoy.getErrorMessage();
            return null;
        }
    }
    public Car specifyBoyTofetch(String parkingBoyname, ParkTicket parkTicket) {
        ParkingBoy parkingBoy=this.getParkingBoys().stream().filter(e->e.getName()==parkingBoyname).map(e->(e)).findFirst().get();
        if (parkingBoy == null) {
            return null;
        }
        Car car = parkingBoy.fetchRightCar(parkTicket);

        if (car != null) {
            return car;
        }else {
            this.errorMessage=parkingBoy.getErrorMessage();
            return car;
        }
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
//                    if (this.name == ParingBoyName.SMART_PARKING_BOY.getValue()) {
//                        currentParingLot = findMoreEmptyOptionParkingLOt();
//                    }else if(this.name == ParingBoyName.SUPER_SMART_PARKING_BOY.getValue()){
//                        currentParingLot=findLargerPositonRatePraringLot();
//                    }
                    parkTicket=new ParkTicket();
                    //关联ticket,与car,而且停车场添加ticket
                    parkTicket.setCarNumber(car.getCarNumber());
                    currentParingLot.addParTicket(parkTicket);
                    currentParingLot.addCar(car);
                }else {
                    this.errorMessage= ErrorMessage.NOT_ENOUGH_CAPACITY_MESSAGE.getValue();
                }


            }

        }


        return parkTicket;
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
}
