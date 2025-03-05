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
        <title>JSP Page</title>
    </head>
    <body>
        <%
    String userType = request.getParameter("userType");

    if ("mechanic".equals(userType)||"salesperson".equals(userType)) {
        response.sendRedirect("loginStaffPage.jsp");
    } else if ("customer".equals(userType)) {
        response.sendRedirect("loginCustPage.jsp");
    } else {
        response.sendRedirect("errorPage.jsp");
    }
%>

    </body>
</html>
