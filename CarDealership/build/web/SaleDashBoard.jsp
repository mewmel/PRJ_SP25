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
         

        <nav>
            <ul class="menu" style="width: 100%; display: inline-block; list-style-type: none; background: yellowgreen">
                <li>Welcome <%= salePerson.getSaleName()%></li>
                <li><a href="LogoutSaleServlet">Logout</a></li>
                <li style="float:right;width: 30%; margin-right: 2%"></li>
            </ul>
        </nav>
            
                <div style="width: 100%">
            <%--menu--%>
            <div style="width: 30%; float: left;">        
                <h1>SALE DASHBOARD</h1>
                <p><a href="ViewCar.jsp">Car</a></p>
                <p><a href="ViewCustomer.jsp">Customer</a></p>
                <p><a href="CreateInvoiceServlet">Create Invoice For New Customer</a></p>
            </div>
                       

                <%
                    ArrayList<Customer> customers = (ArrayList) request.getAttribute("LISTCUSTOMER_RESULT");
                    if (customers != null && !customers.isEmpty()) {
                        for (Customer customer : customers) {
            %>
            <table>
                <tr>
                    <th>Customer ID</th>
                    <th>Customer Name</th>
                    <th>Phone</th>
                    <th>Sex</th>
                    <th>Address</th>
                    <th>Action</th>
                </tr>


                <form action="ShowCarInvoiceServlet" method="POST">
                    <input type="hidden" name="txtcustomerid" value="<%= customer.getCusId()%>">
                    <input type="hidden" name="txtcustname" value="<%= customer.getCusName()%>">
                    <input type="hidden" name="txtcustomerphone" value="<%= customer.getPhone()%>">
                    <input type="hidden" name="txtcustomersex" value="<%= customer.getSex()%>">
                    <input type="hidden" name="txtcustomeraddress" value="<%= customer.getCusAddress()%>">
                    <tr>
                        <td><%= customer.getCusId()%></td>
                        <td><%= customer.getCusName()%></td>
                        <td><%= customer.getPhone()%></td>
                        <td><%= customer.getSex()%></td>
                        <td><%= customer.getCusAddress()%></td>
                        <td><input type="submit" value="Choose"></td>
                    </tr>
                </form>
                <% } %>
            </table>
            <%
                }
            %>


            <%
                ArrayList<Car> cars = (ArrayList) request.getAttribute("LISTCAR_RESULT");
                if (cars != null && !cars.isEmpty()) {
Customer customer = (Customer) request.getAttribute("Customer");
            %>
             

            <table>
                <tr>
                    <th>Car ID</th>
                    <th>Serial Number</th>
                    <th>Model</th>
                    <th>Colour</th>
                    <th>Year</th>
                    <th>Action</th>

                </tr>
                <%
                    for (Car car : cars) {
                %>
                <form action="ConfirmInvoiceServlet" method="POST"> 
                    <input type="hidden" name="txtcustomerid" value="<%= customer.getCusId()%>">  
                    <input type="hidden" name="txtcustomername" value="<%= customer.getCusName()%>">  
                    <input type="hidden" name="txtcarmodel" value="<%= car.getModel()%>">  
                    <input type="hidden" name="txtcarid" value="<%= car.getCarId()%>">

                    <tr>                             
                        <td><%= car.getCarId()%></td>
                        <td><%= car.getSerialNumber()%></td>
                        <td><%= car.getModel()%></td>
                        <td><%= car.getColour()%></td>
                        <td><%= car.getYear()%></td>
                        <td><input type="submit" value="Create Invoice"></td>
                    </tr>
                </form>

                <% } %>
            </table>
            <%
                }
            %>

        </div>

        <% } else {
                // Redirect to login page if session is not valid
                request.getRequestDispatcher("LoginStaffPage.jsp").forward(request, response);
            }%>
    </body>
</html>
