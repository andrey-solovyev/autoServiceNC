package Autoservice;

import Autoservice.Cars.Car;

public class Parking {
    private Car[] cars;
    private int lastIndex;

    public Parking(int size) {
        this.cars = new Car[size];
        this.lastIndex = 0;
    }


    public Car[] getCars() {
        return cars;
    }

    public void setCars(Car[] cars) {
        this.cars = cars;
    }

    public void setCar(int element,Car car){
        this.cars[element]=car;
    }
}
