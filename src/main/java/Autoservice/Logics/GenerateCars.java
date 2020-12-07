package Autoservice.Logics;


import Autoservice.Cars.Car;
import Autoservice.Damages.Damage;

import java.nio.charset.Charset;
import java.util.Random;
import java.util.UUID;
/**
 * Класс генерирование машин
 * @autor Андрей Соловьем
 */
public class GenerateCars {

    public GenerateCars() {
    }

    /**
     * Все что относится к генерации машин
     */

    private String[] brandCars = {"Volvo", "Mazda", "Audi", "BMW", "Ford"};

    private Car createNewCar() {
        Car car = new Car(brandCars[getRandomNumber(0,4)], generateString(), getRandomNumber(1990, 2020));
        return car;
    }

    public Damage createNewDameg() {
        Damage damage = new Damage(getRandomNumber(1, 30), getRandomNumber(1, 50000), createNewCar());
        return damage;
    }

    private String generateString() {
        String uuid = UUID.randomUUID().toString();
        return uuid.replace("-","");
    }
    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
}
