/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ServiceMechanicDAO;
import dao.ServiceTicketDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Customer;
import model.Mechanic;
import model.ServiceMechanic;
import model.ServiceTicket;

/**
 *
 * @author ThinkPad
 */
public class ServiceTicketServlet extends HttpServlet {

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
            request.setCharacterEncoding("utf-8");
            HttpSession s = request.getSession(false);
            Mechanic mechanic = null;

            //Kiểm tra session trước khi lấy dữ liệu
            if (s != null) {
                mechanic = (Mechanic) s.getAttribute("mechanic");
            }

            if (mechanic == null) {
                request.setAttribute("ERROR", "You need to login to do this.");
                request.getRequestDispatcher("LoginStaffPage.jsp").forward(request, response);
                return;
            }

            ServiceTicketDAO ticketDAO = new ServiceTicketDAO();
            ServiceMechanicDAO mechanicDAO = new ServiceMechanicDAO();

            // Lấy danh sách tất cả Service Ticket
            ArrayList<ServiceTicket> list = ticketDAO.getAllServiceTicket(mechanic.getMechanicId());
            if (list.isEmpty()) {
                request.setAttribute("ERROR", "Không có vé dịch vụ nào.");
            } else {
                request.setAttribute("SERVICE_RESULT", list);
            }

            // Kiểm tra nếu người dùng bấm "Show" để lấy chi tiết
            String serviceTicketId = request.getParameter("txtServiceTicketId");
            if (serviceTicketId != null && !serviceTicketId.isEmpty()) {
                ArrayList<ServiceMechanic> details = mechanicDAO.getServiceMechanic(mechanic.getMechanicId() + "", serviceTicketId);
                request.setAttribute("SERVICE_DETAIL", details);
            }

            request.getRequestDispatcher("MechanicDashBoard.jsp").forward(request, response);
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
