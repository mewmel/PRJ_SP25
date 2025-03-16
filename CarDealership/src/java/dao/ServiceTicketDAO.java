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
import model.ServiceTicket;
import mylib.DBUtils;

/**
 *
 * @author ThinkPad
 */
public class ServiceTicketDAO {
    
    /**
     * thuw
     * @param input
     * @return service ticket lay ra sau khi search bang cusId/carId/dateRec
     */
     public ArrayList<ServiceTicket> getServiceTicket(String input) {
        ArrayList<ServiceTicket> rs = new ArrayList<>();
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
                                    "  WHERE [custID] = ? or [carID] = ? ";
                    PreparedStatement st = cnn.prepareStatement(sql);
                    st.setString(1, input);
                    st.setString(2, input);
                    st.setString(3, input);
                    
                    ResultSet table = st.executeQuery();
                    if (table != null) {
                        while (table.next()) {                            
                            String Id = table.getString("serviceTicketID");
                            String dateRec = table.getString("dateReceived");
                            String dateRet = table.getString("dateReturned");
                            String cusId = table.getString("custID");
                            String carId = table.getString("carID");
                            ServiceTicket in = new ServiceTicket(Id, dateRec, dateRet, cusId, carId);
                            rs.add(in);
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
    //thuw 
     public ArrayList<ServiceTicket> getAllServiceTicket() {
        ArrayList<ServiceTicket> rs = new ArrayList<>();
        Connection cnn = null;
        
        try{
            cnn=DBUtils.getConnection();
                if (cnn != null) {
                    String sql = "SELECT [serviceTicketID]\n" +
                                    "      ,[dateReceived]\n" +
                                    "      ,[dateReturned]\n" +
                                    "      ,[custID]\n" +
                                    "      ,[carID]\n" +
                                    "  FROM [Car_Dealership].[dbo].[ServiceTicket]\n";

                    PreparedStatement st = cnn.prepareStatement(sql);

                    
                    ResultSet table = st.executeQuery();
                    if (table != null) {
                        while (table.next()) {                            
                            String Id = table.getString("serviceTicketID");
                            String dateRec = table.getString("dateReceived");
                            String dateRet = table.getString("dateReturned");
                            String cusId = table.getString("custID");
                            String carId = table.getString("carID");
                            ServiceTicket in = new ServiceTicket(Id, dateRec, dateRet, cusId, carId);
                            rs.add(in);
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
         // Linh
    public ArrayList<ServiceTicket> getServiceTicket(String cusId, String date) {
        ArrayList<ServiceTicket> rs = new ArrayList<>();
        Connection cnn = null;
        
        try{
            cnn=DBUtils.getConnection();
                if (cnn != null) {
                    String sql = "SELECT    [serviceTicketID]"+
                                    "      ,[dateReceived]\n" +
                                    "      ,[dateReturned]\n" +
                                    "      ,[custID]\n" +
                                    "      ,[carID]\n" +
                                    "  FROM [Car_Dealership].[dbo].[ServiceTicket]"+
                                    "  WHERE [custID] = ? and [dateReceived] like ?";
                    PreparedStatement st = cnn.prepareStatement(sql);
                    st.setString(1, "%"+cusId+"%" );
                    st.setString(2, date );
                    ResultSet table = st.executeQuery();
                    if (table != null) {
                        while (table.next()) {                
                            String serviceTicketID  = table.getString("serviceTicketID");                            
                            String dateReceived = table.getString("dateReceived");
                            String dateReturned = table.getString("dateReturned");
                            String custID = table.getString("custID");
                            String carID = table.getString("carID");
                            ServiceTicket serviceTicket = new ServiceTicket(serviceTicketID, dateReceived, dateReturned, custID, carID);
                            rs.add(serviceTicket);
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

    /**
     * Linh
     * get from ServiceMechanic
     * @param mechanicID
     * @return 
     */    
        public ArrayList<ServiceTicket> getAllServiceTicket(String mechanicID) {
        ArrayList<ServiceTicket> rs = new ArrayList<>();
        Connection cnn = null;
        
        try{
            cnn=DBUtils.getConnection();
                if (cnn != null) {
                    String sql = "SELECT    st.[serviceTicketID]"+
                                    "      ,st.[dateReceived]\n" +
                                    "      ,st.[dateReturned]\n" +
                                    "      ,st.[custID]\n" +
                                    "      ,st.[carID]\n" +
                                    "  FROM [Car_Dealership].[dbo].[ServiceTicket] st\n"+
                                    "  JOIN [Car_Dealership].[dbo].[ServiceMechanic] sm ON st.serviceTicketID = sm.serviceTicketID\n" +
                                    "  WHERE sm.[mechanicID] = ?";
                    PreparedStatement st = cnn.prepareStatement(sql);
                    st.setString(1, mechanicID);
                    ResultSet table = st.executeQuery();
                    if (table != null) {
                        while (table.next()) {                
                            
                            String serviceTicketID  = table.getString("serviceTicketID");                            
                            String dateReceived = table.getString("dateReceived");
                            String dateReturned = table.getString("dateReturned");
                            String custID = table.getString("custID");
                            String carID = table.getString("carID");
                            
                            ServiceTicket serviceTicket = new ServiceTicket(serviceTicketID, dateReceived, dateReturned, custID, carID);
                            rs.add(serviceTicket);
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
        
    public ArrayList<ServiceTicket> getServiceTicketsByCusID(String cusID) {
    Connection cnn = null;
    ArrayList<ServiceTicket> tickets = new ArrayList<>();

    try {
        cnn = DBUtils.getConnection();
        if (cnn != null) {
            String sql =  "SELECT [serviceTicketID], [dateReceived], [dateReturned], [cusID], [carID] " 
                          + "FROM [Car_Dealership].[dbo].[ServiceTicket] "
                          + "WHERE [custID] = ?";
            PreparedStatement st = cnn.prepareStatement(sql);
            st.setString(1, cusID);

            ResultSet table = st.executeQuery();
            while (table.next()) {
                String ticketId = String.valueOf(table.getInt("serviceTicketID"));
                String dateReceived = table.getString("dateReceived");
                String dateRet = table.getString("dateReturned");
                String carId = table.getString("carID");

                tickets.add(new ServiceTicket(ticketId, dateReceived, dateRet, cusID, carId));
            }

        }
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        try {
            if (cnn != null) {
                cnn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    return tickets;
}

 
}
