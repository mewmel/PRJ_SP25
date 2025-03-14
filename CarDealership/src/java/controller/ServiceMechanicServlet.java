/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import dao.ServiceMechanicDAO;
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
public class ServiceMechanicServlet extends HttpServlet {

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
           Mechanic mechan = (Mechanic) s.getAttribute("mechanic");
           
           if(mechan == null){
               request.setAttribute("ERROR", "ban can login de thuc hien cac tinh nang");
               request.getRequestDispatcher("LoginStaffPage.jsp").forward(request, response);
           }
           else{
               ServiceMechanicDAO d = new ServiceMechanicDAO();
               String serviceTicketId = request.getParameter("txtServiceTicketId");
               
               ArrayList<ServiceMechanic> list = d.getServiceMechanic(mechan.getMechanicId()+"", serviceTicketId);
               request.setAttribute("SERVICE_MECHANIC_RESULT", list);
               request.getRequestDispatcher("MechanicDashBoard.jsp").forward(request, response);
           }
        }
//            HttpSession s = request.getSession();
//           Mechanic mecha = (Mechanic) s.getAttribute("mechanic");
//           if(mecha == null){
//               request.setAttribute("ERROR", "You need to log in to do this!");
//               request.getRequestDispatcher("LoginStaffPage.jsp").forward(request, response);
//           }
//           else{
//               ServiceMechanicDAO d = new ServiceMechanicDAO();
//               String serviceTicketId = request.getParameter("txtServiceTicketId");
//               String date="";
//               ArrayList<ServiceTicket> list = d.getServiceTicket(mecha.getMechanicId()+"", date);
//               request.setAttribute("TICKET_RESULT", list);
//               request.getRequestDispatcher("MechanicDashBoard.jsp").forward(request, response);
//           }
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
