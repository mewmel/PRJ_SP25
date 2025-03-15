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
import model.Car;
import mylib.DBUtils;

public class WishlistDAO {

    public int createWishlist(String cusId, ArrayList<Car> wishlist) {
        int rs = 0;
    Connection cnn = null;
    PreparedStatement st = null;
    ResultSet table = null;

    try {
        cnn = DBUtils.getConnection();
        if (cnn != null) {
            cnn.setAutoCommit(false);

            // Bước 1: Thêm một hàng mới vào bảng Wishlist (Cần chỉ định cột cụ thể)
            String sql = "INSERT INTO Wishlist (custID) VALUES (?)";
            st = cnn.prepareStatement(sql);
            st.setString(1, cusId);
            st.executeUpdate();

            // Bước 2: Lấy wishlistID mới được tạo
            table = st.getGeneratedKeys();
            int wishlistId = -1;
            if (table != null && table.next()) {
                wishlistId = table.getInt(1);
            }

            if (wishlistId != -1) {
                // Bước 3: Duyệt danh sách wishlist và thêm vào DetailWishlist
                sql = "INSERT INTO DetailWishlist VALUES (?, ?)";
                st = cnn.prepareStatement(sql);
                for (Car c : wishlist) {
                    String carId = c.getCarId();
                    st.setString(1, carId);
                    st.setInt(2, wishlistId);
                    
                    rs += st.executeUpdate();
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
    //sua code remove: hen xui:
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
