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
import model.Parts;
import mylib.DBUtils;

/**
 *
 * @author ThinkPad
 */
public class PartsDAO {

    public ArrayList<Parts> getAllParts() {
        Connection cnn = null;
        ArrayList<Parts> list = new ArrayList<>();

        try {
            cnn = DBUtils.getConnection(); // Kết nối DB
            if (cnn != null) {
                String sql = "SELECT partID, partName, purchasePrice, retailPrice FROM Parts";
                PreparedStatement st = cnn.prepareStatement(sql);

                ResultSet rs = st.executeQuery();
                while (rs.next()) {
                    String partID = rs.getString("partID");
                    String partName = rs.getString("partName");
                    String purchasePrice = rs.getString("purchasePrice");
                    String retailPrice = rs.getString("retailPrice");

                    list.add(new Parts(partID, partName, purchasePrice, retailPrice));
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
        return list;
    }

    public ArrayList<Parts> searchPartsByName(String searchName) {
        Connection cnn = null;
        ArrayList<Parts> list = new ArrayList<>();

        try {
            cnn = DBUtils.getConnection(); // Kết nối DB
            if (cnn != null) {
                String sql = "SELECT partID, partName, purchasePrice, retailPrice FROM Parts WHERE partName LIKE ?";
                PreparedStatement st = cnn.prepareStatement(sql);
                st.setString(1, "%" + searchName + "%"); // Tìm kiếm theo từ khóa

                ResultSet rs = st.executeQuery();
                while (rs.next()) {
                    String partID = rs.getString("partID");
                    String partName = rs.getString("partName");
                    String purchasePrice = rs.getString("purchasePrice");
                    String retailPrice = rs.getString("retailPrice");

                    list.add(new Parts(partID, partName, purchasePrice, retailPrice));
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
        return list;
    }

    public int getMaxPartID() {
        int newPartID = 1; // Mặc định nếu bảng trống
        
        Connection cnn = null;
        PreparedStatement st = null;

        try {
            cnn = DBUtils.getConnection(); // Kết nối DB
            if (cnn != null) {
                // SQL câu lệnh để cập nhật chỉ giá mua và giá bán
                String sql = "SELECT MAX(partID) FROM [dbo].[Parts]";
                st = cnn.prepareStatement(sql);
                
                ResultSet rs = st.executeQuery();

                if (rs.next()) {
                    newPartID = rs.getInt(1) + 1; // Tăng giá trị lên 1
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Đảm bảo đóng các tài nguyên
            try {
                if (st != null) {
                    st.close();
                }
                if (cnn != null) {
                    cnn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return newPartID;
    }

    public boolean savePart(String partID, String partName, String purchasePrice, String retailPrice) {
        String query = "INSERT INTO [Parts] ([partID], [partName], [purchasePrice], [retailPrice]) VALUES (?, ?, ?, ?)";

        Connection cnn = null;
        PreparedStatement ps = null;
        
        try {
            cnn = DBUtils.getConnection();
            if (cnn != null) {
                ps = cnn.prepareStatement(query);
                
                ps.setString(1, partID);
                ps.setString(2, partName);
                ps.setString(3, purchasePrice);
                ps.setString(4, retailPrice);

                int rowsInserted = ps.executeUpdate();
                return rowsInserted > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PartsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }

    public boolean updateParts(String partID, String purchasePrice, String retailPrice) {
        Connection cnn = null;
        PreparedStatement st = null;

        try {
            cnn = DBUtils.getConnection(); // Kết nối DB
            if (cnn != null) {
                // SQL câu lệnh để cập nhật chỉ giá mua và giá bán
                String sql = "UPDATE Parts SET purchasePrice = ?, retailPrice = ? WHERE partID = ?";
                st = cnn.prepareStatement(sql);
                
                st.setString(1, purchasePrice);
                st.setString(2, retailPrice);
                st.setString(3, partID);   

                int rowsUpdated = st.executeUpdate();
                return rowsUpdated > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();  // In ra lỗi SQL nếu có
            return false;
        } finally {
            // Đảm bảo đóng các tài nguyên
            try {
                if (st != null) {
                    st.close();
                }
                if (cnn != null) {
                    cnn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return false;
    }
    
    public boolean DeletePart(String partID){
        Connection cnn = null;
        PreparedStatement st = null;

        try {
            cnn = DBUtils.getConnection(); // Kết nối DB
            if (cnn != null) {
                String sql = "DELETE Parts WHERE partID = ?";
                st = cnn.prepareStatement(sql);
                
                st.setString(1, partID); 

                int rowsDeleted = st.executeUpdate();
                return rowsDeleted > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();  // In ra lỗi SQL nếu có
            return false;
        } finally {
            // Đảm bảo đóng các tài nguyên
            try {
                if (st != null) {
                    st.close();
                }
                if (cnn != null) {
                    cnn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return false;
    }
}
