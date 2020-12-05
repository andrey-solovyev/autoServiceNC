package Autoservice.Logics;


import Autoservice.Cars.Car;
import Autoservice.Damages.Damage;
import Autoservice.Master;
import Autoservice.Parking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Logic {
    private int masters;
    private int sizeParking;
    private int amountTowTruck;
    private Parking parking;
    private Queue<Damage> parkingQueue = new LinkedList<>();
    private Queue<Damage> damagesQueue = new LinkedList<>();
    private Queue<Damage> reparing = new LinkedList<>();

    public Logic(int masters, int sizeParking, int amountTowTruck) {
        this.masters = masters;
        this.sizeParking = sizeParking;
        this.amountTowTruck = amountTowTruck;
        this.parking = new Parking(sizeParking);
    }


    private ArrayList<Master> createMaster() {
        ArrayList<Master> mastersList = new ArrayList<>();
        for (int i = 0; i < masters; i++) {
            mastersList.add(new Master());
        }
        return mastersList;
    }

    public void startApplication() {
        generateCars();
        ArrayList<Master> masterList = createMaster();
        while (true) {
            for (Master master : masterList) {
                if (!master.isAlive() && master.getDamage() != null) {
                    System.out.println("Работа выполнена, идем в бд");
                    System.out.println(master.getDamage().getCost());
                    master.stop();
                }
                checkCarsForReparing();
                if (!master.isAlive()) {
                    master.setDamage(reparing.poll());
                }
            }
        }

    }

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

    private void generateCars() {
        new Thread() {
            @Override
            public void run() {
                GenerateCars generateCars = new GenerateCars();
                while (true) {
                    reparing.add(generateCars.createNewDameg());
                    synchronized (reparing) {
                        reparing.notify();
                    }
                    try {
                        Thread.sleep(generateCars.getRandomNumber(1, 10) * 60);
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }
            }
        }.start();
    }

    private boolean isParkingFree() {
        if (parkingQueue.size() >= getSizeParking()) {
            return false;
        }
        return true;
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
}
