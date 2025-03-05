<%-- 
    Document   : LoginCustPage
    Created on : Feb 21, 2025, 6:22:03 PM
    Author     : trant
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Login Customer</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <form action="LoginCustServlet" accept-charset="utf-8" method="post">
            <p><input type="text" name="txtname" required=""/>Name *</p>
            <p><input type="text" name="txtphone" required=""/>Phone *</p>
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
