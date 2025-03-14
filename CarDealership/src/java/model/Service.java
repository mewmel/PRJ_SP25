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
public class Service {
    private String serviceId;
    private String serviceName;
    private String hourlyRate;

    public Service() {
        serviceId = "";
        serviceName = "";
        hourlyRate = "";
    }

    public Service(String serviceId, String serviceName, String hourlyRate) {
        this.serviceId = serviceId;
        this.serviceName = serviceName;
        this.hourlyRate = hourlyRate;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(String hourlyRate) {
        this.hourlyRate = hourlyRate;
    }  
}
