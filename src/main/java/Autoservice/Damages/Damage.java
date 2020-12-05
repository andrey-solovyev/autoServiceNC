package Autoservice.Damages;


import Autoservice.Cars.Car;

public class Damage {
    private int timeForRepairs;
    private double cost;
    private Car car;

    public Damage(int timeForRepairs, double cost, Car car) {
        this.timeForRepairs = timeForRepairs;
        this.cost = cost;
        this.car = car;
    }

    public int getTimeForRepairs() {
        return timeForRepairs;
    }

    public void setTimeForRepairs(int timeForRepairs) {
        this.timeForRepairs = timeForRepairs;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}
