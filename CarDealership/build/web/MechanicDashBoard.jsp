<%@page import="model.ServiceMechanic"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.ServiceTicket"%>
<%@page import="model.Mechanic"%>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <title>Mechanic DashBoard</title>
    </head>
    <body>
        <%
            if (session.getAttribute("mechanic") != null) {
        %>
        <nav class="text">
            <ul class="menu" style="width: 100%; display: inline-block; list-style-type: none; background: yellowgreen">
                <li>Welcome  <%= ((Mechanic) session.getAttribute("mechanic")).getMechanicName()%>  </li>
                <li ><a href="LogoutStaffServlet">logout</a></li>
                <li style="float:right;width: 30%; margin-right: 2%">
                    <form action="FindServiceTicketServlet">
                        <input type="text" name="txt_ticket" value="<%= (request.getParameter("txt_ticket") != null) ? request.getParameter("txt_ticket") : ""%>"/>
                        <input type="submit" value="search"/>
                    </form></li>

            </ul>
        </nav>
        <div style="width: 100%">
            <%--menu--%>
            <div style="width: 30%; float: left;">        
                <h1>MECHANIC DASHBOARD</h1>
                <p><a href="ServiceTicketServlet">Service Ticket</a></p>
            </div>      


            <div style="width: 100%; float: left">
                <section style="width: 50%; float: left" >
                    <%
                        ArrayList<ServiceTicket> serviceTickets = (ArrayList) request.getAttribute("SERVICE_RESULT");
                        if (serviceTickets != null && !serviceTickets.isEmpty()) {
                            for (ServiceTicket serviceTicket : serviceTickets) {
                    %>
                    <table>
                        <tr>
                            <th>Service Ticket ID</th>
                            <th>Date Received</th>
                            <th>Date Returned</th>
                            <th>Customer Id</th>
                            <th>Car ID</th>
                        </tr>
                        <form action="ServiceMechanicServlet" method="POST">
                            <input type="hidden" name="txtServiceTicketId" value="<%=serviceTicket.getId()%>">
                            <tr>
                                <td><%=  serviceTicket.getId()%></td>
                                <td><%=  serviceTicket.getDateReceived()%></td>
                                <td><%=  serviceTicket.getDateReturn()%></td>
                                <td><%=  serviceTicket.getCusID()%></td>
                                <td><%=  serviceTicket.getCarID()%></td>
                                <td><input type="submit" value="show"></td>

                            </tr>
   </form>
                    </table>       
                    <%}
                        }
                    %>

                    <%
                        ArrayList<ServiceMechanic> serviceMechanics = (ArrayList) request.getAttribute("SERVICE_MECHANIC_RESULT");
                        if (serviceMechanics != null && !serviceMechanics.isEmpty()) {
                            for (ServiceMechanic serviceMechanic : serviceMechanics) {
                    %>
                    <table>
                        <tr>
                            <th>Service Ticket ID</th>
                            <th>Service ID</th>
                            <th>Hours</th>
                            <th>Rate</th>
                        </tr>
                        <form action="DetailServiceTicket.jsp" method="POST">
                            <input type="hidden" name="txtServiceTicketId" value="<%= serviceMechanic.getServiceTicketID()%>">
                            <input type="hidden" name="txtServiceId" value="<%= serviceMechanic.getServiceID()%>">
                            <input type="hidden" name="txtHours" value="<%= serviceMechanic.getHours()%>">
                            <input type="hidden" name="txtComment" value="<%= serviceMechanic.getComment()%>">
                            <input type="hidden" name="txtRate" value="<%= serviceMechanic.getRate()%>">
                            <tr>
                                <td><%=  serviceMechanic.getServiceTicketID()%></td>
                                <td><%=  serviceMechanic.getServiceID()%></td>
                                <td><%=  serviceMechanic.getHours()%></td>
                                <td><%=  serviceMechanic.getRate()%></td>
                                <td><input type="submit" value="Detail"></td>
                            </tr>
                        </form>
                    </table>       
                    <%}
                        }
                    %>
                </section>
            </div>   
        </div> 
        <%
            } else {
                request.getRequestDispatcher("LoginStaffPage.jsp").forward(request, response);
            }
        %>
    </body>
</html>                         

