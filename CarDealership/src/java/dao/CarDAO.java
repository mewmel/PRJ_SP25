/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Car;
import mylib.DBUtils;

/**
 *
 * @author trant
 */
public class CarDAO {
    public Car getCar(String carId) {
        Car rs = null; 
        Connection cnn = null;
        
        try{
            cnn=DBUtils.getConnection();
                if (cnn != null) {
                    String sql = "SELECT [carID]\n" 
                                +"      ,[serialNumber]\n" 
                                +"      ,[model]\n" 
                                +"      ,[colour]\n" 
                                +"      ,[year]\n" 
                                +"  FROM [Car_Dealership].[dbo].[Cars]\n" 
                                +"  WHERE [carID]= ?";
                    PreparedStatement st = cnn.prepareStatement(sql);
                    st.setString(1, carId);
                    ResultSet table = st.executeQuery();
                    if (table != null) {
                        while (table.next()) {                            
                            carId = table.getString("carId");
                            String serialNumber = table.getString("serialNumber");
                            String model = table.getString("model");
                            int year = table.getInt("year");
                            String colour = table.getString("colour");
                            rs = new Car(carId, model, serialNumber, colour, year);
                        }
                    } 
                }
            } catch (Exception e) {
                e.printStackTrace();
            }finally{
                try {
                    if (cnn != null) cnn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }  
            }
        return rs;
    }          

    public ArrayList<Car> getCarsByModel(String carModel){
        ArrayList<Car> rs = new ArrayList();
        Connection cnn = null;
        
        try{
            cnn=DBUtils.getConnection();
            if(cnn != null){
                String sql = "SELECT [carID]\n"
                        + "      ,[serialNumber]\n"
                        + "      ,[model]\n"
                        + "      ,[colour]\n"
                        + "      ,[year]\n"
                        + "  FROM [Car_Dealership].[dbo].[Cars]\n"
                        + "  WHERE model like ?";
            PreparedStatement st = cnn.prepareStatement(sql);
            st.setString(1,"%"+ carModel +"%");
            ResultSet table = st.executeQuery();
                if(table != null){
                    while(table.next()){
                        String carId = table.getString("carID");
                        String serialNumber = table.getString("serialNumber");
                        String model = table.getString("model");
                        int year = table.getInt("year");
                        String colour = table.getString("colour");
                        Car c = new Car(carId, model, serialNumber, colour, year);
                        rs.add(c);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                if(cnn != null) cnn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return rs;
    }   

    public ArrayList<Car> getNewCars(String carModel){
        ArrayList<Car> rs = new ArrayList();       
        Connection cnn = null;
        
        try{
            cnn = DBUtils.getConnection();
            if(cnn != null){
                  String sql = "SELECT [carID]\n"
                            + "      ,[serialNumber]\n"
                            + "      ,[model]\n"
                            + "      ,[colour]\n"
                            + "      ,[year]\n"
                            + "  FROM [Car_Dealership].[dbo].[Cars]\n"
                            + "  WHERE model like ? and year=?";
            PreparedStatement st = cnn.prepareStatement(sql);
            st.setString(1,"%"+ carModel +"%");
            int currentYear = java.time.LocalDate.now().getYear();
            st.setInt(2, currentYear);
            ResultSet table = st.executeQuery();
                if(table != null){
                    while(table.next()){
                        String carId = table.getString("carID");
                        String serialNumber = table.getString("serialNumber");
                        String model = table.getString("model");
                        int year = table.getInt("year");
                        String colour = table.getString("colour");
                        Car c = new Car(carId, model, serialNumber, colour, year);
                        rs.add(c);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                if(cnn != null) cnn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return rs;
    }      
    

    public List<Car> getAllCars() {
        List<Car> cars = new ArrayList<>();
        Connection cnn = null;
        try {
            cnn = DBUtils.getConnection();
            if (cnn != null) {
                String sql = "SELECT * FROM [dbo].[Cars]";

                PreparedStatement st = cnn.prepareStatement(sql);
                ResultSet table = st.executeQuery();

                if (table != null) {
                    while (table.next()) {
                        String carId = table.getString("carID");
                        String serialNumber = table.getString("serialNumber");
                        String model = table.getString("model");
                        int year = table.getInt("year");
                        String colour = table.getString("colour");
                        Car c = new Car(carId, model, serialNumber, colour, year);
                        cars.add(c);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (cnn != null) {
                    cnn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return cars;
    }


}
