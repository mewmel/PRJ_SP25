<%-- 
    Document   : DetailServiceTicket
    Created on : Mar 10, 2025, 9:32:58 PM
    Author     : ThinkPad
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Service Ticket Detail</title>
    </head>
    <body>
        <nav style="width: 100%; display: inline-block; list-style-type: none; background: lightcyan">
            <h1>Service Ticket Detail</h1>   
        </nav> 
        <form action="UpdateServiceTicket.jsp" method="POST">
            
            <input type="hidden" name="txtServiceTicketId" value="<%= request.getParameter("txtServiceTicketId") %>">
            <input type="hidden" name="txtServiceId" value="<%= request.getParameter("txtServiceId") %>">
            <input type="hidden" name="txtHours" value="<%= request.getParameter("txtHours") %>">
            <input type="hidden" name="txtComment" value="<%= request.getParameter("txtComment") %>">
            <input type="hidden" name="txtRate" value="<%= request.getParameter("txtRate") %>">
            
            <table>
                <tr><td>Service Ticket Id</td> <td> <%= request.getParameter("txtServiceTicketId") %> </td></tr>
                <tr><td>Service Id</td> <td><%= request.getParameter("txtServiceId") %> </td></tr>
                <tr><td>Hours</td> <td> <%= request.getParameter("txtHours") %> </td></tr>
                <tr><td>Comment</td> <td> <%= request.getParameter("txtComment") %> </td></tr>                                
                <tr><td>Rate</td> <td> <%= request.getParameter("txtRate") %> </td></tr>                                

                <tr>
                    <td><input type="submit" value="Update"></td>
                </tr>
            </table>
        </form>
    </body>
</html>
