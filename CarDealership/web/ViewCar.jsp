<%-- 
    Document   : ViewCar
    Created on : Mar 5, 2025, 9:18:28 PM
    Author     : trant
--%>

<%@page import="model.Car"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Car Information</title>
    </head>
    <body>
        <c:if test="${not empty sessionScope.sale}">
            <h1>Danh sách xe</h1>
            <form action="CarSaleServlet">
                <input type="text" name="txtmodel" placeholder="Enter model..." value="${SEARCH_MODEL}">
                <input type="submit" value="search">
                <input type="submit" value="create">
            </form>
            <c:choose>
                <c:when test="${not empty requestScope.CAR_SALE_RESULT}">
                    <div style="display: flex;">
                        <!-- Bảng danh sách xe -->
                        <table style="width: 60%">
                            <tr>
                                <th>ID</th>
                                <th>Model</th>
                                <th>Status</th>
                                <th>...</th>
                            </tr>
                            <c:forEach var="c" items="${requestScope.CAR_SALE_RESULT}">
                                <tr>
                                    <td>${c.carId}</td>
                                    <td>${c.model}</td>
                                    <td>${c.statusName}</td>
                                    <td>
                                        <form action="CarSaleServlet" method="POST" style="display:inline;">
                                            <input type="hidden" name="carId" value="${c.carId}">
                                            <input type="submit" value="more" name="action">
                                        </form>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                        <!-- Form chỉnh sửa xe -->
                        <c:if test="${not empty SELECTED_CAR}">
                            <div style="width: 35%; margin-left: 20px; padding: 10px; border: 1px solid black;">
                                <h3>Car detail</h3>
                                <form action="CarSaleServlet" method="POST">
                                    <input type="hidden" name="action" value="update">
                                    <input type="hidden" name="carId" value="${SELECTED_CAR.carId}">
                                    <label>Model:</label>
                                    <input type="text" name="model" value="${SELECTED_CAR.model}"><br/>
                                    <label>Serial Number:</label>
                                    <input type="text" name="serialNumber" value="${SELECTED_CAR.serialNumber}"><br/>
                                    <label>Colour:</label>
                                    <input type="text" name="colour" value="${SELECTED_CAR.colour}"><br/>
                                    <label>Year:</label>
                                    <input type="number" name="year" value="${SELECTED_CAR.year}"><br/>
                                    <label>Status:</label>
                                    <select name="statusId">
                                        <c:forEach var="s" items="${requestScope.STATUS_LIST}">
                                            <option value="${s.statusId}" ${s.statusId == SELECTED_CAR.statusId ? 'selected' : ''}>
                                                ${s.statusName}
                                            </option>
                                        </c:forEach>
                                    </select><br/>
                                    <input type="submit" value="ok">
                                </form>
                            </div>
                        </c:if>
                    </div>
                </c:when>
                <c:otherwise>
                    <p>Không có xe nào trong danh sách!</p>
                </c:otherwise>
            </c:choose>
        </c:if>
        <c:if test="${empty sessionScope.sale}">
            <c:redirect url="LoginStaffPage.jsp"/>
        </c:if>
    </body>
</html>

