<%@page import="model.ServiceMechanic"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.ServiceTicket"%>
<%@page import="model.Mechanic"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mechanic Dashboard</title>
    </head>
    <body>
        <c:if test="${not empty sessionScope.mechanic}">
            <!-- Navigation -->
            <nav style="background: #009999; padding: 10px;">
                <ul class="menu" style="display: flex; justify-content: space-between; align-items: center; list-style-type: none; margin: 0; padding: 0;">
                    <li style="font-weight: bold;">Welcome ${sessionScope.mechanic.mechanicName}</li>
                    <li><a href="LogoutMechaServlet" style="color: #003333; text-decoration: none; font-weight: bold;">Logout</a></li>
                </ul>
            </nav>

            <div style="display: flex; gap: 20px; padding: 20px;">
                <!-- Sidebar Menu -->
                <div style="width: 30%; background-color: #f8f9fa; padding: 15px; border-radius: 8px;">
                    <h2>MECHANIC DASHBOARD</h2>
                    <p><a href="ViewService.jsp">Service</a></p>
                    <p><a href="ServiceTicketServlet">Your Service Ticket</a></p>
                    <form action="FindServiceTicketServlet">
                        <input type="text" name="txt_ticket" value="${param.txt_ticket}" />
                        <input type="submit" value="Search"/>
                    </form>
                </div>
                        <!-- Main Content -->
                <div style="width: 65%;">
                    <h2>Service Ticket List</h2>
                    <c:if test="${not empty requestScope.SERVICE_DETAIL}">
                        <table style="width: 100%; border-collapse: collapse;">
                            <tr style="background-color: #ccccff; color: black;">
                                <th>Service Ticket ID</th>
                                <th>Service ID</th>
                                <th>Hours</th>
                                <th>Comment</th>
                                <th>Rate</th>
                                <th>Action</th>
                            </tr>
                            <c:forEach var="ticket" items="${requestScope.SERVICE_DETAIL}">
                                <tr>
                                    <td>${ticket.serviceTicketID}</td>
                                    <td>${ticket.serviceID}</td>
                                    <td>${ticket.hours}</td>
                                    <td>${ticket.comment}</td>
                                    <td>${ticket.rate}</trated>
                                    <td>
                                        <form action="DetailServiceTicket.jsp" method="POST">
                                            <input type="hidden" name="txtServiceTicketId" value="${ticket.serviceTicketID}"/>
                                            <input type="hidden" name="txtServiceId" value="${ticket.serviceID}"/>
                                            <input type="hidden" name="txtHours" value="${ticket.hours}"/>
                                            <input type="hidden" name="txtComment" value="${ticket.comment}"/>
                                            <input type="hidden" name="txtRate" value="${ticket.rate}"/>
                                            <input type="submit" value="Detail"/>
                                        </form>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                    </c:if>
                    <c:if test="${empty requestScope.SERVICE_DETAIL}">
                        <p>No service tickets available.</p>
                    </c:if>
                        <!-- Service Result -->
                    <h2>Service Results</h2>
                    <c:if test="${not empty requestScope.SERVICE_RESULT}">
                        <table style="width: 100%; border-collapse: collapse;">
                            <tr style="background-color: #ccccff; color: black;">
                                <th>Service Ticket ID</th>
                                <th>Date Received</th>
                                <th>Date Return</th>
                                <th>Customer ID</th>
                                <th>Car ID</th>
                                <th>Action</th>
                            </tr>
                            <c:forEach var="result" items="${requestScope.SERVICE_RESULT}">
                                <tr>
                                    <td>${result.id}</td>
                                    <td>${result.dateReceived}</td>
                                    <td>${result.dateReturn}</td>
                                    <td>${result.cusID}</td>
                                    <td>${result.carID}</td>
                                    <td>
                                        <form action="ServiceTicketServlet" method="POST">
                                            <input type="hidden" name="txtServiceTicketId" value="${result.id}"/>
                                            <input type="submit" value="Show"/>
                                        </form>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                    </c:if>
                    <c:if test="${empty requestScope.SERVICE_RESULT}">
                        <p>No service results found.</p>
                    </c:if>
                        <!-- Ticket Details -->
                    <h2>Ticket Details</h2>
                    <c:if test="${not empty requestScope.TICKET_RESULT}">
                        <table style="width: 100%; border-collapse: collapse;">
                            <tr style="background-color: #ccccff; color: black;">
                                <th>Service Ticket ID</th>
                                <th>Date Received</th>
                                <th>Date Return</th>
                                <th>Customer ID</th>
                                <th>Car ID</th>
                            </tr>
                            <c:forEach var="t" items="${requestScope.TICKET_RESULT}">
                                <tr>
                                    <td>${t.id}</td>
                                    <td>${t.dateReceived}</td>
                                    <td>${t.dateReturn}</td>
                                    <td>${t.cusID}</td>
                                    <td>${t.carID}</td>
                                </tr>
                            </c:forEach>
                        </table>
                    </c:if>
                    <c:if test="${empty requestScope.TICKET_RESULT}">
                        <p>No ticket details found.</p>
                    </c:if>

                </div>
            </div>
        </c:if>
        <!-- Error Message -->
        <p style="color: red; font-weight: bold;">
            ${requestScope.ERROR}
        </p>

        <c:if test="${empty sessionScope.mechanic}">
            <c:redirect url="LoginStaffPage.jsp"/>
        </c:if>
    </body>
</html>