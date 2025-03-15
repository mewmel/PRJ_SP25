/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author ThinkPad
 */
public class CarCounting {
    private String carModel;
    private String total_sold;

    public CarCounting(String carModel, String total_sold) {
        this.carModel = carModel;
        this.total_sold = total_sold;
    }

    public CarCounting() {
    }
    
    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public String getTotal_sold() {
        return total_sold;
    }

    public void setTotal_sold(String total_sold) {
        this.total_sold = total_sold;
    }
    
    
    
}
