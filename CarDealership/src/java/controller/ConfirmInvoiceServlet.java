/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.InvoiceDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.SalePerson;

/**
 *
 * @author ThinkPad
 */
public class ConfirmInvoiceServlet extends HttpServlet {

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

        // Kiểm tra session
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("sale") == null) {
            response.sendRedirect("LoginPage.jsp"); // Chuyển hướng nếu chưa đăng nhập
            return;
        }

        // Lấy thông tin SalePerson
        SalePerson sale = (SalePerson) session.getAttribute("sale");
        
        try {
            String customerID = request.getParameter("txtcustomerid");
            String customerName = request.getParameter("txtcustomername");
            String carModel = request.getParameter("txtcarmodel");
            String carID = request.getParameter("txtcarid");
            String invoiceDate = request.getParameter("txtdate");

            // Set các giá trị vào request attribute
            request.setAttribute("customerID", customerID);
            request.setAttribute("customerName", customerName);
            request.setAttribute("carModel", carModel);
            request.setAttribute("carID", carID);
            request.setAttribute("invoicedate", invoiceDate);

            // Lấy invoiceID từ DAO
            InvoiceDAO invoiceDAO = new InvoiceDAO();
            int invoiceID = invoiceDAO.getMaxInvoiceID();  // Lấy invoiceID cao nhất hiện tại
            
            // Đưa invoiceID vào request attribute để chuyển cho JSP
            request.setAttribute("invoiceID", invoiceID);

            // Gọi phương thức saveInvoice để lưu vào cơ sở dữ liệu
            boolean isSaved = invoiceDAO.saveInvoice(
                String.valueOf(invoiceID),  // invoiceID là số nguyên, convert thành String
                invoiceDate,
                String.valueOf(sale.getSaleId()),  // saleID lấy từ đối tượng SalePerson
                carID,
                customerID
            );
            
            if (isSaved) {
                out.println("Hóa đơn đã được lưu thành công!");
                // Chuyển hướng tới JSP để hiển thị hóa đơn
                RequestDispatcher dispatcher = request.getRequestDispatcher("DetailInvoice.jsp");
                dispatcher.forward(request, response);
            } else {
                out.println("Lỗi khi lưu hóa đơn.");
                // Gửi lỗi nếu không thể lưu hóa đơn
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Lỗi khi lưu hóa đơn.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Lỗi khi tải danh sách xe.");
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
