/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.Mechanic;
import mylib.DBUtils;

/**
 *
 * @author trant
 */
public class MechanicDAO {
    public Mechanic checkLogin(String mechanicName){
        Mechanic rs = null;
        Connection cnn = null;
        
        try{
            cnn = DBUtils.getConnection();
            if(cnn != null){
                  String sql = "SELECT mechanicID, mechanicName\n"
                                + "FROM dbo.Mechanic\n"
                                + "WHERE mechanicName = ?";
            PreparedStatement st = cnn.prepareStatement(sql);
            
            st.setString(1, mechanicName);
              
            ResultSet table=st.executeQuery();
                if(table!=null){
                    while(table.next()){
                        String mechanicId = table.getString("mechanicID");
                        table.getString("mechanicName");
                        rs = new Mechanic(mechanicId, mechanicName);  
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
