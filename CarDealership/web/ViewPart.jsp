<%-- 
    Document   : parts
    Created on : Mar 16, 2025, 8:58:17 PM
    Author     : ThinkPad
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Part</title>
    </head>
    <body style="background-color: lightcyan">
        <c:if test="${not empty sessionScope.sale}">
            <p><a href="SaleDashBoard.jsp" style="padding: 5px 10px; border: none; background-color: #009999; color: white; border-radius: 5px; cursor: pointer;">back</a></p>
            <h1>PARTS LIST</h1>
            <form action="ViewPartsServlet">
                <input type="text" name="txtsearchname" placeholder="Enter name..." value="${SEARCH_NAME}">
                <input type="submit" value="Search">
            </form>
            <div style="display: flex;">
                <!-- form add -->
                <div style="width: 35%; margin-left: 20px; padding: 10px;">
                    <h3>Information</h3>
                    <form action="ViewPartsServlet" method="POST">
                        <input type="hidden" name="action" value="Add">
                        <label>Name</label>
                        <input type="text" name="txtName"><br/>
                        <label>Purchase price</label>
                        <input type="text" name="txtPurchasePrice"><br/>
                        <label>Retail Price</label>
                        <input type="text" name="txtRetailPrice"><br/>
                        <input type="submit" value="ADD">
                    </form>
                </div>                 

                <c:choose>
                    <c:when test="${not empty requestScope.PARTS_RESULT}">
                        <table style="width: 60%">
                            <tr>
                                <th>ID</th>
                                <th>Name</th>
                                <th>Purchase price</th>
                                <th>Retail Price</th>
                                <th>Action</th>
                            </tr>
                            <c:forEach var="c" items="${requestScope.PARTS_RESULT}">
                                <tr>
                                    <td>${c.partID}</td>
                                    <td>${c.partName}</td>
                                    <td>${c.purchasePrice}</td>
                                    <td>${c.retailPrice}</td>                                   
                                    <td>
                                        <!-- Add actions for Edit or Delete -->
                                        <form action="UpdateParts.jsp" method="POST" style="display:inline;">
                                            
                                            <input type="hidden" name="partName" value="${c.partName}">
                                            <input type="hidden" name="purchasePrice" value="${c.purchasePrice}">
                                            <input type="hidden" name="retailPrice" value="${c.retailPrice}">
                                            <input type="hidden" name="partID" value="${c.partID}">
                                            <input type="submit" value="Edit">
                                        </form>
                                        <form action="ViewPartsServlet" method="POST" style="display:inline;">
                                            <input type="hidden" name="action" value="Delete">
                                            <input type="hidden" name="partID" value="${c.partID}">
                                            <input type="submit" value="Delete">
                                        </form>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                    </c:when>
                </c:choose>             
            </div>
        </c:if>
            
    </body>
</html>
