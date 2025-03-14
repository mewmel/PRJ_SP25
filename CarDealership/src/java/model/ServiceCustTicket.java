/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;

/**
 *co van de
 * @author trant 
 */
public class ServiceCustTicket implements Serializable{
    private String id;
    private String dateReceived;
    private String dateReturn;
    private String cusID;
    private String carID;

    public ServiceCustTicket() {
    }

    public ServiceCustTicket(String id, String dateReceived, String dateReturn, String cusID, String carID) {
        this.id = id;
        this.dateReceived = dateReceived;
        this.dateReturn = dateReturn;
        this.cusID = cusID;
        this.carID = carID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDateReceived() {
        return dateReceived;
    }

    public void setDateReceived(String dateReceived) {
        this.dateReceived = dateReceived;
    }

    public String getDateReturn() {
        return dateReturn;
    }

    public void setDateReturn(String dateReturn) {
        this.dateReturn = dateReturn;
    }

    public String getCusID() {
        return cusID;
    }

    public void setCusID(String cusID) {
        this.cusID = cusID;
    }

    public String getCarID() {
        return carID;
    }

    public void setCarID(String carID) {
        this.carID = carID;
    }
   
}
