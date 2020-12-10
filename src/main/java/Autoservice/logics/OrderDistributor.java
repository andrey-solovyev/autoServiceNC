package Autoservice.logics;

import Autoservice.orders.Order;
import Autoservice.parking.Parking;
import java.util.LinkedList;
import java.util.Queue;

public class OrderDistributor {

    private Parking parking;
    private GenerateOrder generateOrder;
    private int amountTowTruck;
    private Queue<Order> deliveryQueue;
    public OrderDistributor(Parking parking, GenerateOrder generateOrder, int amountTowTruck) {
        this.parking = parking;
        this.generateOrder = generateOrder;
        this.amountTowTruck = amountTowTruck;
        this.deliveryQueue = new LinkedList<>();
    }

    public void distibutor() {
        new Thread() {

            @Override
            public void run() {
                while (true) {
                    try {
                        synchronized (parking.getParkingQueue()) {
                            parking.getParkingQueue().notify();
                        }
                        synchronized (generateOrder.getOrderQueue()) {
                            generateOrder.getOrderQueue().wait();
                            pickUpTheCar();
                        }
                    }catch (Exception e){
                        System.out.println(e);
                    }
                }
            }
        }.start();
    }
//    /**
//     * Функция для проверки, что есть новая машина в гараже
//     */
//    private void checkCarsForReparing() {
//        while (reparing.peek() == null) {
//            synchronized (reparing) {
//                try {
//                    reparing.wait();
//                } catch (InterruptedException e) {
//                }
//            }
//        }
//    }

    /**
     * Функция для для работы с очередями и "доставки" машин
     */
    private void pickUpTheCar() {
        if (generateOrder.getOrderQueue().size() < getAmountTowTruck()) {
            deliveryQueue.add(generateOrder.getOrderQueue().poll());
        }
        if (parking.isParkFree()) {
            parking.addNewOrderInParking(deliveryQueue.poll());
            parking.addNewOrder();
            synchronized (parking.getParkingQueue()) {
                parking.getParkingQueue().notify();
            }
        }

    }

    public int getAmountTowTruck() {
        return amountTowTruck;
    }

    public void setAmountTowTruck(int amountTowTruck) {
        this.amountTowTruck = amountTowTruck;
    }
}
