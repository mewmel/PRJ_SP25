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
        <p><a href="SaleDashboard.jsp">back</a></p>
            <h1>List car</h1>
            <form action="CarSaleServlet">
                <input type="text" name="txtmodel" placeholder="Enter model..." value="${SEARCH_MODEL}">
                <input type="submit" value="Search">
                <input type="submit" value="Add">
            </form>
                <div style="display: flex;">
            <!-- form add -->
            <div style="width: 35%; margin-left: 20px; padding: 10px;">
                <h3>Information</h3>
                <form action="CarSaleServlet" method="POST">
                    <input type="hidden" name="action" value="Add">
                    <label>ID</label>
                    <input type="text" name="txtcarId"><br/>
                    <label>Model</label>
                    <input type="text" name="txtcarModel"><br/>
                    <label>Serial Number</label>
                    <input type="text" name="txtcarSerialNumber"><br/>
                    <label>Colour</label>
                    <input type="text" name="txtcarColour"><br/>
                    <label>Year</label>
                    <input type="text" name="txtcarYear"><br/>
                    <input type="submit" value="ADD">
                </form>
            </div>                

            <c:choose>
                <c:when test="${not empty requestScope.CAR_SALE_RESULT}">
                    
                        <table style="width: 60%">
                            <tr>
                                <th>ID</th>
                                <th>Model</th>
                                <th>Status</th>
                                <th>Action</th>
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
                        <!-- update status -->
                        <c:if test="${not empty SELECTED_CAR}">
                            <div style="width: 35%; margin-left: 20px; padding: 10px;">
                                
                                <form action="CarSaleServlet" method="POST">
                                    <h3> id ${SELECTED_CAR.carId}</h3>
                                    <input type="hidden" name="action" value="update">
                                    <input type="hidden" name="carId" value="${SELECTED_CAR.carId}">
                                   
                        
                                    <label>Status</label>
                                    <input type="text" name="statusName" value="${SELECTED_CAR.statusName}">
                                    
                                    <input type="submit" value="UPDATE"><br/>
                                </form>
                        <!--delete-->
                                <form action="CarSaleServlet" method="POST" onsubmit="return confirm('Bạn có chắc muốn xóa xe này không?');">
                                    <input type="hidden" name="action" value="delete">
                                    <input type="hidden" name="carId" value="${SELECTED_CAR.carId}">
                                    <input type="submit" value="DELETE" style="background-color: pink;">
                                </form>
                            </div>
                        </c:if>
                </div>
            </c:when>
                <c:otherwise>
                    <p>click "Search" để tải lại danh sách xe nha!</p>
                </c:otherwise>
            </c:choose>
        </c:if>
        <c:if test="${empty sessionScope.sale}">
            <c:redirect url="LoginStaffPage.jsp"/>
        </c:if>
    </body>
</html>

