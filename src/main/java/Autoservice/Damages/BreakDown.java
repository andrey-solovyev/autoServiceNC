package Autoservice.Damages;


import Autoservice.Cars.Car;
/**
 * Класс поломка <b>timeForRepairs</b>, <b>cost</b>,<b>car</b>.
 * @autor Андрей Соловьем
 */
public class BreakDown {
    private int timeForRepairs;
    private double cost;

    public BreakDown(int timeForRepairs, double cost) {
        this.timeForRepairs = timeForRepairs;
        this.cost = cost;
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
}
