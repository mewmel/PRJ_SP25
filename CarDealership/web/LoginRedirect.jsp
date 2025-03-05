<%-- 
    Document   : loginRedirect
    Created on : Mar 5, 2025, 12:05:36 AM
    Author     : ThinkPad
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Man hinh cho</title>
    </head>
    <body>
        <%
    String userType = request.getParameter("userType");

    if ("staff".equals(userType)) {
        response.sendRedirect("LoginStaffPage.jsp");
    } else if ("customer".equals(userType)) {
        response.sendRedirect("LoginCustPage.jsp");
    } else {
        out.print(request.getAttribute("ERROR"));
    }
%>

    </body>
</html>
