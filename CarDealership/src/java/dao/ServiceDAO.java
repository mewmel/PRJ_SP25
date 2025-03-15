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
import model.Service;
import mylib.DBUtils;

/**
 *
 * @author trant
 */
public class ServiceDAO {
    public Service getService(String seId) {
        Service se = null;
        Connection cnn = null;
        
        try {
            cnn = DBUtils.getConnection();
        if (cnn != null) {
            String sql = "SELECT serviceId, serviceName, hourlyRate\n"
                       + "FROM Service WHERE serviceId = ? ";
            PreparedStatement st = cnn.prepareStatement(sql);
            st.setString(1, seId);

            ResultSet table = st.executeQuery();
            if (table != null) {
                while (table.next()) {                    
                    String name = table.getString("serviceName");
                    String hourR = table.getString("hourlyRate");
                    se = new Service(seId, name, hourR);
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
        return se;
    }
    
    public ArrayList<Service> getServiceBy (String seId) {
        ArrayList<Service> rs = new ArrayList<>();
        Connection cnn = null;
        
        try {
            cnn = DBUtils.getConnection();
            if (cnn != null) {
                String sql = "SELECT serviceId, serviceName, hourlyRate\n"
                        + "FROM Service WHERE serviceId like ? or serviceName like ?";
                PreparedStatement st = cnn.prepareStatement(sql);
                st.setString(1, "%"+seId+"%");
                st.setString(2, "%"+seId+"%");
                ResultSet table = st.executeQuery();
                if (table != null) {
                    while (table.next()) {
                        seId = table.getString("serviceId");
                        String name = table.getString("serviceName");
                        String hourR = table.getString("hourlyRate");
                        Service se = new Service(seId, name, hourR);
                        rs.add(se);
                    }
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally{
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
    
    public ArrayList<Service> getAllService() {
        ArrayList<Service> rs = new ArrayList<>();
        Connection cnn = null;
        
        try {
            cnn = DBUtils.getConnection();
            if (cnn != null) {
                String sql = "SELECT serviceId, serviceName, hourlyRate\n"
                        + "FROM Service";
                PreparedStatement st = cnn.prepareStatement(sql);
                ResultSet table = st.executeQuery();
                if (table != null) {
                    while (table.next()) {
                        String seId = table.getString("serviceId");
                        String name = table.getString("serviceName");
                        String hourR = table.getString("hourlyRate");
                        Service se = new Service(seId, name, hourR);
                        rs.add(se);
                    }
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
        return rs;
    }

    public void addService(Service se) {
        Connection cnn = null;
        try {
            cnn = DBUtils.getConnection();
            String sql = "INSERT INTO Service (serviceId, serviceName, hourlyRate) VALUES (?, ?, ?)";
            PreparedStatement st = cnn.prepareStatement(sql);
                  
                    st.setString(1, se.getServiceId());
                    st.setString(2, se.getServiceName());
                    st.setString(3, se.getHourlyRate());
                    
                    st.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                if(cnn != null) cnn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
        
        
    public void updateService(String seId, String name, String hourly) {
        Connection cnn = null;
        try {
            cnn = DBUtils.getConnection();
            if (cnn != null) {
                String sql = "UPDATE Service \n"
                        + "SET serviceName = ?, hourlyRate = ?\n"
                        + "WHERE serviceId = ? \n";
                PreparedStatement st = cnn.prepareStatement(sql);
                st.setString(1, name);
                st.setString(2, hourly);
                st.setString(3, seId);
                st.executeUpdate();
            }
        }
         catch (Exception e) {
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
    }    
     
    public void deleteService(String seId) {
        Connection cnn = null;
        
        try {
            cnn = DBUtils.getConnection();
            if (cnn != null) {
                String sql = "DELETE FROM Service WHERE serviceId = ?";
                PreparedStatement st = cnn.prepareStatement(sql);
                st.setString(1, seId);
                
                st.executeUpdate();
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
    }      
        
}
