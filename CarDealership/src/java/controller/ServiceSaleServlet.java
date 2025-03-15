/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ServiceDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Service;

/**
 *
 * @author trant
 */
public class ServiceSaleServlet extends HttpServlet {

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
            ServiceDAO seDAO = new ServiceDAO();
            ArrayList<Service> list = seDAO.getAllService();

            String seId = request.getParameter("txtname");
            if (seId != null && !seId.trim().isEmpty()) {
                ArrayList<Service> searchSe = seDAO.getServiceBy(seId);
                if (!searchSe.isEmpty()) {
                    list = searchSe;
                }
                request.setAttribute("SEARCH_NAME", seId);
            }
            request.setAttribute("SERVICE_MECHA_RESULT", list);
            request.getRequestDispatcher("ViewService.jsp").forward(request, response);
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
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        ServiceDAO seDAO = new ServiceDAO();
        ArrayList<Service> list = new ArrayList<>();
        list = seDAO.getAllService();       
        String action = request.getParameter("action");
        
        if (action != null) {

            if (action.equals("more")) {
                String seId = request.getParameter("seId");
                Service selectedSe = seDAO.getService(seId);
                if (selectedSe != null) {
                    request.getSession().setAttribute("SELECTED_SERVICE", selectedSe);
                }
                request.setAttribute("SERVICE_MECHA_RESULT", list);
                request.getRequestDispatcher("ViewService.jsp").forward(request, response);
                return;
            }
            if (action.equals("update")) {
                String seId = request.getParameter("txtseId");
                String name = request.getParameter("txtseName");
                String hourlyRate = request.getParameter("txtseRate");

                if (seId != null) {
                    seDAO.updateService(seId, name, hourlyRate);
                                    request.setAttribute("SERVICE_MECHA_RESULT", list);
                request.getRequestDispatcher("ViewService.jsp").forward(request, response);
                    return;
                }
            }

            if (action.equals("delete")) {
                String seId = request.getParameter("seId");

                if (seId != null) {
                    seDAO.deleteService(seId);
                    response.sendRedirect("ServiceSaleServlet");
                    return;
                }
            }

            if (action.equals("Add")) {
                String seId = request.getParameter("txtseId");
                String name = request.getParameter("txtseName");
                String hourlyRate = request.getParameter("txtseRate");

                Service newSeAdd = new Service(seId, name, hourlyRate);
                seDAO.addService(newSeAdd);

                //load lại ds service
                request.setAttribute("SERVICE_MECHA_RESULT", list);
                request.getRequestDispatcher("ViewService.jsp").forward(request, response);

            }
        } else {
            // Mặc định: Load danh sách xe nếu không có action cụ thể
            request.setAttribute("SERVICE_MECHA_RESULT", list);
            request.getRequestDispatcher("ViewService.jsp").forward(request, response);
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
