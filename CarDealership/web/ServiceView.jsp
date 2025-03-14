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
        <c:if test="${not empty sessionScope.sale}">
        <p><a href="SaleDashBoard.jsp">back</a></p>
            <h1>SERVICE LIST</h1>
            <form action="ServiceSaleServlet">
                <input type="text" name="txtname" placeholder="Enter service's name..." value="${SEARCH_NAME}">
                <input type="submit" value="Search">
                <input type="submit" value="Add">
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
                                    <td>${s.carId}</td>
                                    <td>${c.model}</td>
                                    <td>${c.statusName}</td>
                                    <td>
                                        <form action="ServiceSaleServlet" method="POST" style="display:inline;">
                                            <input type="hidden" name="carId" value="${c.carId}">
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
                                    <h3> id ${SELECTED_SERVICE.carId}</h3>
                                    <input type="hidden" name="action" value="update">
                                    <input type="hidden" name="carId" value="${SELECTED_SERVICE.carId}">
                                   
                        
                                    <label>Status</label>
                                    <input type="text" name="statusName" value="${SELECTED_SERVICE.statusName}">
                                    
                                    <input type="submit" value="UPDATE"><br/>
                                </form>
                        <!--delete-->
                                <form action="ServiceSaleServlet" method="POST" onsubmit="return confirm('Are you sure?');">
                                    <input type="hidden" name="action" value="delete">
                                    <input type="hidden" name="carId" value="${SELECTED_SERVICE.carId}">
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
        <c:if test="${empty sessionScope.sale}">
            <c:redirect url="LoginStaffPage.jsp"/>
        </c:if>
    </body>
</html>
