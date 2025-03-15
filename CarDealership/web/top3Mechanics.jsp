<%-- 
    Document   : top3Mechanics
    Created on : Mar 16, 2025, 2:51:47 AM
    Author     : ThinkPad
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="model.Mechanic"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h2>Top 3 mechanics assigned to the most repairs</h2>
    <table border="1">
        <tr>
            <th>Mechanic ID</th>
            <th>Mechanic Name</th>
            <th>Total Services</th>
        </tr>
        <%
            ArrayList<Mechanic> top3Mechanics = (ArrayList<Mechanic>) request.getAttribute("top3Mechanics");
            if (top3Mechanics != null && !top3Mechanics.isEmpty()) {
                for (Mechanic mechanic : top3Mechanics) {
        %>
        <tr>
            <td><%= mechanic.getMechanicId() %></td>
            <td><%= mechanic.getMechanicName() %></td>
            <td><%= mechanic.getTotal_Services()%></td>
            
        </tr>
        <%
                }
            } else {
        %>
        <tr>
            <td colspan="2">Không có dữ liệu</td>
        </tr>
        <%
            }
        %>
    </table>
    </body>
</html>
