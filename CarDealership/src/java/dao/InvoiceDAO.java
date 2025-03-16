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
        
        try {
            cnn = DBUtils.getConnection();
            if(cnn != null){
                String sql = "SELECT invoiceID,invoiceDate,salesID,custID,carID\n"
                            + "FROM dbo.SalesInvoice\n"
                            + "WHERE custID = ? and invoiceDate like ?";
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
            try {
                if(cnn != null) cnn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }            
        }      
        return rs;
    }
    //linh
    public int getMaxInvoiceID() throws ClassNotFoundException {
    int newInvoiceID = 1; // Mặc định nếu bảng trống
    String query = "SELECT MAX(invoiceID) FROM [dbo].[SalesInvoice]";

    try (Connection cnn = DBUtils.getConnection();
         PreparedStatement ps = cnn.prepareStatement(query);
         ResultSet rs = ps.executeQuery()) {

        if (rs.next()) {
            newInvoiceID = rs.getInt(1) + 1; // Tăng giá trị lên 1
        }

    } catch (SQLException e) {
        System.err.println("Lỗi khi truy vấn MAX(invoiceID): " + e.getMessage());
    }
    return newInvoiceID;
}

    //linh
    // Phương thức lưu hóa đơn vào database
    public boolean saveInvoice( String invoiceID, String saleID, String carID, String custID) throws ClassNotFoundException {
        String query = "INSERT INTO SalesInvoice (invoiceID, invoiceDate, salesID, carID ,custID) VALUES (?, GETDATE(), ?, ?, ?)";

        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, invoiceID);
            ps.setString(2, saleID);
            ps.setString(3, carID);
            ps.setString(4, custID);

            int rowsInserted = ps.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }   
}
