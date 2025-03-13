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
import model.Customer;
import mylib.DBUtils;

/**
 *
 * @author trant
 */
public class CustomerDAO {

    public Customer checkLogin(String cusName, String phone) {
        Customer rs = null;
        Connection cnn = null;

        try {
            cnn = DBUtils.getConnection();
            if (cnn != null) {
                String sql = "SELECT custID,custName,phone,sex,cusAddress\n"
                        + "FROM dbo.Customer\n"
                        + "WHERE custName= ? and phone=?";
                PreparedStatement st = cnn.prepareStatement(sql);
                st.setString(1, cusName);
                st.setString(2, phone);
                ResultSet table = st.executeQuery();
                if (table != null) {
                    while (table.next()) {
                        String cusId = table.getString("custID");
//                      String phoneResult = "" + table.getString("phone"); 
                        String sex = table.getString("sex");
                        String custAdd = table.getString("cusAddress");
                        rs = new Customer(cusId, cusName, phone, sex, custAdd);
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

    public void updateByCusId(String cusID) {
        Customer customer = null;
        Connection cnn = null;
        try {
            cnn = DBUtils.getConnection();
            if (cnn != null) {
                String sql = "UPDATE Customer SET custName = ?, phone = ?, sex = ?, cusAddress = ? WHERE custID = ?";
                PreparedStatement st = cnn.prepareStatement(sql);
                st.setString(1, customer.getCusName());
                st.setString(2, customer.getPhone());
                st.setString(3, customer.getSex());
                st.setString(4, customer.getCusAddress());
                st.setString(5, customer.getCusId());

                st.executeUpdate();
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
    }

    public String update(Customer customer) {
        String sql = "UPDATE Customer SET custName = ?, phone = ?, sex = ?, cusAddress = ? WHERE custID = ?";
        try {
            Connection conn = null;
            try {
                conn = DBUtils.getConnection();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, customer.getCusName());
            ps.setString(2, customer.getPhone());
            ps.setString(3, customer.getSex());
            ps.setString(4, customer.getCusAddress());
            ps.setString(5, customer.getCusId());

            ps.executeUpdate();
            conn.close();

            return customer.getCusId();
        } catch (SQLException e) {
            System.out.println("Update customer error: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public void deleteCus(String cusId) {
        Connection cnn = null;

        try {
            cnn = DBUtils.getConnection();
            
            if (cnn != null) {
                String sql = "DELETE FROM Customer WHERE cusId = ?";
            PreparedStatement ps = cnn.prepareStatement(sql);

            ps.setString(1, cusId);
            ps.executeUpdate();
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
    }

    public void addCustomer(Customer customer) {
        Connection cnn = null;
        try {
            cnn = DBUtils.getConnection();
            String sql = "INSERT INTO [Customer] ([custID], [custName], [phone], [sex],[cusAddress]) VALUES (?, ?, ?, ?, ?)";

            PreparedStatement st = cnn.prepareStatement(sql);

            st.setString(1, customer.getCusId());
            st.setString(2, customer.getCusName());
            st.setString(3, customer.getPhone());
            st.setString(4, customer.getSex());
            st.setString(5, customer.getCusAddress());

            st.executeUpdate();

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
    }

    public ArrayList<Customer> getAllCustomers() {
        ArrayList<Customer> rs = new ArrayList<>();
        Connection cnn = null;

        try {
            cnn = DBUtils.getConnection();
            if (cnn != null) {
                String sql = "SELECT    [custID]"
                        + "      ,[custName]\n"
                        + "      ,[phone]\n"
                        + "      ,[sex]\n"
                        + "      ,[cusAddress]\n"
                        + "  FROM [Car_Dealership].[dbo].[Customer]";
                PreparedStatement st = cnn.prepareStatement(sql);
                ResultSet table = st.executeQuery();
                if (table != null) {
                    while (table.next()) {

                        String custID = table.getString("custID");
                        String custName = table.getString("custName");
                        String phone = table.getString("phone");
                        String sex = table.getString("sex");
                        String cusAddress = table.getString("cusAddress");

                        Customer customer = new Customer(custID, custName, phone, sex, cusAddress);
                        rs.add(customer);
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

    public Customer getCus1(String cusId) {
        Customer cus = null;
        Connection cnn = null;

        try {
            cnn = DBUtils.getConnection();
            if (cnn != null) {
                String sql = "SELECT [custID]\n"
                        + "      ,[custName]\n"
                        + "      ,[phone]\n"
                        + "      ,[sex]\n"
                        + "      ,[cusAddress]\n"
                        + "  FROM [Car_Dealership].[dbo].[Customer]\n"
                        + "  WHERE [custID]= ?";
                PreparedStatement st = cnn.prepareStatement(sql);
                st.setString(1,cusId);
                ResultSet table = st.executeQuery();
                if (table != null) {
                    while (table.next()) {
                        String name = table.getString("custName");
                        String phone = table.getString("phone");
                        String sex = table.getString("sex");
                        String address = table.getString("cusAddress");
                        cus = new Customer(cusId, name, phone, sex, address);
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
        return cus;
    }

    public ArrayList<Customer> getCus(String cusId) {
        ArrayList<Customer> rs = new ArrayList();
        Connection cnn = null;

        try {
            cnn = DBUtils.getConnection();
            if (cnn != null) {
                String sql = "SELECT [custID]\n"
                        + "      ,[custName]\n"
                        + "      ,[phone]\n"
                        + "      ,[sex]\n"
                        + "      ,[cusAddress]\n"
                        + "  FROM [Car_Dealership].[dbo].[Customer]\n"
                        + "  WHERE [custID]= ?";
                PreparedStatement st = cnn.prepareStatement(sql);
                st.setString(1, "%" + cusId + "%");
                ResultSet table = st.executeQuery();
                if (table != null) {
                    while (table.next()) {
                        cusId = table.getString("custID");
                        String name = table.getString("custName");
                        String phone = table.getString("phone");
                        String sex = table.getString("sex");
                        Customer cus = new Customer(cusId, name, phone, sex, cusId);
                        rs.add(cus);
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
