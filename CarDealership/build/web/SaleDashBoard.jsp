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
        <title>Sale DashBoard</title>
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
                <p><a href="ViewCar.jsp">Car</a></p>
                <p><a href="ViewCustomer.jsp">Customer</a></p>
                <p><a href="CreateInvoice.jsp">Create Invoice For New Customer</a></p>
            </div>          
                    
        <%
            } else {
                request.getRequestDispatcher("LoginStaffPage.jsp").forward(request, response);     
            }
            %>
    </body>
</html>
