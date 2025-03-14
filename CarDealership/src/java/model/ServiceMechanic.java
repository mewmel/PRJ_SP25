/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;

/**
 *
 * @author ThinkPad
 */
public class ServiceMechanic implements Serializable{
    private String hours;
    private String comment;
    private String rate;
    private String serviceTicketID;
    private String serviceID;
    private String mechanicID;

    public ServiceMechanic() {
        hours = "";
        comment = "";
        rate = "";
        serviceTicketID = "";
        serviceID = "";
        mechanicID = "";
    }

    public ServiceMechanic(String hours, String comment, String rate, String serviceTicketID, String serviceID, String mechanicID) {
        this.hours = hours;
        this.comment = comment;
        this.rate = rate;
        this.serviceTicketID = serviceTicketID;
        this.serviceID = serviceID;
        this.mechanicID = mechanicID;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getServiceTicketID() {
        return serviceTicketID;
    }

    public void setServiceTicketID(String serviceTicketID) {
        this.serviceTicketID = serviceTicketID;
    }

    public String getServiceID() {
        return serviceID;
    }

    public void setServiceID(String serviceID) {
        this.serviceID = serviceID;
    }

    public String getMechanicID() {
        return mechanicID;
    }

    public void setMechanicID(String mechanicID) {
        this.mechanicID = mechanicID;
    }
}
