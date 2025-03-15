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
public class Mechanic {
    private String mechanicId;
    private String mechanicName;
    private int total_Services;

    public Mechanic(String mechanicId, String mechanicName, int total_Services) {
        this.mechanicId = mechanicId;
        this.mechanicName = mechanicName;
        this.total_Services = total_Services;
    }

    public Mechanic() {
        mechanicId = "";
        mechanicName = "";
        total_Services =0;
    }

    

    public String getMechanicId() {
        return mechanicId;
    }

    public void setMechanicId(String mechanicId) {
        this.mechanicId = mechanicId;
    }

    public String getMechanicName() {
        return mechanicName;
    }

    public void setMechanicName(String mechanicName) {
        this.mechanicName = mechanicName;
    }

    public int getTotal_Services() {
        return total_Services;
        
    }

    public void setTotal_Services(int total_Services) {
        this.total_Services = total_Services;
    }
    
    
}
