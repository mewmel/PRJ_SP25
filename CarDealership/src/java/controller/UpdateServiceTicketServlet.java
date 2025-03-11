/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ServiceMechanicDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Mechanic;
import model.ServiceMechanic;

/**
 *
 * @author trant
 */
public class UpdateServiceTicketServlet extends HttpServlet {

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
            Mechanic mechanic = (Mechanic) s.getAttribute("mechanic");
            
            if (mechanic == null) {
                request.setAttribute("ERROR", "ban can dang nhap");
                request.getRequestDispatcher("LoginStaffPage.jsp").forward(request, response);
                return;
            }
            
            String hours = request.getParameter("txtHours");
            String comment = request.getParameter("txtComment");
            String rate = request.getParameter("txtRate");
            String serviceTicketID = request.getParameter("txtServiceTicketId");
            String serviceID = request.getParameter("txtServiceId");
            
            String mechanicID = mechanic.getMechanicId();
            
            ServiceMechanic seviceMechanic = new ServiceMechanic(hours, comment, rate, serviceTicketID, serviceID, mechanicID);
            ServiceMechanicDAO seviceMechanicDAO = new ServiceMechanicDAO();
            
            String commentEncoded = URLEncoder.encode(comment, StandardCharsets.UTF_8.toString());
            String hoursEncoded = URLEncoder.encode(hours, StandardCharsets.UTF_8.toString());
            String rateEncoded = URLEncoder.encode(rate, StandardCharsets.UTF_8.toString());
            String serviceTicketIDEncoded = URLEncoder.encode(serviceTicketID, StandardCharsets.UTF_8.toString());
            String serviceIDEncoded = URLEncoder.encode(serviceID, StandardCharsets.UTF_8.toString());
            
            String successEncoded = URLEncoder.encode("UPDATE SUCCESSFUL", StandardCharsets.UTF_8.toString());
            String failEncoded = URLEncoder.encode("FAILED!", StandardCharsets.UTF_8.toString());
            
            String successURL = "UpdateServiceTicket.jsp?ERROR="+ successEncoded +"&txtHours="+ hoursEncoded +
                    "&txtComment="+ commentEncoded +"&txtRate="+ rateEncoded +"&txtServiceTicketId="+ serviceTicketIDEncoded +
                    "&txtServiceId="+ serviceIDEncoded;
            String failURL = "UpdateServiceTicket.jsp?ERROR="+ failEncoded +"&txtHours="+ hoursEncoded +
                    "&txtComment="+ commentEncoded +"&txtRate="+ rateEncoded +"&txtServiceTicketId="+ serviceTicketIDEncoded +
                    "&txtServiceId="+ serviceIDEncoded;
            
            if (seviceMechanicDAO.update(seviceMechanic)) {
                response.sendRedirect(successURL);
            } else {
                response.sendRedirect(failURL);
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
