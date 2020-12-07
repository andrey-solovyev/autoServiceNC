package Autoservice.Logics;


import Autoservice.Cars.Car;
import Autoservice.Damages.Damage;
import Autoservice.Databases.Database;
import Autoservice.Master;
import Autoservice.Parking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Класс Логика со своействами <b>masters</b>, <b>sizeParking</b>,<b>amountTowTruck</b>(то что передаем).
 * @autor Андрей Соловьем
 */
public class Logic {
    private int masters;
    private int sizeParking;
    private int amountTowTruck;
    private Parking parking;
    private Database database;
    private Queue<Damage> deliveryQueue;
    private Queue<Damage> damagesQueue;
    private Queue<Damage> reparing;

    public Logic(int masters, int sizeParking, int amountTowTruck) {
        this.masters = masters;
        this.sizeParking = sizeParking;
        this.amountTowTruck = amountTowTruck;
        this.parking = new Parking(sizeParking);
        this.deliveryQueue = new LinkedList<>();
        this.damagesQueue = new LinkedList<>();
        this.reparing = new LinkedList<>();
        this.database = new Database();
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


    private void writeIntoDataBase(Damage damage, String master) {
        parking.deleteDamage();
        getDatabase().insertReadyDamage(master, damage);
    }

    /**
     * Главная функция приложения
     */
    public void startApplication() {
        generateCars();
        ArrayList<Master> masterList = createMaster();
        while (true) {
            for (Master master : masterList) {
                if (!master.isBusy() && master.getDamage() != null) {
                    writeIntoDataBase(master.getDamage(),master.getName());
                    System.out.println(String.format("Работа выполнена мастером:%s\nМашина отремантирована %s %s", master.getName(), master.getDamage().getCar().getBrand(), master.getDamage().getCar().getModel()));
                }
                if (!master.isBusy()) {
                    checkCarsForReparing();
                    master.setDamage(reparing.poll());
                    System.out.println(String.format("%s взял в работу машину %s %s", master.getName(), master.getDamage().getCar().getBrand(), master.getDamage().getCar().getModel()));
                    new Thread(master).start();
                }
            }
        }

    }

    /**
     * Функция для проверки, что есть новая машина в гараже
     */
    private void checkCarsForReparing() {
        while (reparing.peek() == null) {
            synchronized (reparing) {
                try {
                    reparing.wait();
                } catch (InterruptedException e) {
                }
            }
        }
    }

    /**
     * Функция для для работы с очередями и "доставки" машин
     */
    private void pickUpTheCar() {
        if (deliveryQueue.size() < getAmountTowTruck()) {
            deliveryQueue.add(damagesQueue.poll());
        }
        if (isParkingFree()) {
            reparing.add(deliveryQueue.poll());
            parking.addNewDamage();
            synchronized (reparing) {
                reparing.notify();
            }
        }

    }

    /**
     * Функция генерирования поломок запущенная в новом треде
     */
    private void generateCars() {
        new Thread() {
            @Override
            public void run() {
                GenerateCars generateCars = new GenerateCars();
                while (true) {
                    damagesQueue.add(generateCars.createNewDameg());
                    pickUpTheCar();
                    try {
                        Thread.sleep(generateCars.getRandomNumber(1, 30) * 60);
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }
            }
        }.start();
    }

    private boolean isParkingFree() {
        if (parking.getDamages().length >= reparing.size() && parking.isParkFree()) {
            return true;
        }
        return false;
    }


    public int getMasters() {
        return masters;
    }

    public void setMasters(int masters) {
        this.masters = masters;
    }

    public int getSizeParking() {
        return sizeParking;
    }

    public void setSizeParking(int sizeParking) {
        this.sizeParking = sizeParking;
    }

    public int getAmountTowTruck() {
        return amountTowTruck;
    }

    public void setAmountTowTruck(int amountTowTruck) {
        this.amountTowTruck = amountTowTruck;
    }

    public Database getDatabase() {
        return database;
    }
}

