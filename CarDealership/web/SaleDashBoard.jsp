<%-- 
    Document   : SaleDashBoard
    Created on : Feb 21, 2025, 6:24:44 PM
    Author     : trant
--%>

<%@page import="model.SalePerson"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.ServiceTicket"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mechanic DashBoard</title>
    </head>
    <body>
        <%
            if (session.getAttribute("sale") != null) {
        %>
        <nav class="text">
            <ul class="menu" style="width: 100%; display: inline-block; list-style-type: none">
                <li>Welcome  <%= ((SalePerson) session.getAttribute("sale")).getSaleName()%>  </li>
                <li><a href="#">logout</a></li>
                <li style="float:right;width: 30%; margin-right: 2%">
                        
                             
            <div style="width: 100%">            
                <h1>DASHBOARD</h1>
                <p><a href="ViewCar.jsp">invoices</a></p>
                <p><a href="ViewWishlist.jsp">view wishlist</a></p>
                <p><a href="ServiceTicketServlet">service ticket</a></p>
            </div> 
                    
                    
        <%
            } else {
                request.getRequestDispatcher("LoginStaffPage.jsp").forward(request, response);     
            }
            %>
    </body>
</html>
