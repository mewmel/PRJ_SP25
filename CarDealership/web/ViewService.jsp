<%-- 
    Document   : ServiceView
    Created on : Mar 14, 2025, 10:42:34 PM
    Author     : trant
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Service</title>
    </head>
    <body> 
        <c:if test="${not empty sessionScope.mechanic}">
        <p><a href="MechanicDashBoard.jsp">back</a></p>
            <h1>SERVICE LIST</h1>
            <form action="ServiceSaleServlet">
                <input type="text" name="txtname" placeholder="Enter service's name..." value="${SEARCH_NAME}">
                <input type="submit" value="Search">
                
            </form>
                <div style="display: flex;">
            <!-- form add -->
            <div style="width: 35%; margin-left: 20px; padding: 10px;">
                <h3>Information</h3>
                <form action="ServiceSaleServlet" method="POST">
                    <input type="hidden" name="action" value="Add">
                    <label>ID</label>
                    <input type="text" name="txtseId"><br/>
                    <label>NAME</label>
                    <input type="text" name="txtseName"><br/>
                    <label>HOURLY RATE</label>
                    <input type="text" name="txtseRate"><br/>
                    <input type="submit" value="ADD">
                </form>
            </div>                
            <c:choose>
                <c:when test="${not empty requestScope.SERVICE_MECHA_RESULT}">
                    
                        <table style="width: 60%">
                            <tr>
                                <th>ID</th>
                                <th>NAME</th>
                                <th>HOURLY RATE</th>
                                <th>ACTION</th>
                            </tr>
                            <c:forEach var="s" items="${requestScope.SERVICE_MECHA_RESULT}">
                                <tr>
                                    <td>${s.serviceId}</td>
                                    <td>${s.serviceName}</td>
                                    <td>${s.hourlyRate}</td>
                                    <td>
                                        <form action="ServiceSaleServlet" method="POST" style="display:inline;">
                                            <input type="hidden" name="seId" value="${s.serviceId}">
                                            <input type="submit" value="more" name="action">
                                        </form>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                        <!-- update status -->
                        <c:if test="${not empty SELECTED_SERVICE}">
                            <div style="width: 35%; margin-left: 20px; padding: 10px;">
                                <form action="ServiceSaleServlet" method="POST">
                                    <h3>SERVICE'S ID ${SELECTED_SERVICE.serviceId}</h3>
                                    <input type="hidden" name="seId" value="${SELECTED_SERVICE.serviceId}">
                                    <label>NAME</label><br/>
                                    <input type="text" name="seName" value="${SELECTED_SERVICE.serviceName}"><br/>
                                    <label>HOURLY RATE</label><br/>
                                    <input type="text" name="seRate" value="${SELECTED_SERVICE.hourlyRate}"><br/>
                                    <input type="hidden" name="action" value="update">
                                    <input type="submit" value="UPDATE"><br/>
                                </form>
                        <!--delete-->
                                <form action="ServiceSaleServlet" method="POST" onsubmit="return confirm('Are you sure?');">
                                    <input type="hidden" name="action" value="delete">
                                    <input type="hidden" name="seId" value="${SELECTED_SERVICE.serviceId}">
                                    <input type="submit" value="DELETE" style="background-color: pink;">
                                </form>
                            </div>
                        </c:if>
                </div>
            </c:when>
                <c:otherwise>
                    <p>Click "Search" to reload the service list <3</p>
                </c:otherwise>
            </c:choose>
        </c:if>
        <c:if test="${empty sessionScope.mechanic}">
            <c:redirect url="LoginStaffPage.jsp"/>
        </c:if>
    </body>
</html>
