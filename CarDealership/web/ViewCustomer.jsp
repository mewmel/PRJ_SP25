<%-- 
    Document   : ViewCustomer
    Created on : Mar 5, 2025, 9:20:04 PM
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
        <title>Customer Information</title>
    </head>
    <body style="background-color: lightcyan">
        
        <c:if test="${not empty sessionScope.sale}">
        <p><a href="SaleDashBoard.jsp" style="padding: 5px 10px; border: none; background-color: #009999; color: white; border-radius: 5px; cursor: pointer;">back</a></p>
        
            <h1>CUSTOMER LIST</h1>
            <form action="CusSaleServlet">
                <input type="text" name="txtcusId" placeholder="Enter customer's id..." value="${SEARCH_ID}">
                <input type="submit" value="Search">
            </form>
                <div style="display: flex;">
            <!-- form add -->
            <div style="width: 35%; margin-left: 20px; padding: 10px;">
                <h3>Information</h3>
                <form action="CusSaleServlet" method="POST">
                    <input type="hidden" name="action" value="Add">
                    <label>ID</label>
                    <input type="text" name="txtcusId"><br/>
                    <label>NAME</label>
                    <input type="text" name="txtcusName"><br/>
                    <label>PHONE</label>
                    <input type="text" name="txtcusPhone"><br/>
                    <label>SEX</label>
                    <input type="text" name="txtcusSex"><br/>
                    <label>ADDRESS</label>
                    <input type="text" name="txtcusAddress"><br/>
                    <input type="submit" value="ADD" style="background-color: #ccffcc">
                </form>
            </div>                
            <c:choose>
                <c:when test="${not empty requestScope.CUS_SALE_RESULT}">
                        <table style="width: 60%">
                            <tr>
                                <th>ID</th>
                                <th>NAME</th>
                                <th>PHONE</th>
                                <th>SEX</th>
                                <th>ADDRESS</th>
                            </tr>
                            <c:forEach var="c" items="${requestScope.CUS_SALE_RESULT}">
                                <tr>
                                    <td>${c.cusId}</td>
                                    <td>${c.cusName}</td>
                                    <td>${c.phone}</td>
                                    <td>${c.sex}</td>
                                    <td>${c.cusAddress}</td>
                                    <td>
                                        <form action="CusSaleServlet" method="POST" style="display:inline;">
                                            <input type="hidden" name="cusId" value="${c.cusId}">
                                            <button type="submit" value="more" name="action">more</button>
                                        </form>
                                        <!--<form action="ViewServiceTicketServlet" method="post">
                                            <input type="hidden" name="cusId" value="${c.cusId}">
                                            <button type="submit" name="action">view</button>
                                        </form>-->


                                    </td>
                                </tr>
                            </c:forEach>
                        </table>  
                         <c:if test="${not empty SELECTED_CUS}">
                            <div style="width: 35%; margin-left: 20px; padding: 10px;">
                                <form action="CusSaleServlet" method="POST">
                                    <h3> CUSTOMER'S ID ${SELECTED_CUS.cusId}</h3>
                                    <input type="hidden" name="cusId" value="${SELECTED_CUS.cusId}"><br/>
                                    <label>NAME</label><br/>
                                    <input type="text" name="txtcusName" value="${SELECTED_CUS.cusName}"><br/>
                                    <label>PHONE</label><br/>
                                    <input type="text" name="txtcusPhone" value="${SELECTED_CUS.phone}"><br/>
                                    <label>SEX</label><br/>
                                    <input type="text" name="txtcusSex" value="${SELECTED_CUS.sex}"><br/>
                                    <label>ADDRESS</label><br/>
                                    <input type="text" name="txtcusAd" value="${SELECTED_CUS.cusAddress}"><br/>
                                    <input type="hidden" name="action" value="update">
                                    <input type="submit" value="UPDATE"><br/>
                                </form>
                        <!--delete-->
                                <form action="CusSaleServlet" method="POST" onsubmit="return confirm('Are you sure?');">
                                    <input type="hidden" name="cusId" value="${SELECTED_CUS.cusId}">
                                    <input type="hidden" name="action" value="delete">
                                    
                                    <input type="submit" value="DELETE" style="background-color: #ffcccc;">
                                </form>
                            </div>
                        </c:if>
                </div>
            </c:when>
                <c:otherwise>
                    <p>Click "Search" to reload the customer list <3</p>
                </c:otherwise>
            </c:choose>
        </c:if>
        <c:if test="${empty sessionScope.sale}">
            <c:redirect url="LoginStaffPage.jsp"/>
        </c:if>
    </body>
</html>
