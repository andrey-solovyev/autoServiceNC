package Autoservice;

import Autoservice.Damages.Damage;

import java.util.Comparator;
import java.util.TreeSet;


/**
 * Класс Мастер со своействами <b>name</b>, <b>damage</b>,<b>isBusy</b>.
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
    private Damage damage;
    /**
     * Поле проверки занят ли он
     */
    private boolean isBusy;

    public Master(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        if (getDamage() != null) {
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
        return getDamage().getTimeForRepairs();
    }

    public Damage getDamage() {
        return damage;
    }

    public void setDamage(Damage damage) {
        this.damage = damage;
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
