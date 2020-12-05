package Autoservice.Logics;


import Autoservice.Cars.Car;
import Autoservice.Damages.Damage;

import java.nio.charset.Charset;
import java.util.Random;

public class GenerateCars {
    public Car createNewCar(){
        byte[] array = new byte[7]; // length is bounded by 7
        new Random().nextBytes(array);
        String generatedString = new String(array, Charset.forName("UTF-8"));
        Car car=new Car( new String(array, Charset.forName("UTF-8")), new String(array, Charset.forName("UTF-8")), getRandomNumber(1990,2020));
        return car;
    }
    public Damage createNewDameg(){
        Damage damage=new Damage(getRandomNumber(1,30),getRandomNumber(1,50000),createNewCar());
        return damage;
    }

    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
}
