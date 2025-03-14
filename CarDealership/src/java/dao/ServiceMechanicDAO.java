/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.ServiceMechanic;
import model.ServiceTicket;
import mylib.DBUtils;

/**
 *
 * @author ThinkPad
 */
public class ServiceMechanicDAO {
    //linh
     public ArrayList<ServiceMechanic> getServiceMechanic(String mechanicId, String serviceTicketId) {
        ArrayList<ServiceMechanic> rs = new ArrayList<>();
        Connection cnn = null;

        try {
            cnn = DBUtils.getConnection();
            if (cnn != null) {
                String sql = "SELECT \n"
                        + "      [serviceID]\n"
                        + "      ,[hours]\n"
                        + "      ,[comment]\n"
                        + "      ,[rate]\n"
                        + "  FROM [dbo].[ServiceMechanic]\n"
                        + "  WHERE [mechanicID] = ? AND [serviceTicketID] = ? ";
                PreparedStatement st = cnn.prepareStatement(sql);
                st.setString(1, mechanicId);
                st.setString(2, serviceTicketId);
                ResultSet table = st.executeQuery();
                if (table != null) {
                    while (table.next()) {
//                        String serviceTicketID = table.getString("serviceTicketID");
                        String serviceID = table.getString("serviceID");
                        String hours = table.getString("hours");
                        String comment = table.getString("comment");
                        String rate = table.getString("rate");
                        ServiceMechanic sm = new ServiceMechanic(hours, comment, rate, serviceTicketId, serviceID, mechanicId);
                        rs.add(sm);
                    }
                }
            }
        } catch (Exception e) { // chay duoc rs.add(new ServiceMechanic("????", "????", "????", "????", "????", "????"));
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
        return rs;
    }
    //linh
    public boolean update(ServiceMechanic serviceMechanic){
        String sql = "UPDATE [ServiceMechanic] SET [hours] = ?, [comment] = ?, [rate] = ?\n"+
                    "WHERE [serviceTicketID] = ? AND [serviceID] = ? AND [mechanicID] = ?";
        try {
            Connection conn = null;
            try {
                conn = DBUtils.getConnection();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ps.setString(1, serviceMechanic.getHours());
            ps.setString(2, serviceMechanic.getComment());
            ps.setString(3, serviceMechanic.getRate());
            
            ps.setString(4, serviceMechanic.getServiceTicketID());
            ps.setString(5, serviceMechanic.getServiceID());
            ps.setString(6, serviceMechanic.getMechanicID());
            
            ps.executeUpdate();
            conn.close();
            
            return true;
        } catch (SQLException e) {
            System.out.println("Update service mechanic error: "+e.getMessage());
            e.printStackTrace();
        }
        return false;
    }
    
    //thuw
        public ArrayList<ServiceTicket> getServiceTicket(String cusId, String dateReceived) {
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
                                    "  WHERE [custID] = ? and [dateReceived] like ?";
                    PreparedStatement st = cnn.prepareStatement(sql);
                    st.setString(1, cusId );
                    st.setString(2, "%"+dateReceived+"%" );
                    
                    ResultSet table = st.executeQuery();
                    if (table != null) {
                        while (table.next()) {                            
                            String ticketId = table.getString("serviceTicketID");
                            String dateRec = table.getString("dateReceived");
                            String dateRet = table.getString("dateReturned");
                            String carId = table.getString("carID");
                            ServiceTicket in = new ServiceTicket(ticketId, dateRec, dateRet, cusId, carId);
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
        public ServiceTicket getServiceTicketByID(String tickeId) {
        ServiceTicket trs = null; 
        Connection cnn = null;
        
        try{
            cnn=DBUtils.getConnection();
                if (cnn != null) {
                    String sql = "SELECT [serviceTicketID]\n" 
                                +"      ,[dateReceived]\n" 
                                +"      ,[dateReturned]\n" 
                                +"      ,[custID]\n" 
                                +"      ,[carID]\n" 
                                +"  FROM [Car_Dealership].[dbo].[ServiceTicket]\n" 
                                +"  WHERE [serviceTicketID]= ?";
                    PreparedStatement st = cnn.prepareStatement(sql);
                    st.setString(1, tickeId);
                    ResultSet table = st.executeQuery();
                    if (table != null) {
                        while (table.next()) {                            
                            String ticketId = table.getString("serviceTicketID");
                            String dateReceived = table.getString("dateReceived");
                            String dateRet = table.getString("dateReturned");
                            String cusId = table.getString("custID");
                            String carId = table.getString("carID");
                            trs = new ServiceTicket(ticketId, dateReceived, dateRet, cusId, carId);
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
        return trs;
    } 
     
    }

