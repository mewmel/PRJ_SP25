/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import model.ServiceTicketDetail;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import mylib.DBUtils;

/**
 *
 * @author trant
 */
public class ServiceTicketDetailDAO {
    public ArrayList<ServiceTicketDetail> getTicketsByCustomer(String custID) {
        ArrayList<ServiceTicketDetail> rs = new ArrayList<>();
        Connection cnn = null;

        try  {
            cnn = DBUtils.getConnection();
            if (cnn != null) {
                String sql = " SELECT t.serviceTicketID, s.serviceName, c.model AS carName, t.dateReceived, t.dateReturned, sm.hours, sm.comment, sm.rate "
                        + " FROM ServiceTicket t "
                        + " JOIN Cars c ON t.carID = c.carID "
                        + " JOIN ServiceMechanic sm ON t.serviceTicketID = sm.serviceTicketID "
                        + " JOIN Service s ON sm.serviceID = s.serviceID "
                        + " WHERE t.custID = ? ";

                PreparedStatement st = cnn.prepareStatement(sql);
                st.setString(1, custID);
                ResultSet table = st.executeQuery();

                if (table != null) {
                    while (table.next()) {
                        String id = table.getString("serviceTicketID");
                        String name = table.getString("serviceName");
                        String carName = table.getString("carName");
                        String dateRec = table.getString("dateReceived");
                        String dateRet = table.getString("dateReturned");
                        int hour = table.getInt("hours");
                        String comment = table.getString("comment");
                        String rate = table.getString("rate");
                        ServiceTicketDetail se = new ServiceTicketDetail(id, name, carName, dateRec, dateRet, hour, comment, rate);
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
}
