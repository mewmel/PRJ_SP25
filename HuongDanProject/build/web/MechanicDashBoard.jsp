<%-- 
    Document   : MechanicDashBoard
    Created on : Feb 21, 2025, 6:25:05 PM
    Author     : trant
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="model.ServiceTicket"%>
<%@page import="model.Mechanic"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mechanic Dashboard</title>
    </head>
    <body>
        <%
            if (session.getAttribute("mechanic") != null) {
        %>
        <nav class="text">
            <ul class="menu" style="width: 100%; display: inline-block; list-style-type: none">
                <li>Welcome  <%= ((Mechanic) session.getAttribute("mechanic")).getMechanicName()%>  </li>
                <li><a href="#">logout</a></li>
                <li style="float:right;width: 30%; margin-right: 2%">
                    <form action="FindServiceTicketServlet">
                        <input type="text" name="txt_ticket" value="<%= (request.getParameter("txt_ticket") != null) ? request.getParameter("txt_ticket") : ""%>"/>
                        <input type="submit" value="search"/>
                    </form></li>

            </ul>
        </nav>
                        <!-- cho nay de xuat thong tin xe tim thay sau khi nhan "search"-->
           <%
            ArrayList<ServiceTicket> listSTicket =(ArrayList)request.getAttribute("TICKET_RESULT");
            if(listSTicket != null && !listSTicket.isEmpty()){
                for(ServiceTicket c:listSTicket){
           %>
            <p>
                    id:                      <%= c.getId()%><br/>
                    Date Received: <%= c.getDateReceived()%><br/>                  
                    Date Return: <%= c.getDateReturn()    %><br/>
                    <a href="UpdateServiceTicketServlet?id=<%=c.getId()%>">Update</a>
            </p>  
            <% 
                }
            } 
            %>
                      
                        
        <%
            } else {
                request.getRequestDispatcher("LoginStaffPage.jsp").forward(request, response);     
            }
            %>
    </body>
</html>
