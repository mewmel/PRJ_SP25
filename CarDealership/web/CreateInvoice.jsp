<%-- 
    Document   : CreateInvoice
    Created on : Mar 10, 2025, 10:03:56 PM
    Author     : ThinkPad
--%>

<%@page import="java.util.List"%>
<%@page import="model.Car"%>
<%@page import="model.SalePerson"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            if (session.getAttribute("sale") != null) {
        %>
           <form action="CreateInvoiceServlet" method="POST">
        <label>Số điện thoại khách hàng:</label>
        <input type="text" name="customerPhone" required><br>

        <label>Chọn xe:</label>
        <select name="carID" required>
            
            <%
                List<Car> cars = (List<Car>) request.getAttribute("cars");
                if (cars != null) {
                    for (Car car : cars) {
            %>
                        <option value="<%= car.getCarId()%>"> </option>
            <% 
                    }
                }
            %>
        </select><br>

        <label>Ngày lập hóa đơn:</label>
        <input type="date" name="invoiceDate" required><br>

        <label>Mã nhân viên:</label>
        <input type="text" name="salesID" value=" <%= ((SalePerson) session.getAttribute("sale")).getSaleId()%>" readonly><br>

        <input type="submit" value="Tạo Hóa Đơn">
    </form>
         <%
            } else {
                request.getRequestDispatcher("LoginStaffPage.jsp").forward(request, response);     
            }
            %>
    </body>
</html>
