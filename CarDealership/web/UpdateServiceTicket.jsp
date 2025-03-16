<%-- 
    Document   : UpdateServiceTicket
    Created on : Mar 10, 2025, 9:34:34 PM
    Author     : ThinkPad
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Page</title>
    </head>
    <body style="background-color: lightcyan">
        <p><a href="MechanicDashBoard.jsp" style="padding: 5px 10px; border: none; background-color: #009999; color: white; border-radius: 5px; cursor: pointer;">back</a></p>
        <% if (session.getAttribute("mechanic") != null) {
        %>

        <nav style="width: 100%; display: inline-block; list-style-type: none; background: lightcyan">
            <h1>Service Ticket Update</h1>   
        </nav> 
        <form action="UpdateServiceTicketServlet" method="POST">
            <input type="hidden" name="txtServiceTicketId" value="<%= request.getParameter("txtServiceTicketId")%>">
            <input type="hidden" name="txtServiceId" value="<%= request.getParameter("txtServiceId")%>">
            
            <table>
                <tr><td>Service Ticket Id</td><td><%= request.getParameter("txtServiceTicketId")%></td></tr>
                <tr><td>Service Id</td><td> <%= request.getParameter("txtServiceId")%></td></tr>
                <tr><td>Hours</td><td> <input name="txtHours" value="<%= request.getParameter("txtHours")%>"> </td></tr>
                <tr><td>Comment</td><td> <input name="txtComment" value="<%= request.getParameter("txtComment")%>"> </td></tr>                                
                <tr><td>Rate</td><td> <input name="txtRate" value="<%= request.getParameter("txtRate")%>"> </td></tr>                                

                <tr>
                    <td><input type="submit" value="Save"></td>
                </tr>
            </table>
        </form>

        <p><%
            if (request.getParameter("ERROR") != null) {
                out.print(request.getParameter("ERROR"));
            }
            %></p>

        <%
            } else {
                request.getRequestDispatcher("LoginStaffPage.jsp").forward(request, response);
            }
        %>
    </body>
</html>
