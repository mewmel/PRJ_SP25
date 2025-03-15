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
import java.util.ArrayList;
import java.util.List;
import model.Car;
import model.CarCounting;
import mylib.DBUtils;

/**
 *
 * @author trant
 */
public class CarDAO {

    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
     */
    public Car getCar(String carId) {
        Car rs = null;
        Connection cnn = null;

        try {
            cnn = DBUtils.getConnection();
            if (cnn != null) {
                String sql = "SELECT [carID]\n"
                        + "      ,[model]\n"
                        + "      ,[serialNumber]\n"
                        + "      ,[colour]\n"
                        + "      ,[year]\n"
                        + "      ,[statusName]\n"
                        + "  FROM [Car_Dealership].[dbo].[Cars]\n"
                        + "  WHERE [carID]= ?";
                PreparedStatement st = cnn.prepareStatement(sql);
                st.setString(1, carId);
                ResultSet table = st.executeQuery();
                if (table != null) {
                    while (table.next()) {
                        carId = table.getString("carId");
                        String model = table.getString("model");
                        String serialNumber = table.getString("serialNumber");
                        String colour = table.getString("colour");
                        int year = table.getInt("year");
                        String staName = table.getString("statusName");
                        rs = new Car(carId, model, serialNumber, colour, year, staName);
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
        return rs;
    }

    public ArrayList<Car> getCarsByModel(String carModel) {
        ArrayList<Car> rs = new ArrayList();
        Connection cnn = null;

        try {
            cnn = DBUtils.getConnection();
            if (cnn != null) {
                String sql = "SELECT [carID]\n"
                        + "      ,[model]\n"
                        + "      ,[serialNumber]\n"
                        + "      ,[colour]\n"
                        + "      ,[year]\n"
                        + "      ,[statusName]\n"
                        + "  FROM [Car_Dealership].[dbo].[Cars]\n"
                        + "  WHERE model like ?";
                PreparedStatement st = cnn.prepareStatement(sql);
                st.setString(1, "%" + carModel + "%");
                ResultSet table = st.executeQuery();
                if (table != null) {
                    while (table.next()) {
                        String carId = table.getString("carID");
                        String model = table.getString("model");
                        String serialNumber = table.getString("serialNumber");
                        String colour = table.getString("colour");
                        int year = table.getInt("year");
                        String staName = table.getString("statusName");
                        Car c = new Car(carId, model, serialNumber, colour, year, staName);
                        rs.add(c);
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
        return rs;
    }

    public Car getCarById(String carId) {
        Connection cnn = null;
        Car rs = null;

        try {
            cnn = DBUtils.getConnection();
            if (cnn != null) {
                String sql = "SELECT carID, model, serialNumber, colour, year, statusName "
                        + "FROM Cars WHERE carID = ?";
                PreparedStatement st = cnn.prepareStatement(sql);
                st.setString(1, carId);

                ResultSet table = st.executeQuery();
                if (table.next()) {
                    String model = table.getString("model");
                    String serialNumber = table.getString("serialNumber");
                    String colour = table.getString("colour");
                    int year = table.getInt("year");
                    String statusName = table.getString("statusName");

                    rs = new Car(carId, model, serialNumber, colour, year, statusName);
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
        return rs;
    }

    public ArrayList<CarCounting> getBestSellingCarModels() {
        Connection cnn = null;
        ArrayList<CarCounting> rs = new ArrayList<>();

        try {
            cnn = DBUtils.getConnection();
            if (cnn != null) {
                String sql = "SELECT C.model, COUNT(S.carID) AS total_sold \n" +
"                     FROM SalesInvoice S \n" +
"                     JOIN Cars C ON S.carID = C.carID \n" +
"                     GROUP BY C.model\n" +
"                     ORDER BY total_sold DESC";
                PreparedStatement st = cnn.prepareStatement(sql);

                ResultSet table = st.executeQuery();
                while (table.next()) {
                    String carModel = table.getString("model");
                    String total_sold = String.valueOf(table.getInt("total_sold"));

                    rs.add(new CarCounting(carModel, total_sold));
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
        return rs;
    }

    public ArrayList<Car> getAllCars() {
        ArrayList<Car> rs = new ArrayList();
        Connection cnn = null;

        try {
            cnn = DBUtils.getConnection();
            if (cnn != null) {
                String sql = "SELECT [carID]\n"
                        + "      ,[model]\n"
                        + "      ,[serialNumber]\n"
                        + "      ,[colour]\n"
                        + "      ,[year]\n"
                        + "      ,[statusName]\n"
                        + "  FROM [Car_Dealership].[dbo].[Cars]\n";
                PreparedStatement st = cnn.prepareStatement(sql);
                ResultSet table = st.executeQuery();
                if (table != null) {
                    while (table.next()) {
                        String carId = table.getString("carID");
                        String model = table.getString("model");
                        String serialNumber = table.getString("serialNumber");
                        String colour = table.getString("colour");
                        int year = table.getInt("year");
                        String sta = table.getString("statusName");
                        Car c = new Car(carId, model, serialNumber, colour, year, sta);
                        rs.add(c);
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
        return rs;
    }

    public void updateCar(String carId, String statusName) {
        Connection cnn = null;
        //nhớ check giá tri nhap vao cua status name
        try {
            cnn = DBUtils.getConnection();
            if (cnn != null) {
                String sql = "UPDATE [Car_Dealership].[dbo].[Cars] \n"
                        + "SET [statusName] = ?\n"
                        + "WHERE [carID]= ?";
                PreparedStatement st = cnn.prepareStatement(sql);
                st.setString(1, statusName);
                st.setString(2, carId);

                st.executeUpdate();
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
    }

    public void deleteCar(String carId) {
        Connection cnn = null;
        //nhớ check giá tri nhap vao cua status name
        try {
            cnn = DBUtils.getConnection();
            if (cnn != null) {
                String sql = "DELETE FROM [Car_Dealership].[dbo].[Cars]\n"
                        + "WHERE [carID]= ?";
                PreparedStatement st = cnn.prepareStatement(sql);
                st.setString(1, carId);
                st.executeUpdate();

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
    }
    public void addCar(Car car) {
        Connection cnn = null;
        try {
            cnn = DBUtils.getConnection();
            String sql = "INSERT INTO Cars (carID, model, serialNumber, colour, year, statusName) VALUES (?, ?, ?, ?, ?, ?)";
                
            PreparedStatement st = cnn.prepareStatement(sql);

            st.setString(1, car.getCarId());
            st.setString(2, car.getModel());
            st.setString(3, car.getSerialNumber());
            st.setString(4, car.getColour());
            st.setInt(5, car.getYear());
            st.setString(6, car.getStatusName());

            st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                if(cnn != null) cnn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
