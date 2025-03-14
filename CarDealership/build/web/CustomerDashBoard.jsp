<%-- 
    Document   : CustomerDashboard
    Created on : Mar 5, 2025, 1:58:17 AM
    Author     : trant
--%>

<%@page import="model.ServiceCustTicket"%>
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
        <nav class="text">
            
                Welcome <a href="ChangeProfile.jsp"><%= ((Customer) session.getAttribute("customer")).getCusName()%></a>
                <li><a href="LogoutCustServlet">logout</a></li>
                <a style="float:right;width: 30%; margin-right: 2%">
                    <form action="FindCarServlet">
                        <input type="text" name="txtmodel" value="<%= (request.getParameter("txtmodel") != null) ? request.getParameter("txtmodel") : ""%>"/>
                        <input type="submit" value="go"/>
                    </form></a>

        </nav>  
        <div style="width: 100%">
            <%--menu--%>
            <div style="width: 30%; float: left;">        
                <h1>DASHBOARD</h1>
                <p><a href="InvoiceServlet">invoices</a></p>
                <p><a href="ViewWishlist.jsp">view wishlist</a></p>
                <p><a href="ServiceCustTicketServlet">service ticket</a></p>
            </div>      
            <section style="width: 50%; float: left" >
                <%
                    ArrayList<Invoice> kq = (ArrayList) request.getAttribute("INVOICE_RESULT"); //request.getAttribute("RESULT");
                    if (kq != null && !kq.isEmpty()) {
                        for (Invoice v : kq) {
                %>
                <table>
                    <tr>
                        <th>Inv id</th>
                        <th>date</th>
                        <th>car id</th>

                    </tr>
                    <form action="DetailCarServlet">
                        <input type="hidden" name="txtcarid" value="<%=v.getCarId()%>">
                        <tr>
                            <td><%=  v.getInvoiceId()%></td>
                            <td><%=  v.getInvoiceDate()%></td>
                            <td><%=  v.getCarId()%></td>
                            <td><input type="submit" value="detail"></td>
                        </tr>
                    </form>
                </table>      
                <%}
                    }
                %>                
            <!-- cho nay de xuat thong tin xe tim thay sau khi nhan "go"-->    
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
            
            <!-- ticket detail-->
            
        </section>
            <!-- hien thi detail car sau khi click nut detail -->
            <div style="width: 20%; float:left">   
                <%
                    Car car = (Car) request.getAttribute("FOUND_CAR");
                    if (car != null) {
                %>
                <p>
                    Car id: <%= car.getCarId()%><br/>
                    Car model: <%= car.getModel()%><br/>
                    Car color: <%= car.getColour()%><br/>
                    Year: <%= car.getYear()%>
                </p>
                <%
                    }
                %>
            </div>
            <!--detail ticket -->
            
            <%
                } else {
                    request.getRequestDispatcher("LoginCustPage.jsp").forward(request, response);     
                }
            %>            
        </div>
    </body>
</html>
