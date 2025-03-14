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

    public Mechanic() {
        mechanicId = "";
        mechanicName = "";
    }

    public Mechanic(String mechanicId, String mechanicName) {
        this.mechanicId = mechanicId;
        this.mechanicName = mechanicName;
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
}
