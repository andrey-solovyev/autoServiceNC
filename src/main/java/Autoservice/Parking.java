package Autoservice;

import Autoservice.Damages.BreakDown;
import Autoservice.Orders.Order;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Класс Парковка(гараж) со своействами <b>Damages</b>, <b>nowSize</b>.
 * @autor Андрей Соловьем
 */
public class Parking {
    private int size;
    private Queue<Order> parkingQueue;
    /**
     * Отвечает за то,чтобы когда мастер брал машину, новые не въезжали на парковку
     */
    private int nowSize;
    /**
     * Идея была в том, чтобы в массив из очереди кидать машину на парковочное место.
     */
    public Parking(int size) {
        this.size=size;
        this.parkingQueue = new LinkedList<>();
        this.nowSize=0;
    }

    public void checkCarsForReparing(){
        if (getParkingQueue()!=null){
            synchronized (getParkingQueue()){
                try {
                    getParkingQueue().wait();
                } catch (InterruptedException e){
                    System.out.println(e);
                }
            }
        }
    }
    public boolean isParkFree() {
        return nowSize<size;
    }

    public void addNewOrder() {
        this.nowSize++;
    }
    public void deleteOrder(){
        this.nowSize--;
    }

    public Queue<Order> getParkingQueue() {
        return parkingQueue;
    }
    public void addNewOrderInParking(Order order){
        getParkingQueue().add(order);
    }
    public void setParkingQueue(Queue<Order> parkingQueue) {
        this.parkingQueue = parkingQueue;
    }
}
