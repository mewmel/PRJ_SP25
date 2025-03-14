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

    <form action="ConfirmInvoiceServlet" accept-charset="utf-8">
        <input type="hidden" name="txtcustomerid" value="<%= request.getAttribute("cusID")%>">
        <input type="hidden" name="txtcarid" value="<%= request.getAttribute("carID")%>">
        
        
        <p>Customer ID: <%= request.getAttribute("cusID")%></p>
        <p>Customer Name: <%= request.getAttribute("cusName")%></p>
        <p>Car Model: <%= request.getAttribute("carModel")%></p>
        <p>Car ID: <%= request.getAttribute("carID")%></p>
        <p><input type="submit" value="Confirm"/></p>
    </form>

    </body>
</html>
