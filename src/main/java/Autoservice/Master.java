package Autoservice;

import Autoservice.Damages.Damage;

import java.util.Comparator;
import java.util.TreeSet;

public class Master extends Thread {
    private Damage damage;

    public Master() {
    }

    @Override
    public void run() {
        if (getDamage() != null) {
            try {
                Thread.sleep(60 * getTimeToWork());
            } catch (Exception e) {
                System.out.println(e);
            }
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
}
