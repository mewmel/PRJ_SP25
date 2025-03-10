<%-- 
    Document   : InvoicesForm
    Created on : Mar 6, 2025, 11:17:55 AM
    Author     : ThinkPad
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="SaveInvoiceServlet" method="POST">
    <label for="invoiceID">Invoice ID:</label>
    <input type="text" id="invoiceID" name="invoiceID" required><br>

    <label for="invoiceDate">Invoice Date:</label>
    <input type="date" id="invoiceDate" name="invoiceDate" required><br>

    <label for="salesID">Sales ID:</label>
    <input type="text" id="salesID" name="salesID" required><br>

    <label for="carID">Car ID:</label>
    <input type="text" id="carID" name="carID" required><br>

    <label for="custID">Customer ID:</label>
    <input type="text" id="custID" name="custID" required><br>

    <button type="submit">Save Invoice</button>
</form>

    </body>
</html>
