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
        <title>JSP Page</title>
    </head>
    <body>
        <h1>danh sach xe yeu thich</h1>
        <h2>#booking de lai thu  #showroom</h2>
        
        <%
            ArrayList<Car> wl = (ArrayList)session.getAttribute("WISHLIST");
            if (wl != null && !wl.isEmpty()) {
                    for (Car car : wl) {
                            %>
                            <table>
                                <tr><td><%=car.getCarId() %></td></tr>
                                <tr><td><%=car.getModel() %></td></tr>
                                <tr><td><a href="#">remove</a></td></tr>
                            </table>
                            
                            <%
                        }
                }
            %>
            <form action="SaveWishlistServlet">
                <input type="submit" value="Dang ki lai thu">         
            </form>
        
    </body>
</html>
