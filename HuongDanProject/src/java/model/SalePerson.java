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
public class SalePerson {
    private String saleId;
    private String saleName;
    private String bd;
    private String sex;
    private String address;

    public SalePerson() {
    }

    public SalePerson(String saleId, String saleName, String bd, String sex, String address) {
        this.saleId = saleId;
        this.saleName = saleName;
        this.bd = bd;
        this.sex = sex;
        this.address = address;
    }

    public String getSaleId() {
        return saleId;
    }

    public void setSaleId(String saleId) {
        this.saleId = saleId;
    }

    public String getSaleName() {
        return saleName;
    }

    public void setSaleName(String saleName) {
        this.saleName = saleName;
    }

    public String getBd() {
        return bd;
    }

    public void setBd(String bd) {
        this.bd = bd;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }   
}
