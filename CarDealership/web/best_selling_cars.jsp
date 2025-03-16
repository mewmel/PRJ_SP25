<%@page import="model.CarCounting"%>
<%@page import="java.util.ArrayList"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>


<html>
<head>
    <title>Best Selling Cars</title>
</head>
<body>
    <h2>List of best selling cars</h2>
    <p><a href="SaleDashBoard.jsp" style="padding: 5px 10px; border: none; background-color: #009999; color: white; border-radius: 5px; cursor: pointer;">back</a></p>
    <table border="1">
        <tr>
            <th>Model</th>
            <th>Quantity</th>
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
            <td colspan="2">No Data</td>
        </tr>
        <%
            }
        %>
    </table>
</body>
</html>

