package Autoservice.Databases;

import Autoservice.Damages.Damage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * Класс база данных
 * @autor Андрей Соловьем
 */
public class Database {
//    public void connectToDataBase(){
//        String url = "jdbc:postgresql://localhost/test";
//        Properties props = new Properties();
//        props.setProperty("user","fred");
//        props.setProperty("password","secret");
//        props.setProperty("ssl","true");
//        Connection conn = DriverManager.getConnection(url, props);
//    }

    public Database() {
    }

    private Connection getDBConnection() {
        Connection dbConnection = null;
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try {
            dbConnection = DriverManager.getConnection("jdbc:postgresql://localhost/check_number_car", "postgres","beautifulQ");
            return dbConnection;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return dbConnection;
    }
    public void insertReadyDamage(String master,Damage damage) {
        Connection dbConnection = null;
        Statement statement = null;

        String insertTableSQL = String.format("INSERT INTO Car_reparing (NAME_MASTER, BRAND_CAR, MODEL_CAR, CAR_YEAR,COST_REPAIR,TIME_FOR_REPARING,)VALUES %s,%s,%s, %s,%s,%d)",master,damage.getCar().getBrand(),damage.getCar().getModel(),damage.getCar().getYear(),damage.getCost(),damage.getTimeForRepairs());
        try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();

            // выполнить SQL запрос
            statement.execute(insertTableSQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }}