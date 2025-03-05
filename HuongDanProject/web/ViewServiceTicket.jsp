<%-- 
    Document   : ViewServiceTicket
    Created on : Mar 4, 2025, 12:05:48 AM
    Author     : trant
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="model.ServiceTicket"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%-- nay dung cho sale person --%>
        <%
            ArrayList<ServiceTicket> ticket = (ArrayList)session.getAttribute("TICKET_LIST");
            if (ticket != null && !ticket.isEmpty()) {
                    for (ServiceTicket t : ticket) {
                            %>
                            <table>
                                <tr><td><%=t.getId() %></td></tr>
                                <tr><td><%=t.getDateReceived() %></td></tr>
                                <tr><td><%=t.getDateReturn() %></td></tr>
                                <tr><td><%=t.getCusID() %></td></tr>
                                <tr><td><%=t.getCarID() %></td></tr>
                                <tr><td><a href="#">create new</a></td></tr>
                            </table>
                            
                            <%
                        }
                }
            %>
    </body>
</html>
