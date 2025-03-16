<%-- 
    Document   : CustomerDashboard
    Created on : Mar 5, 2025, 1:58:17 AM
    Author     : trant
--%>


<%@page import="model.ServiceTicketDetail"%>
<%@page import="model.Car"%>
<%@page import="model.Invoice"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Customer"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Customer Dashboard</title>
        <link href="mystyle.css" style="css" rel="stylesheet"/>     
    </head>
    <body>
        <%
            if (session.getAttribute("customer") != null) {
        %>
<nav style="display: flex; justify-content: space-between; align-items: center; padding: 10px; background-color: #f8f9fa; border-bottom: 2px solid #ddd;">
    <div>
        Welcome <a href="ChangeProfile.jsp" style="font-weight: bold; color: #007bff; text-decoration: none;">
            <%= ((Customer) session.getAttribute("customer")).getCusName()%>
        </a>
    </div>
    <ul style="list-style: none; display: flex; gap: 15px; margin: 0; padding: 0;">
        <li><a href="LogoutCustServlet" style="color: red; text-decoration: none;">Logout</a></li>
    </ul>
    <div style="width: 30%;">
        <form action="FindCarServlet">
            <input type="text" name="txtmodel" placeholder="Search car..." value="<%= (request.getParameter("txtmodel") != null) ? request.getParameter("txtmodel") : ""%>"
                   style="flex: 1; padding: 5px; border: 1px solid #ccc; border-radius: 5px;">
            <input type="submit" value="go">
        </form>
    </div>
</nav>

<div style="display: flex; gap: 20px; padding: 20px;">
    <!-- Menu -->
    <div style="width: 25%; background-color: #f8f9fa; padding: 15px; border-radius: 8px;">
        <h2>DASHBOARD</h2>
        <p><a href="InvoiceServlet">Invoice</a></p>
        <p><a href="ViewWishlist.jsp">View Wishlist</a></p>
        <p><a href="TicketCusServlet">Your Tickets</a></p>
    </div>

    <!-- Invoice List -->
    <div style="width: 50%;">
        <h2>Your Invoices</h2>
        <%
            ArrayList<Invoice> kq = (ArrayList) request.getAttribute("INVOICE_RESULT");
            if (kq != null && !kq.isEmpty()) {
        %>
        <table style="width: 100%; border-collapse: collapse;">
            <tr style="background-color: #007bff; color: white;">
                <th>Inv ID</th>
                <th>Date</th>
                <th>Car ID</th>
                <th>Action</th>
            </tr>
            <%
                for (Invoice v : kq) {
            %>
            <tr>
                <td><%= v.getInvoiceId()%></td>
                <td><%= v.getInvoiceDate()%></td>
                <td><%= v.getCarId()%></td>
                <td>
                    <form action="DetailCarServlet">
                        <input type="hidden" name="txtcarid" value="<%= v.getCarId()%>">
                        <input type="submit" value="detail">
                    </form>
                </td>
            </tr>
            <% } %>
        </table>
        <% } else { %>
        <p>Click "Invoice" to load your Invoice list</p>
        <% } %>
        <!-- Service Tickets List -->
        <%
            ArrayList<ServiceTicketDetail> ticketList = (ArrayList<ServiceTicketDetail>) request.getAttribute("TICKET_LIST");
        %>
        <h2>Your Service Tickets</h2>
        <% if (ticketList != null && !ticketList.isEmpty()) { %>
        <table border="1" style="width: 100%; border-collapse: collapse;">
            <tr style="background-color: yellowgreen; color: white;"> 
                <th>Service ID</th>
                <th>Service Name</th>
                <th>Car Name</th>
                <th>Date Receive</th>
                <th>Date Return</th>
                <th>Hours</th>
                <th>Comment</th>
                <th>Rate</th>
            </tr>
            <% for (ServiceTicketDetail ticket : ticketList) {%>
            <tr>
                <td><%= ticket.getSeDetailId()%></td>
                <td><%= ticket.getServiceName()%></td>
                <td><%= ticket.getCarName()%></td>
                <td><%= ticket.getDateReceive()%></td>
                <td><%= ticket.getDateReturn()%></td>
                <td><%= ticket.getHours()%></td>
                <td><%= ticket.getComment()%></td>
                <td><%= ticket.getRate()%></td>
            </tr>
            <% } %>
        </table>
        <% } else { %>
        <p>Click "Your Tickets" to load all your ticket service</p>
        <% } %>
    </div>

    <!-- Car Details -->
    <div style="width: 25%; background-color: #f8f9fa; padding: 15px; border-radius: 8px;">
        <h3>Car Details</h3>
        <%
            Car car = (Car) request.getAttribute("FOUND_CAR");
            if (car != null) {
        %>
        <p><strong>ID:</strong> <%= car.getCarId()%></p>
        <p><strong>Model:</strong> <%= car.getModel()%></p>
        <p><strong>Color:</strong> <%= car.getColour()%></p>
        <p><strong>Year:</strong> <%= car.getYear()%></p>
        <p><strong>Status:</strong> <%= car.getStatusName()%></p>
        <%
            }
        %>

        <!--List Cars-->
        <%
            ArrayList<Car> listCar = (ArrayList) request.getAttribute("CAR_RESULT");
            if (listCar != null && !listCar.isEmpty()) {
                for (Car c : listCar) {
        %>
        <p>
            <strong>ID:</strong> <%= c.getCarId()%><br/>
            <strong>Model:</strong> <%= c.getModel()%><br/>                  
            <strong>Year:</strong> <%= c.getYear()%><br/>
            <strong>Status:</strong> <%= c.getStatusName()%><br/>
            <a href="AddToWishlistServlet?carId=<%=c.getCarId()%>">Add to wishlist</a>
        </p>  
        <%
                }
            }
        %>
    </div>
</div>

            <%
                } else {
                    request.getRequestDispatcher("LoginCustPage.jsp").forward(request, response);
                }
            %>            
        </div>
    </body>
</html>
