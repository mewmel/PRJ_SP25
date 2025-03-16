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
import java.sql.Statement;
import java.util.ArrayList;
import model.Car;
import mylib.DBUtils;

// hết cứu T.T
public class WishlistDAO {

    public int createWishlist(String cusId, ArrayList<Car> wishlist) {
    int rs = 0;
    Connection cnn = null;
    PreparedStatement insertWishlistStmt = null;
    PreparedStatement insertDetailStmt = null;
    ResultSet generatedKeys = null;

    try {
        cnn = DBUtils.getConnection();
        if (cnn != null) {
            cnn.setAutoCommit(false); // Tắt auto-commit để có thể rollback nếu lỗi

            // Bước 1: Chèn vào bảng Wishlist
            String insertWishlistSQL = "INSERT INTO Wishlist (custID) VALUES (?)";
            insertWishlistStmt = cnn.prepareStatement(insertWishlistSQL, Statement.RETURN_GENERATED_KEYS);
            insertWishlistStmt.setString(1, cusId);
            insertWishlistStmt.executeUpdate();

            // Bước 2: Lấy ID mới tạo từ Wishlist
            generatedKeys = insertWishlistStmt.getGeneratedKeys();
            int wishlistId = -1;
            if (generatedKeys.next()) {
                wishlistId = generatedKeys.getInt(1);
            } else {
                throw new SQLException("Creating wishlist failed, no ID obtained.");
            }

            // Bước 3: Thêm vào DetailWishlist nếu có wishlistId hợp lệ
            String insertDetailSQL = "INSERT INTO DetailWishlist (carId, wishlistId) VALUES (?, ?)";
            insertDetailStmt = cnn.prepareStatement(insertDetailSQL);
            for (Car c : wishlist) {
                insertDetailStmt.setString(1, c.getCarId());
                insertDetailStmt.setInt(2, wishlistId);
                rs += insertDetailStmt.executeUpdate();
            }

            cnn.commit(); // Nếu không có lỗi, commit transaction
        }
    } catch (Exception e) {
        if (cnn != null) {
            try {
                cnn.rollback(); // Rollback nếu có lỗi
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
        }
        e.printStackTrace();
    } finally {
        // Đóng tài nguyên để tránh rò rỉ bộ nhớ
        try {
            if (generatedKeys != null) generatedKeys.close();
            if (insertWishlistStmt != null) insertWishlistStmt.close();
            if (insertDetailStmt != null) insertDetailStmt.close();
            if (cnn != null) cnn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    return rs;
}
    //sua code remove: hết cứu
public int removeWishlist(String cusId, ArrayList<Car> wishlist) {
    int rs = 0;
    Connection cnn = null;
    try {
        cnn = DBUtils.getConnection();
        if (cnn != null) {
            cnn.setAutoCommit(false);

            // Bước 1: Lấy wishlistID của khách hàng từ Wishlist
            String sql = "SELECT wishlistID FROM Wishlist WHERE custID = ?";
            PreparedStatement st = cnn.prepareStatement(sql);
            st.setString(1, cusId);
            ResultSet table = st.executeQuery();

            if (table != null && table.next()) {
                int wishlistId = table.getInt("wishlistID");

                // Bước 2: Xóa từng xe trong danh sách wishlist khỏi DetailWishlist
                for (Car c : wishlist) {
                    String carId = c.getCarId();
                    sql = "DELETE FROM DetailWishlist WHERE carID = ? AND wishlistID = ?";
                    st = cnn.prepareStatement(sql);
                    st.setString(1, carId);
                    st.setInt(2, wishlistId);
                    rs += st.executeUpdate();
                }

                // Bước 3: Nếu không còn xe nào, xóa cả Wishlist
                sql = "SELECT COUNT(*) AS total FROM DetailWishlist WHERE wishlistID = ?";
                st = cnn.prepareStatement(sql);
                st.setInt(1, wishlistId);
                table = st.executeQuery();
                if (table.next() && table.getInt("total") == 0) {
                    sql = "DELETE FROM Wishlist WHERE wishlistID = ?";
                    st = cnn.prepareStatement(sql);
                    st.setInt(1, wishlistId);
                    st.executeUpdate();
                }
            }
            cnn.commit();
            cnn.setAutoCommit(true);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return rs;
}
}
