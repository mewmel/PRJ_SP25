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
    // Lấy invoiceID từ request
    Integer invoiceID = (Integer) request.getAttribute("invoiceID");
    if (invoiceID == null) {
               invoiceID =1; // Giá trị mặc định nếu không có dữ liệu
    }
    %>


    <form action="ConfirmInvoiceServlet" accept-charset="utf-8">
        <p>Customer ID: <%= request.getAttribute("customerID")%></p>
        <p>Customer Name: <%= request.getAttribute("customerName")%></p>
        <p>Car Model: <%= request.getAttribute("carModel")%></p>
        <p>Car ID: <%= request.getAttribute("carID")%></p>
        <p>Invoice ID: <%= invoiceID%></p>
        <p>Date <input type="hiden" name="txtdate" required=""/>*</p>
        <p><input type="submit" value="Confirm"/></p>
    </form>

    </body>
</html>
