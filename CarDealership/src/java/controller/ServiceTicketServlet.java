/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

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
            Customer custPerson = (Customer) s.getAttribute("customer");
            Mechanic mechanic = (Mechanic) s.getAttribute("mechanic");
            
            ServiceTicketDAO d = new ServiceTicketDAO();
            
            if (custPerson == null) {
                if (mechanic == null) {
//                    request.setAttribute("ERROR", "ban can login de thuc hien cac tinh nang");
                    request.getRequestDispatcher("LoginPage.html").forward(request, response);
                } else {
                    
                    ArrayList<ServiceTicket> list = d.getAllServiceTicket(mechanic.getMechanicId());
                    request.setAttribute("SERVICE_RESULT", list);
                    request.getRequestDispatcher("MechanicDashBoard.jsp").forward(request, response);
                }
                
            } else {
                String date = "";
                ArrayList<ServiceTicket> list = d.getServiceTicket(custPerson.getCusId() + "", date);
                request.setAttribute("TICKET_RESULT", list);
                request.getRequestDispatcher("CustomerDashBoard.jsp").forward(request, response);
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
