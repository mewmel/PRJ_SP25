/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.WishlistDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Car;
import model.Customer;

/**
 *
 * @author trant
 */
public class SaveWishlistServlet extends HttpServlet {

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
            HttpSession s = request.getSession();
            Customer customer = (Customer) s.getAttribute("customer");

            if (customer == null) {
                request.setAttribute("ERROR", "You need to login first.");
                request.getRequestDispatcher("LoginPage.jsp").forward(request, response);
                return;
            }

            String cusId = String.valueOf(customer.getCusId());
            ArrayList<Car> wl = (ArrayList<Car>) s.getAttribute("WISHLIST");

            if (wl == null || wl.isEmpty()) {
                request.setAttribute("ERROR", "Your wishlist is empty.");
                request.getRequestDispatcher("ViewWishlist.jsp").forward(request, response);
                return;
            }

            WishlistDAO d = new WishlistDAO();
            int rs = d.createWishlist(cusId, wl);

            if (rs > 0) {
                s.removeAttribute("WISHLIST");
                request.setAttribute("SUCCESS", "Wishlist saved successfully!");
                request.getRequestDispatcher("CustomerDashBoard.jsp").forward(request, response);
            } else {
                request.setAttribute("ERROR", "Failed to save wishlist. Please try again.");
                request.getRequestDispatcher("ViewWishlist.jsp").forward(request, response);
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
