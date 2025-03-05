/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.Customer;
import mylib.DBUtils;

/**
 *
 * @author trant
 */
public class CustomerDAO {
    public Customer checkLogin(String cusName, String phone){
        Customer rs = null;
        Connection cnn = null;
        
        try{
            cnn = DBUtils.getConnection();
            if(cnn != null){
            String sql = "SELECT custID,custName,phone,sex,cusAddress\n"
                        + "FROM dbo.Customer\n"
                        + "WHERE custName= ? and phone=?";
            PreparedStatement st = cnn.prepareStatement(sql);
            st.setString(1, cusName);
            st.setString(2, phone);
            ResultSet table = st.executeQuery();
                if(table != null){
                    while(table.next()){
                        String cusId = table.getString("custID");
                        String custName = table.getString("custName");
//                        String phoneResult = "" + table.getString("phone"); // Không bị ảnh hưởng nếu DB đảm bảo NOT NULL    
                        String sex = table.getString("sex");
                        String custAdd = table.getString("cusAddress");
                        rs = new Customer(cusId, custName, phone, sex, custAdd);   
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try {
                if (cnn != null) cnn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return rs;
    }
}
