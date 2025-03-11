/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.CarDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Car;

/**
 *
 * @author trant
 */
public class CarSaleServlet extends HttpServlet {

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
        String model = request.getParameter("txtmodel");
            CarDAO d = new CarDAO();
            ArrayList<Car> list = d.getAllCars();
            request.setAttribute("CAR_SALE_RESULT", list);
        if (model != null && !model.trim().isEmpty()) {
            list = d.getCarsByModel(model);
             // Cập nhật danh sách hiển thị
            request.setAttribute("SEARCH_MODEL", model); // Lưu lại giá trị tìm kiếm
        } else {
            list = d.getAllCars();
        }
        request.setAttribute("CAR_SALE_RESULT", list);
        request.getRequestDispatcher("ViewCar.jsp").forward(request, response); 
        
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
                String action = request.getParameter("action");
        CarDAO carDAO = new CarDAO();

                if ("more".equals(action)) {
        String carId = request.getParameter("carId");
        ArrayList<Car> list = carDAO.getAllCars();

        // Tìm xe có carId trong danh sách
        for (Car c : list) {
            if (c.getCarId().equals(carId)) {
                request.setAttribute("SELECTED_CAR", c);
                break;
            }
        }
        } else if ("update".equals(action)) {
            String carId = request.getParameter("carId");
            String statusName = request.getParameter("statusName");

            carDAO.updateCar(carId, statusName); // Cập nhật trạng thái của xe
            response.sendRedirect("CarSaleServlet"); // Load lại danh sách
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
