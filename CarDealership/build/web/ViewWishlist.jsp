<%-- 
    Document   : ViewWishlist
    Created on : Feb 24, 2025, 10:09:32 AM
    Author     : trant
--%>

<%@page import="model.Car"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Wishlist</title>
    </head>
    <body>
        <h3># Click "Booking" to register for a test drive</h3>
        <p><a href="CustomerDashBoard.jsp">back</a></p>
        <%
            ArrayList<Car> wishlist = (ArrayList<Car>) session.getAttribute("WISHLIST");
            if (wishlist != null && !wishlist.isEmpty()) {
        %>
        <table>
            <tr>
                <th>Car ID</th>
                <th>Model</th>
                <th>Action</th>
            </tr>
            <%
                for (Car car : wishlist) {
            %>
            <tr>
                <td><%= car.getCarId() %></td>
                <td><%= car.getModel() %></td>
                <td>
                    <!-- Remove -->
                    <form action="RemoveFromWishlistServlet" method="post">
                        <input type="hidden" name="carId" value="<%= car.getCarId() %>">
                        <input type="submit" value="remove">
                    </form>
                </td>
            </tr>
            <%
                }
            %>
        </table>

        <br>
        <form action="SaveWishlistServlet">
            <input type="submit" value="Booking">
        </form>

        <%
            } else {
        %>
        <p>Your wishlist is empty.</p>
        <%
            }
        %>
    </body>
</html>