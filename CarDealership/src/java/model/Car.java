/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author trant
 */
public class Car {
    private String  carId;
    private String model;
    private String serialNumber;
    private String colour;
    private int year;
    private String statusName;

    public Car() {
        carId ="";
        model ="";
        serialNumber ="";
        colour ="";
        year = 0;
        statusName ="";
    }

    public Car(String carId, String model, String serialNumber, String colour, int year, String statusName) {
        this.carId = carId;
        this.model = model;
        this.serialNumber = serialNumber;
        this.colour = colour;
        this.year = year;
        this.statusName = statusName;

    }

   

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

}
    

       
