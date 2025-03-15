/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
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
                        rs = new Mechanic(mechanicId, mechanicName, 0);
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

    /**
     *
     * @return
     */
       public ArrayList<Mechanic> getTop3Mechanics() {
        Connection cnn = null;
        PreparedStatement st = null;
        ResultSet table = null;
        ArrayList<Mechanic> mechanics = new ArrayList<>();

        try {
            cnn = DBUtils.getConnection();
            if (cnn != null) {
                String sql = "SELECT TOP 3 m.mechanicID, m.mechanicName, COUNT(s.serviceID) AS total_services " +
                             "FROM ServiceMehanic s " +
                             "JOIN Mechanic m ON s.mechanicID = m.mechanicID " +
                             "GROUP BY m.mechanicID, m.mechanicName " +
                             "ORDER BY total_services DESC";
                st = cnn.prepareStatement(sql);
                table = st.executeQuery();

                while (table.next()) {
                    String mechanicID = table.getString("mechanicID");
                    String name = table.getString("mechanicName"); 
                    int total_Services = table.getInt("total_Services");

                    mechanics.add(new Mechanic(mechanicID, name, total_Services));
                }
                
                
                
                
//                SELECT TOP 3 m.mechanicID, m.mechanicName, COUNT(s.serviceID) AS total_services
//FROM ServiceMehanic s
//JOIN Mechanic m ON s.mechanicID = m.mechanicID
//GROUP BY m.mechanicID, m.mechanicName
//ORDER BY total_services DESC
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (table != null) table.close();
                if (st != null) st.close();
                if (cnn != null) cnn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mechanics;
    }
}
