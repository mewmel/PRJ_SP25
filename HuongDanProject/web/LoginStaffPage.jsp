<%-- 
    Document   : LoginStaffPage
    Created on : Mar 3, 2025, 11:07:35 PM
    Author     : trant
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Login for Staff</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <form action="LoginSalePersonServlet" accept-charset="utf-8">
            <p>Name <input type="text" name="txtname" required=""/>*</p>
            <p><input type="submit" value="login"/></p>
        </form>
        <p>
        <%
            if(request.getAttribute("ERROR")!= null)
                out.print(request.getAttribute("ERROR"));
        %>
        </p>
    </body>
</html>
