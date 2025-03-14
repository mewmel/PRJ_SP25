<%@page import="model.ServiceMechanic"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.ServiceTicket"%>
<%@page import="model.Mechanic"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
    <head>
        <title>Mechanic DashBoard</title>
    </head>
    <body>
        <c:if test="${not empty sessionScope.mechanic}">
            <nav class="text">
                <ul class="menu" style="width: 100%; display: inline-block; list-style-type: none; background: chartreuse">
                    <li>Welcome ${sessionScope.mechanic.mechanicName}</li>
                    <li><a href="LogoutMechaServlet">Logout</a></li>
                    <li style="float:right;width: 30%; margin-right: 2%">
                        <form action="FindServiceTicketServlet">
                            <input type="text" name="txt_ticket" value="${param.txt_ticket}"/>
                            <input type="submit" value="Search"/>
                        </form>
                    </li>
                </ul>
            </nav>

            <div style="width: 100%">
                <div style="width: 30%; float: left;">
                    <h1>MECHANIC DASHBOARD</h1>
                    <p><a href="ServiceView">Service</a></p>
                    <p><a href="ServiceTicketServlet">Your Service Ticket</a></p>
                </div>
                <div style="width: 30%; float: right">
                    <c:if test="${not empty SERVICE_MECHANIC_RESULT}">
                        <table>
                            <tr>
                                <th>Service Ticket ID</th>
                                <th>Service ID</th>
                                <th>Hours</th>
                                <th>Rate</th>
                                <th>Action</th>
                            </tr>
                            <c:forEach var="serviceMechanic" items="${SERVICE_MECHANIC_RESULT}">
                                <tr>
                                    <td>${serviceMechanic.serviceTicketID}</td>
                                    <td>${serviceMechanic.serviceID}</td>
                                    <td>${serviceMechanic.hours}</td>
                                    <td>${serviceMechanic.rate}</td>
                                    <td>
                                        <form action="DetailServiceTicket.jsp" method="POST">
                                            <input type="hidden" name="txtServiceTicketId" value="${serviceMechanic.serviceTicketID}"/>
                                            <input type="hidden" name="txtServiceId" value="${serviceMechanic.serviceID}"/>
                                            <input type="hidden" name="txtHours" value="${serviceMechanic.hours}"/>
                                            <input type="hidden" name="txtComment" value="${serviceMechanic.comment}"/>
                                            <input type="hidden" name="txtRate" value="${serviceMechanic.rate}"/>
                                            <input type="submit" value="Detail"/>
                                        </form>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                    </c:if>
                </div> 
                    <section style="width: 50%; float: left">
                        <c:if test="${not empty requestScope.SERVICE_RESULT}">
                            <table>
                                <tr>
                                    <th>Service Ticket ID</th>
                                    <th>Date Received</th>
                                    <th>Date Return</th>
                                    <th>Customer ID</th>
                                    <th>Car ID</th>
                                    <th>Action</th>
                                </tr>
                                <c:forEach var="serviceTicket" items="${requestScope.SERVICE_RESULT}">
                                    <tr>
                                        <td>${serviceTicket.id}</td>
                                        <td>${serviceTicket.dateReceived}</td>
                                        <td>${serviceTicket.dateReturn}</td>
                                        <td>${serviceTicket.cusID}</td>
                                        <td>${serviceTicket.carID}</td>
                                        <td>
                                            <form action="ServiceMechanicServlet" method="POST">
                                                <input type="hidden" name="txtServiceTicketId" value="${serviceTicket.id}"/>
                                                <input type="submit" value="show"/>
                                            </form>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </c:if>
                    </section>
                    <div style="width: 60%; float: left">
                        <c:if test="${not empty TICKET_RESULT}">
                            <table>
                                <tr>
                                    <th>Service Ticket ID</th>
                                    <th>Date Received</th>
                                    <th>Action</th>
                                </tr>
                                <c:forEach var="t" items="${TICKET_RESULT}">
                                    <tr>
                                        <td>${t.id}</td>
                                        <td>${t.dateReceived}</td>
                                        <td>
                                            <form action="DetailServiceTicketServlet">
                                                <input type="hidden" name="txtticketid" value="${t.id}"/>
                                                <input type="submit" value="Detail"/>
                                            </form>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </c:if>
                    </div>
                <div style="width: 20%; float:left">
                    <c:if test="${not empty FOUND_TICKET}">
                        <p>
                            Service Ticket ID: ${FOUND_TICKET.id} <br/>
                            Date Received: ${FOUND_TICKET.dateReceived}<br/>
                            Date Return: ${FOUND_TICKET.dateReturn}<br/>
                            Car ID: ${FOUND_TICKET.carID}
                        </p>
                    </c:if>
                </div>
            </div>
        </c:if>

        <c:if test="${empty sessionScope.mechanic}">
            <c:redirect url="LoginStaffPage.jsp"/>
        </c:if>
    </body>
</html>