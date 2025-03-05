/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.ServiceTicket;
import mylib.DBUtils;

/**
 *
 * @author trant
 */
public class ServiceTicketDAO {
    public ServiceTicket getServiceTicket (String cusID, String dateReceived) {
        ServiceTicket rs = null; 
        Connection cnn = null;
        
        try{
            cnn=DBUtils.getConnection();
                if (cnn != null) {
                    String sql = "SELECT [serviceTicketID]\n" +
                                    "      ,[dateReceived]\n" +
                                    "      ,[dateReturned]\n" +
                                    "      ,[custID]\n" +
                                    "      ,[carID]\n" +
                                    "  FROM [Car_Dealership].[dbo].[ServiceTicket]\n" +
                                    "  WHERE [custID] = ? or [carID] = ? or [dateReceived] like ?";
                    PreparedStatement st = cnn.prepareStatement(sql);
                    st.setString(1, cusID );
                    st.setString(2, dateReceived );
                    
                    ResultSet table = st.executeQuery();
                    if (table != null) {
                        while (table.next()) {                            
                            String ticketId = table.getString("serviceTicketID");
                            table.getString("dateReceived");
                            String dateRet = table.getString("dateReturned");
                            String cusId = table.getString("custID");
                            String carId = table.getString("carID");
                            rs = new ServiceTicket(ticketId, dateReceived, dateRet, cusId, carId);
                        }
                    } 
                }
            } catch (Exception e) {
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
