<%-- 
    Document   : DetailInvoice
    Created on : Mar 12, 2025, 1:04:45 AM
    Author     : ThinkPad
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1> Detail Invoice</h1> 
        
        <%
        request.setCharacterEncoding("utf-8"); 
        String customerID = request.getParameter("txtcustomerid");
        String carID = request.getParameter("txtcarid");
        String customerName = request.getParameter("txtcustomername");
        String carModel = request.getParameter("txtcarmodel");
        
        out.println("Customer ID: " + customerID + "<br>");
        out.println("Customer Name: " + customerName + "<br>");
        out.println("Car ID: " + carID + "<br>");
        out.println("Car Model: " + carModel + "<br>");
        
        %>
        <form action="ConfirmInvoiceServlet" accept-charset="utf-8">
            <p>Date <input type="date" name="date" required=""/>*</p>
            <p><input type="submit" value="confirm"/></p>
        </form>
        
    </body>
</html>
