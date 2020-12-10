package Autoservice.logics;


import Autoservice.orders.Order;

/**
 * Класс Мастер со своействами <b>name</b>, <b>order</b>,<b>isBusy</b>.
 * @autor Андрей Соловьем
 */
public class Master implements Runnable {
    /**
     * Поле имя мастера
     */
    private String name;

    /**
     * Поле поломка отданная текущему мастеру
     */
    private Order order;
    /**
     * Поле проверки занят ли он
     */
    private boolean isBusy;

    public Master(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        if (getOrder() != null) {
            try {
                setBusy(true);
                Thread.sleep(60 * getTimeToWork());
            } catch (Exception e) {
                System.out.println(e);
            }
            setBusy(false);
        }
    }

    private int getTimeToWork() {
        return getOrder().getDamage().getTimeForRepairs();
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public boolean isBusy() {
        return isBusy;
    }

    public void setBusy(boolean busy) {
        isBusy = busy;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
