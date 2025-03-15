/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *dung hien thi cho nguoi dung chi tiet ve dich vu 
 * @author trant
 */
public class ServiceTicketDetail {
    private String seDetailId;
    private String serviceName;
    private String carName;
    private String dateReceive;
    private String dateReturn;
    private int hours;
    private String comment;
    private String rate;

    public ServiceTicketDetail() {
    }

    public ServiceTicketDetail(String seDetailId, String serviceName, String carName, String dateReceive, String dateReturn, int hours, String comment, String rate) {
        this.seDetailId = seDetailId;
        this.serviceName = serviceName;
        this.carName = carName;
        this.dateReceive = dateReceive;
        this.dateReturn = dateReturn;
        this.hours = hours;
        this.comment = comment;
        this.rate = rate;
    }

    public String getSeDetailId() {
        return seDetailId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public String getCarName() {
        return carName;
    }

    public String getDateReceive() {
        return dateReceive;
    }

    public String getDateReturn() {
        return dateReturn;
    }

    public int getHours() {
        return hours;
    }

    public String getComment() {
        return comment;
    }

    public String getRate() {
        return rate;
    }

    
    
}
