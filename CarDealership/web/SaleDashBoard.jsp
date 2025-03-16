<%@page import="model.Car"%>
<%@page import="model.Customer"%>
<%@page import="model.SalePerson"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sale Dashboard</title>
    </head>
    <body>
        <%
            if (session.getAttribute("sale") != null) {
                SalePerson salePerson = (SalePerson) session.getAttribute("sale");
        %>
       <nav style="background: yellowgreen; padding: 10px;">
    <ul class="menu" style="display: flex; justify-content: space-between; align-items: center; list-style-type: none; margin: 0; padding: 0;">
        <li style="font-weight: bold;">Welcome <%= salePerson.getSaleName()%></li>
        <li><a href="LogoutSaleServlet" style="color: #003333; text-decoration: none; font-weight: bold;">Logout</a></li>
    </ul>
</nav>

<div style="display: flex; gap: 20px; padding: 20px;">
    <!-- Menu -->
    <div style="width: 30%; background-color: #f8f9fa; padding: 15px; border-radius: 8px;">
        <h2>SALE DASHBOARD</h2>
        <p><a href="ViewCar.jsp">Car</a></p>
        <p><a href="ViewCustomer.jsp">Customer</a></p>
        <p><a href="CreateInvoiceServlet">Create Invoice</a></p>
        <p><a href="Report.jsp">Report</a></p>
        <p><a href="ViewPartsServlet">View Parts</a></p>
    </div>

    <!-- Customer List -->
    <div style="width: 65%;">
        <h2>Customer List</h2>
        <%
            ArrayList<Customer> customers = (ArrayList) request.getAttribute("LISTCUSTOMER_RESULT");
            if (customers != null && !customers.isEmpty()) {
        %>
        <table style="width: 100%; border-collapse: collapse;">
            <tr style="background-color: yellowgreen; color: white;">
                <th>Customer ID</th>
                <th>Name</th>
                <th>Phone</th>
                <th>Sex</th>
                <th>Address</th>
                <th>Action</th>
            </tr>
            <%
                for (Customer customer : customers) {
            %>
            <tr>
                <td><%= customer.getCusId()%></td>
                <td><%= customer.getCusName()%></td>
                <td><%= customer.getPhone()%></td>
                <td><%= customer.getSex()%></td>
                <td><%= customer.getCusAddress()%></td>
                <td>
                    <form action="ShowCarInvoiceServlet" method="POST">
                        <input type="hidden" name="txtcustomerid" value="<%= customer.getCusId()%>">
                        <input type="submit" value="Choose">
                    </form>
                </td>
            </tr>
            <% } %>
        </table>
        <% } else { %>
        <p>Click "Create Invoice" to load Customer list</p>
        <% } %>

        <!-- Car List -->
        <%
            ArrayList<Car> cars = (ArrayList) request.getAttribute("LISTCAR_RESULT");
            if (cars != null && !cars.isEmpty()) {
                Customer customer = (Customer) request.getAttribute("Customer");
        %>
        <h2>Car List</h2>
        <table style="width: 100%; border-collapse: collapse;">
            <tr style="background-color: yellowgreen; color: white;">
                <th>Car ID</th>
                <th>Serial Number</th>
                <th>Model</th>
                <th>Colour</th>
                <th>Year</th>
                <th>Status</th>
                <th>Action</th>
            </tr>
            <%
                for (Car car : cars) {
            %>
            <tr>
                <td><%= car.getCarId()%></td>
                <td><%= car.getSerialNumber()%></td>
                <td><%= car.getModel()%></td>
                <td><%= car.getColour()%></td>
                <td><%= car.getYear()%></td>
                <td><%= car.getStatusName()%></td>
                <td>
                    <form action="DetailInvoiceServlet" method="POST">
                        <input type="hidden" name="txtcustomerid" value="<%= customer.getCusId()%>">
                        <input type="hidden" name="txtcarid" value="<%= car.getCarId()%>">
                        <input type="hidden" name="txtcarmodel" value="<%= car.getModel()%>">
                        <input type="hidden" name="txtcustomername" value="<%= customer.getCusName()%>">
                        <input type="submit" value="Create Invoice">
                    </form>
                </td>
            </tr>
            <% } %>
        </table>
        <% } %>

        <!-- Error Message -->
        <p style="color: red; font-weight: bold;">
            <%
                if (request.getAttribute("ERROR") != null) {
                    out.print(request.getAttribute("ERROR"));
                }
            %>
        </p>
    </div>
</div>

        <% } else {
                // Redirect to login page if session is not valid
                request.getRequestDispatcher("LoginStaffPage.jsp").forward(request, response);
            }%>
    </body>
</html>
