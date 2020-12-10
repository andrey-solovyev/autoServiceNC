package Autoservice;

import Autoservice.Logics.ServiceBuilding;

import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите количество мастеров: ");
        int amountMasters = scanner.nextInt();
        System.out.println("Введите размер гаража: ");

        int sizeParking = scanner.nextInt();
        System.out.println("Введите количество эвакуаторов: ");
        int amountTowTruck = scanner.nextInt();
        ServiceBuilding serviceBuilding = new ServiceBuilding(amountMasters, sizeParking, amountTowTruck);
        serviceBuilding.startApplication();
        // write your code here
    }
}
