package Autoservice.orders;

import Autoservice.cars.Car;
import Autoservice.damages.BreakDown;

public class Order {
    private BreakDown damage;
    private Car car;

    public Order(BreakDown damage, Car car) {
        this.damage = damage;
        this.car = car;
    }

    public BreakDown getDamage() {
        return damage;
    }

    public void setDamage(BreakDown damage) {
        this.damage = damage;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}
