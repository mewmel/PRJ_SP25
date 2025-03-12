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
import javax.servlet.http.HttpSession;
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
            CarDAO carDAO = new CarDAO();
            ArrayList<Car> list = new ArrayList<>();
            list = carDAO.getAllCars();
            String action = request.getParameter("action");
            
            // model search
            String model = request.getParameter("txtmodel");
            if (model != null && !model.trim().isEmpty()) {
                list = carDAO.getCarsByModel(model);
                request.setAttribute("SEARCH_MODEL", model);
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
        CarDAO carDAO = new CarDAO();
        ArrayList<Car> list = new ArrayList<>();
        list = carDAO.getAllCars();
        String action = request.getParameter("action");

        if (action.equals("more")) {
            String carId = request.getParameter("carId");
            Car selectedCar = carDAO.getCarById(carId);

            if (selectedCar != null) {
                request.setAttribute("SELECTED_CAR", selectedCar); // Gán xe được chọn vào request
            }

            request.setAttribute("CAR_SALE_RESULT", list);
            request.getRequestDispatcher("ViewCar.jsp").forward(request, response);
            return;
        }
        if (action.equals("update")) {
            String carId = request.getParameter("carId");
            String statusName = request.getParameter("statusName");

            if (carId != null && statusName != null) {
                carDAO.updateCar(carId, statusName);
                response.sendRedirect("CarSaleServlet"); //  Chuyển hướng để tránh lỗi
                return;
            }

        }
        if (action.equals("delete")) {
            String carId = request.getParameter("carId");

            if (carId != null) {
                carDAO.deleteCar(carId);
                response.sendRedirect("CarSaleServlet");
            return;
            }
        }
        if (action.equals("Add")) {
            String carId = request.getParameter("txtcarId");
            String carModel = request.getParameter("txtcarModel");
            String serialNumber = request.getParameter("txtcarSerialNumber");
            String colour = request.getParameter("txtcarColour");
            String yearStr = request.getParameter("txtcarYear");

            int year = 0;
            try {
                year = Integer.parseInt(yearStr);
            } catch (NumberFormatException e) {
                request.setAttribute("ERROR", "Year must be YYYY");
                request.getRequestDispatcher("ViewCar.jsp").forward(request, response);
                return;
            }
            String staName = "available";
            Car newCar = new Car(carId, carModel, serialNumber, colour, year, staName);
            carDAO.addCar(newCar);

            // Cập nhật lại danh sách xe ngay tại đây
            request.setAttribute("CAR_SALE_RESULT", list);

            // Chuyển hướng tới trang ViewCar.jsp kèm danh sách mới
            request.getRequestDispatcher("ViewCar.jsp").forward(request, response);
            return;
        } else {
            // Mặc định: Load danh sách xe nếu không có action cụ thể
            request.setAttribute("CAR_SALE_RESULT", list);
            request.getRequestDispatcher("ViewCar.jsp").forward(request, response);
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
