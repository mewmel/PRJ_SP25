<!DOCTYPE html>
<html lang="vi">
<head>
    <%@page contentType="text/html" pageEncoding="UTF-8"%>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="mystyle.css" type="text/css"/>

</head>
<body class="login-staff-page">
    <div class="login-container">
        <h2>Staff Login</h2>
        <form action="LoginStaffServlet" method="POST" accept-charset="utf-8">
            <input type="text" name="txtname" placeholder="Enter your name" required="">
            <button type="submit" class="btn">Login</button>
        </form>

        <p class="error-message">
            <% if (session.getAttribute("ERROR") != null) {%>
            <%= session.getAttribute("ERROR")%>
            <% session.removeAttribute("ERROR"); %> <!-- Xóa để khong hiện lại -->
            <% }%>
        </p>


        <a href="LoginPage.html">&larr;</a>
    </div>

</body>
</html>



