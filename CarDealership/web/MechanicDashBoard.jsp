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
                <ul class="menu" style="width: 100%; display: inline-block; list-style-type: none; background: #ccccff">
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
                    <p><a href="ViewService.jsp">Service</a></p>
                    <p><a href="ServiceTicketServlet">Your Service Ticket</a></p>
                </div>
                <div style="width: 100%">    
                    <div style="width: 50%; float: right">
                        <c:if test="${not empty SERVICE_MECHANIC_RESULT}">
                            <table>
                                <tr>
                                    <th>Service Ticket ID</th>
                                    <th>Service ID</th>
                                    <th>Hours</th>
                                    <th>Comment</th>
                                    <th>Rate</th>
                                    <th>Action</th>
                                </tr>
                                <c:forEach var="serviceMechanic" items="${SERVICE_MECHANIC_RESULT}">
                                    <tr>
                                        <td>${serviceMechanic.serviceTicketID}</td>
                                        <td>${serviceMechanic.serviceID}</td>
                                        <td>${serviceMechanic.hours}</td>
                                        <td>${serviceMechanic.comment}</td>
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
                    <section style="width: 50%; float: right">
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
                    <div style="width: 50%; float: left">
                        <c:if test="${not empty TICKET_RESULT}">
                            <c:forEach var="t" items="${TICKET_RESULT}">
                                <tr>
                                    <th>Service Ticket ID:</th><td>${t.id}</td><br/>
                                <th>Date Received: </th><td>${t.dateReceived}</td><br/>
                                <th>Date Return: </th><td>${t.dateReturn}</td><br/>
                                <th>Customer ID: </th><td>${t.cusID}</td><br/>
                                <th>Car ID: </th><td>${t.carID}</td><br/>
                                <br/><div style="width: 50%; float: left">
                                    </tr>
                                </c:forEach>
                            </c:if>
                        </div>
                    </div>
                </c:if>

                <c:if test="${empty sessionScope.mechanic}">
                    <c:redirect url="LoginStaffPage.jsp"/>
                </c:if>
                </body>
                </html>