<%-- 
    Document   : serviceticketforcustomer
    Created on : Mar 16, 2025, 2:50:42 AM
    Author     : ThinkPad
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
         <body>
        <c:if test="${not empty serviceticketforcus}">
    <table>
        <tr>
            <th>Service Ticket ID</th>
            <th>Date Received</th>
            <th>Date Return</th>
            <th>Car ID</th>
            <th>Action</th>
        </tr>
        <c:forEach var="viewServiceTicket" items="${requestScope.serviceticketforcus}">
            <tr>
                <td>${viewServiceTicket.ticketId}</td>
                <td>${viewServiceTicket.dateReceived}</td>
                <td>${viewServiceTicket.dateReturn}</td>
                <td>${viewServiceTicket.carID}</td>
                <td>
                    <!-- Thêm hành động bạn muốn thực hiện (ví dụ: sửa, xóa) -->
                </td>
            </tr>
        </c:forEach>
    </table>
</c:if>

    </body>
</html>
