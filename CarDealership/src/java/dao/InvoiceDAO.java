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
import model.Invoice;
import mylib.DBUtils;

/**
 *
 * @author trant
 */
public class InvoiceDAO {
    public ArrayList<Invoice> getInvoices(String cusID, String date){
        ArrayList<Invoice> rs = new ArrayList<>();
        Connection cnn = null;
        
        try{
            cnn = DBUtils.getConnection();
            if(cnn != null){
                String sql = "select invoiceID,invoiceDate,salesID,custID,carID\n"
                            + "from dbo.SalesInvoice\n"
                            + "where custID=? and invoiceDate like ?";
                PreparedStatement st = cnn.prepareStatement(sql);
                st.setString(1, cusID);
                st.setString(2, "%"+date+"%");
                ResultSet table = st.executeQuery();
                if(table != null){
                    while(table.next()){
                        String invoiceId = table.getString("invoiceID");
                        String invoiceDate = table.getString("invoiceDate");
                        String saleId = table.getString("salesID");
                        String carId = table.getString("carID");
                        Invoice in = new Invoice(invoiceId, invoiceDate, saleId, cusID, carId);
                        rs.add(in);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
//            try {
//                if(cnn != null) cnn.close();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }            
        }      
        return rs;
    }
    
    public boolean createInvoice(Invoice invoice) {
        String sql = "INSERT INTO [SalesInvoice] ([invoiceID], [invoiceDate], [salesID], [carID], [custID]) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, invoice.getInvoiceId());
            ps.setString(2, invoice.getInvoiceDate());
            ps.setString(3, invoice.getSaleId());
            ps.setString(4, invoice.getCarId());
            ps.setString(5, invoice.getCustid());            
            

            return ps.executeUpdate() > 0;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }
    
//   c
    
}

//public class InvoiceDAO {
//   public  ArrayList<Invoice> getInvoices(String custID, String date){
//        ArrayList<Invoice> rs=new ArrayList<>();
//        Connection cn=null;
//        try{
//            cn=DBUtils.getConnection();
//            if(cn!=null){
//                String sql = "select invoiceID,invoiceDate,salesID,carID,custID\n"
//                        + "from dbo.SalesInvoice\n"
//                        + "where custID=? and invoiceDate like ?";
//                PreparedStatement st=cn.prepareStatement(sql);
//                st.setString(1, custID);
//                st.setString(2, "%"+date+"%");
//                ResultSet table=st.executeQuery();
//                if(table!=null){
//                    while(table.next()){
//                        String id=table.getString("invoiceID");
//                        String d=table.getString("invoiceDate");
//                        String saleid=table.getString("salesID");
//                        String carid=table.getString("carID");
//                        Invoice in=new Invoice(id, d, saleid,custID, carid);
//                        rs.add(in);
//                    }
//                }
//            }
//        }catch(Exception e){
//            e.printStackTrace();
//        }finally{            
//        }      
//        
//        return rs;
//    }
//}