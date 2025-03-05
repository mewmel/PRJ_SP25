/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.SalePerson;
import mylib.DBUtils;

/**
 *
 * @author trant
 */
public class SalePersonDAO {
    public SalePerson checkLogin(String saleName){
        SalePerson rs = null;
        Connection cnn = null;
        
        try{
          cnn = DBUtils.getConnection();
          if(cnn != null){
                String sql = "select salesID,salesName,birthday,sex,salesAddress\n"
                        + "from dbo.SalesPerson\n"
                        + "where salesName=?";
                PreparedStatement st = cnn.prepareStatement(sql);
                st.setString(1, saleName);
              
                ResultSet table = st.executeQuery();
                if(table != null){
                  while(table.next()){
                        String salesId = table.getString("salesID");
                        saleName = table.getString("salesName");
                        String bd = "" + table.getString("birthday");
                        String sex = table.getString("sex");
                        String salesAdd = table.getString("salesAddress");
                        rs = new SalePerson(salesId, saleName, bd, sex, salesAdd);   
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                if(cnn != null) cnn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return rs;
    }
}
