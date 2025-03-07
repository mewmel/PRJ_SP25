<%-- 
    Document   : ViewCar
    Created on : Mar 5, 2025, 9:18:28 PM
    Author     : trant
--%>

<%@page import="model.Car"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Car Information</title>
    </head>
    <body>
        <h1>CRUD car</h1>
 <%
            ArrayList<Car> listCar =(ArrayList)request.getAttribute("CAR_RESULT");
            if(listCar != null && !listCar.isEmpty()){
                for(Car c:listCar){
           %>
            <p>
                    Id: <%=c.getCarId()       %><br/>
                    Model: <%= c.getModel()   %><br/>                  
                    Year: <%= c.getYear()     %><br/>
                    <a href="AddToWishlistServlet?carId=<%=c.getCarId()%>">Add to wishlist</a>
            </p>  
            <% 
                    }
                } 
            %>
            <form action="SaveWishlistServlet">
                <input type="submit" value="Dang ki lai thu">         
            </form>
 
    </body>
</html>
