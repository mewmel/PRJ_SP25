/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;

/**
 *
 * @author trant
 */
public class Invoice implements Serializable{
    private String invoiceId;
    private String invoiceDate;
    private String saleId;
    private String custid;
    private String carId;

    public Invoice() {
    }

    public Invoice(String invoiceId, String invoiceDate, String saleId, String custid, String carId) {
        this.invoiceId = invoiceId;
        this.invoiceDate = invoiceDate;
        this.saleId = saleId;
        this.custid = custid;
        this.carId = carId;
    }

    public String getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getSaleId() {
        return saleId;
    }

    public void setSaleId(String saleId) {
        this.saleId = saleId;
    }

    public String getCustid() {
        return custid;
    }

    public void setCustid(String custid) {
        this.custid = custid;
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }
      
}
