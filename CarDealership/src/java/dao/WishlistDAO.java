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
        try {
            cnn = DBUtils.getConnection();
            if (cnn != null) {
                cnn.setAutoCommit(false);
                // Bước 1: Thêm một hàng mới vào bảng wishlist
                String sql = "INSERT INTO Wishlist VALUES (?)";
                PreparedStatement st = cnn.prepareStatement(sql);
                st.setString(1, cusId);
                rs = st.executeUpdate();
                if (rs > 0) {
                    // Bước 2: Lấy id trong bảng wishlist mới chèn
                    sql = "SELECT TOP 1 wishlistId FROM Wishlist ORDER BY id DESC";
                    st = cnn.prepareStatement(sql);
                    ResultSet table = st.executeQuery();
                    if (table != null && table.next()) {
                        int wishlistId = table.getInt("wishlistId");
                        // Bước 3: Duyệt array wishlist
                        for (Car c : wishlist) {
                            String carId = c.getCarId();
                            sql = "INSERT INTO DetailWishlist VALUES (?, ?)";
                            st = cnn.prepareStatement(sql);
                            st.setString(1, carId);
                            st.setInt(2, wishlistId);
                            rs = st.executeUpdate();
                        }
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
    //sua code remove
    public int removeWishlist(String cusId, ArrayList<Car> wishlist) {
        int rs = 0;
        Connection cnn = null;
        try {
            cnn = DBUtils.getConnection();
            if (cnn != null) {
                cnn.setAutoCommit(false);
                // Bước 1: Thêm một hàng mới vào bảng wishlist
                String sql = "INSERT INTO Wishlist VALUES (?)";
                PreparedStatement st = cnn.prepareStatement(sql);
                st.setString(1, cusId);
                rs = st.executeUpdate();
                if (rs > 0) {
                    // Bước 2: Lấy id trong bảng wishlist mới chèn
                    sql = "SELECT TOP 1 wishlistId FROM Wishlist ORDER BY id DESC";
                    st = cnn.prepareStatement(sql);
                    ResultSet table = st.executeQuery();
                    if (table != null && table.next()) {
                        int wishlistId = table.getInt("wishlistId");
                        // Bước 3: Duyệt array wishlist
                        for (Car c : wishlist) {
                            String carId = c.getCarId();
                            sql = "INSERT INTO DetailWishlist VALUES (?, ?)";
                            st = cnn.prepareStatement(sql);
                            st.setString(1, carId);
                            st.setInt(2, wishlistId);
                            rs = st.executeUpdate();
                        }
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
