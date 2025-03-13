package controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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

/**
 *
 * @author trant
 */
public class CusSaleServlet extends HttpServlet {

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

            CustomerDAO cusDAO = new CustomerDAO();
            ArrayList<Customer> list = cusDAO.getAllCustomers();

            String id = request.getParameter("txtcusId");
            if (id != null && !id.trim().isEmpty()) {
                ArrayList<Customer> searchList = cusDAO.getCus(id);
                if (!searchList.isEmpty()) {
                    list = searchList;
                }
                request.setAttribute("SEARCH_ID", id);
            }

            request.setAttribute("CUS_SALE_RESULT", list);
            request.getRequestDispatcher("ViewCustomer.jsp").forward(request, response);
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
        CustomerDAO cusDAO = new CustomerDAO();
        ArrayList<Customer> list = new ArrayList<>();
        list = cusDAO.getAllCustomers();
        String action = request.getParameter("action");

        if (action.equals("more")) {
            String cusId = request.getParameter("cusId"); 
            Customer selectedCustomer = cusDAO.getCus1(cusId);
            if (selectedCustomer != null) {
                    request.getSession().setAttribute("SELECTED_CUS", selectedCustomer); // Đưa vào session
                }  
            request.setAttribute("CUS_SALE_RESULT", list);
            request.getRequestDispatcher("ViewCustomer.jsp").forward(request, response);
            return;
        }
        if (action.equals("update")) {
            String cusId = request.getParameter("cusId");

            if (cusId != null) {
                cusDAO.updateByCusId(cusId);
                response.sendRedirect("CusSaleServlet");
                return;
            }
        }
        if (action.equals("delete")) {
            String cusId = request.getParameter("cusId");

            if (cusId != null) {
                cusDAO.deleteCus(cusId);
                response.sendRedirect("CusSaleServlet");
                return;
            }
        }

        if (action.equals("Add")) {
            String cusId = request.getParameter("txtcusId");
            String name = request.getParameter("txtcusName");
            String phone = request.getParameter("txtcusPhone");
            String sex = request.getParameter("txtcusSex");
            String ad = request.getParameter("txtcusAd");

            Customer newCus = new Customer(cusId, name, phone, sex, ad);
            cusDAO.addCustomer(newCus);

            // Cập nhật lại danh sách xe ngay tại đây
            request.setAttribute("CUS_SALE_RESULT", list);

            // Chuyển hướng tới trang ViewCar.jsp kèm danh sách mới
            request.getRequestDispatcher("ViewCustomer.jsp").forward(request, response);
            return;

        } else {
            // Mặc định: Load danh sách xe nếu không có action cụ thể
            request.setAttribute("CUS_SALE_RESULT", list);
            request.getRequestDispatcher("ViewCustomer.jsp").forward(request, response);
        }

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
