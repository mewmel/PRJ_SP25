/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import dao.CustomerDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Customer;
import model.SalePerson;

/**
 *
 * @author ThinkPad
 */
public class CreateInvoiceServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */

            request.setCharacterEncoding("UTF-8");
            
            
            // Lấy dữ liệu từ form  

            out.println("<!DOCTYPE html>");
            request.setCharacterEncoding("utf-8");           
        // Kiểm tra session
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("sale") == null) {
            response.sendRedirect("LoginStaffPage.jsp"); // Chuyển hướng nếu chưa đăng nhập
            return;
        }

        // Lấy thông tin SalePerson
        SalePerson sale = (SalePerson) session.getAttribute("sale");

        try {
            
            // Lấy danh sách khách hàng từ database
            CustomerDAO customer = new CustomerDAO();
            ArrayList<Customer> customerList = customer.getAllCustomers();

            // Kiểm tra danh sách xe có rỗng không
            if (customerList == null || customerList.isEmpty()) {
                request.setAttribute("message", "Không có KH nào trong hệ thống.");
            } else {
                request.setAttribute("LISTCUSTOMER_RESULT", customerList);
            }

            // Chuyển đến SaleDashBoard.jsp
            request.getRequestDispatcher("SaleDashBoard.jsp").forward(request, response);
            
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Lỗi khi tải danh sách KH.");
        }

        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
