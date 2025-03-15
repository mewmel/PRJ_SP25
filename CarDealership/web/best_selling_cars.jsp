<%@page import="model.CarCounting"%>
<%@page import="java.util.ArrayList"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>


<html>
<head>
    <title>Best Selling Cars</title>
</head>
<body>
    <h2>Danh sách xe bán chạy</h2>
    <table border="1">
        <tr>
            <th>Model</th>
            <th>Số lượng bán</th>
        </tr>
        <%
            ArrayList<CarCounting> bestSellingCars = (ArrayList<CarCounting>) request.getAttribute("bestSellingCars");
            if (bestSellingCars != null && !bestSellingCars.isEmpty()) {
                for (CarCounting car : bestSellingCars) {
        %>
        <tr>
            <td><%= car.getCarModel() %></td>
            <td><%= car.getTotal_sold() %></td>
        </tr>
        <%
                }
            } else {
        %>
        <tr>
            <td colspan="2">Không có dữ liệu</td>
        </tr>
        <%
            }
        %>
    </table>
</body>
</html>

