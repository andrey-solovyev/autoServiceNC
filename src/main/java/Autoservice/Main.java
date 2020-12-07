package Autoservice;

import Autoservice.Databases.Database;
import Autoservice.Logics.Logic;
import com.sun.nio.sctp.SctpStandardSocketOptions;

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
        Logic logic = new Logic(amountMasters, sizeParking, amountTowTruck);
        logic.startApplication();
        // write your code here
    }
}
