/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.PartsDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Parts;

/**
 *
 * @author ThinkPad
 */
public class ViewPartsServlet extends HttpServlet {

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
//            String searchName = request.getParameter("searchName"); // Lấy tên từ request
//    PartsDAO dao = new PartsDAO();
//    ArrayList<Parts> list = dao.searchPartsByName(searchName);
//
//    request.setAttribute("PARTS_LIST", list);
//    request.setAttribute("SEARCH_NAME", searchName); // Lưu lại giá trị tìm kiếm để hiển thị
//    request.getRequestDispatcher("parts.jsp").forward(request, response);
//    }
//    }
    
    
          PartsDAO partsDAO = new PartsDAO();
            ArrayList<Parts> list = new ArrayList<>();
            list = partsDAO.getAllParts();

            // model search
            String searchname = request.getParameter("txtsearchname");
            if (searchname != null && !searchname.trim().isEmpty()) {
                list = partsDAO.searchPartsByName(searchname);
                request.setAttribute("SEARCH_NAME", searchname);
            }

            request.setAttribute("PARTS_RESULT", list);
            request.getRequestDispatcher("parts.jsp").forward(request, response);
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
        PartsDAO partsDAO = new PartsDAO();
        ArrayList<Parts> list = new ArrayList<>();
        String action = request.getParameter("action");
        
         
        
        if(action.equals("Edit")){
            
            String purchasePrice = request.getParameter("purchasePrice");
            String retailPrice = request.getParameter("retailPrice");
            String partID = request.getParameter("partID");
            
            if( purchasePrice!= null && retailPrice !=null){
                if (partsDAO.updateParts(partID, purchasePrice, retailPrice)) {
                    request.setAttribute("ERROR", "UPDATE SUCCESSFUL");
                } else {
                    request.setAttribute("ERROR", "UPDATE FAILED");
                }
                
                list = partsDAO.getAllParts();

                // model search
                String searchname = request.getParameter("txtsearchname");
                if (searchname != null && !searchname.trim().isEmpty()) {
                    list = partsDAO.searchPartsByName(searchname);
                    request.setAttribute("SEARCH_NAME", searchname);
                }

                request.setAttribute("PARTS_RESULT", list);
                request.getRequestDispatcher("parts.jsp").forward(request, response);
                
            }  
        } else {
            if (action.equalsIgnoreCase("Add")) {
                int maxPartID = partsDAO.getMaxPartID();

                String partName = request.getParameter("txtName");
                String purchasePrice = request.getParameter("txtPurchasePrice");
                String retailPrice = request.getParameter("txtRetailPrice");
                
                if (partsDAO.savePart(String.valueOf(maxPartID), partName, purchasePrice, retailPrice)) {
                    request.setAttribute("ERROR", "ADD COMPLETED!");
                } else{
                    request.setAttribute("ERROR", "ADD FAILED!");
                }
                
                list = partsDAO.getAllParts();

                // model search
                String searchname = request.getParameter("txtsearchname");
                if (searchname != null && !searchname.trim().isEmpty()) {
                    list = partsDAO.searchPartsByName(searchname);
                    request.setAttribute("SEARCH_NAME", searchname);
                }

                request.setAttribute("PARTS_RESULT", list);
                request.getRequestDispatcher("parts.jsp").forward(request, response);
            } else {
                if (action.equalsIgnoreCase("Delete")) {
                    String partID = request.getParameter("partID");
                    if (partsDAO.DeletePart(partID)) {
                        request.setAttribute("ERROR", "DELETE COMPLETED!");
                    } else {
                        request.setAttribute("ERROR", "ADD FAILED!");
                    }
                    
                list = partsDAO.getAllParts();

                // model search
                String searchname = request.getParameter("txtsearchname");
                if (searchname != null && !searchname.trim().isEmpty()) {
                    list = partsDAO.searchPartsByName(searchname);
                    request.setAttribute("SEARCH_NAME", searchname);
                }

                request.setAttribute("PARTS_RESULT", list);
                    request.getRequestDispatcher("parts.jsp").forward(request, response);
                }
            }
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
