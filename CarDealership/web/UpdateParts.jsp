<%-- 
    Document   : UpdateParts.jsp
    Created on : Mar 16, 2025, 10:56:28 PM
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
         <% if (session.getAttribute("sale") != null) {
        %>

        <nav style="width: 100%; display: inline-block; list-style-type: none; background: yellowgreen">
            <h1>UPDATE PARTS</h1>   
        </nav> 
        <form action="ViewPartsServlet" method="POST">
            <input type="hidden" name="action" value="Edit">
            <input type="hidden" name="partID" value="<%=request.getParameter("partID") %>">
            <table>
                <tr><td>Part Name</td><td><%=request.getParameter("partName") %></td></tr>
                <tr><td>Purchase Price</td><td> <input name="purchasePrice" required="" value="<%=request.getParameter("purchasePrice") %>"> </td></tr>
                <tr><td>Retail Price</td><td> <input name="retailPrice" required="" value="<%=request.getParameter("retailPrice") %>"> </td></tr>                                

                <tr>
                    <td><input type="submit" value="Save"></td>
                </tr>
            </table>
        </form>
        <p><%
            if (request.getAttribute("ERROR") != null) {
                out.print(request.getAttribute("ERROR"));
            }
            %></p>

        <%
            } else {
                request.getRequestDispatcher("LoginCustPage.jsp").forward(request, response);
            }
        %>
    </body>
</html>
