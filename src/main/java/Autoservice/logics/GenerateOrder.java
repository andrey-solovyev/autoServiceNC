package Autoservice.logics;


import Autoservice.cars.Car;
import Autoservice.damages.BreakDown;
import Autoservice.orders.Order;

import java.util.LinkedList;
import java.util.Queue;
import java.util.UUID;

/**
 * Класс генерирование машин
 *
 * @autor Андрей Соловьем
 */
public class GenerateOrder {
    private Queue<Order> orderQueue;
    private final String[] brandCars = {"Volvo", "Mazda", "Audi", "BMW", "Ford"};

    public GenerateOrder() {
        this.orderQueue = new LinkedList<>();
    }

    /**
     * Все что относится к генерации машин
     */

    public void generateOrders() {
        new Thread() {
            @Override
            public void run() {
                GenerateOrder generateOrder = new GenerateOrder();
                while (true) {
                    getOrderQueue().add(new Order(createNewDameg(), createNewCar()));
                    synchronized (getOrderQueue()) {
                        getOrderQueue().notify();
                    }
                    try {
                        Thread.sleep(generateOrder.getRandomNumber(1, 30) * 60);
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }
            }
        }.start();
    }

    private Car createNewCar() {
        return new Car(brandCars[getRandomNumber(0, 4)], generateString(), getRandomNumber(1990, 2020));
    }

    private BreakDown createNewDameg() {
        return new BreakDown(getRandomNumber(1, 30), getRandomNumber(1, 50000));
    }

    private String generateString() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public Queue<Order> getOrderQueue() {
        return orderQueue;
    }

    public void setOrderQueue(Queue<Order> orderQueue) {
        this.orderQueue = orderQueue;
    }
}
