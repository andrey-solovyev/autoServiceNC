package Autoservice.logics;


import Autoservice.orders.Order;
import Autoservice.parking.Parking;

import java.util.ArrayList;

/**
 * Класс Логика со своействами <b>masters</b>, <b>sizeParking</b>,<b>amountTowTruck</b>(то что передаем).
 * @autor Андрей Соловьем
 */
public class ServiceBuilding {
    private int masters;
    private int amountTowTruck;
    private Parking parking;
    private GenerateOrder generateOrder;
    private OrderDistributor orderDistributor;


    public ServiceBuilding(int masters, int sizeParking, int amountTowTruck) {
        this.masters = masters;
        this.amountTowTruck = amountTowTruck;
        this.parking = new Parking(sizeParking);
        this.generateOrder=new GenerateOrder();
        this.orderDistributor = new OrderDistributor(parking,generateOrder,amountTowTruck);
    }

    /**
     * Функция генерации мастеров
     *
     * @return лист мастеров
     */
    private ArrayList<Master> createMaster() {
        ArrayList<Master> mastersList = new ArrayList<>();
        for (int i = 0; i < masters; i++) {
            mastersList.add(new Master("Master:" + i));
        }
        return mastersList;
    }


    private synchronized void writeIntoDataBase(Order order, String master) {
        parking.deleteOrder();
        //getDatabase().insertReadyDamage(master, damage);
    }

    /**
     * Главная функция приложения
     */
    public void startApplication() {
        generateOrder.generateOrders();
        orderDistributor.distibutor();
        ArrayList<Master> masterList = createMaster();
        while (true) {
            for (Master master : masterList) {
                if (!master.isBusy() && master.getOrder() != null) {
                    writeIntoDataBase(master.getOrder(),master.getName());
                    System.out.println(String.format("Работа выполнена мастером:%s\nМашина отремантирована %s %s", master.getName(), master.getOrder().getCar().getBrand(), master.getOrder().getCar().getModel()));
                }
                if (!master.isBusy()) {
                    parking.checkCarsForReparing();
                    master.setOrder(parking.getParkingQueue().poll());
                    System.out.println(String.format("%s взял в работу машину %s %s", master.getName(), master.getOrder().getCar().getBrand(), master.getOrder().getCar().getModel()));
                    new Thread(master).start();
                }
            }
        }

    }



    /**
     * Функция генерирования поломок запущенная в новом треде
     */
//
//
//    private boolean isParkingFree() {
//        if (parking.getDamages().length >= reparing.size() && parking.isParkFree()) {
//            return true;
//        }
//        return false;
//    }
//
//
//    public int getMasters() {
//        return masters;
//    }
//
//    public void setMasters(int masters) {
//        this.masters = masters;
//    }
//
//    public int getSizeParking() {
//        return sizeParking;
//    }
//
//    public void setSizeParking(int sizeParking) {
//        this.sizeParking = sizeParking;
//    }
//
//    public int getAmountTowTruck() {
//        return amountTowTruck;
//    }
//
//    public void setAmountTowTruck(int amountTowTruck) {
//        this.amountTowTruck = amountTowTruck;
//    }
//
//    public Database getDatabase() {
//        return database;
//    }
}

