<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Customer Login</title>
    <link rel="stylesheet" href="mystyle.css" type="text/css"/>
</head>
<body class="login-customer-page"> 
    
    <div class="login-container">
        <h2>Customer Login</h2>        
        <form action="LoginCustServlet" method="POST" accept-charset="utf-8">
            <input type="text" name="txtname" placeholder="Enter your name" required>
            <input type="text" name="txtphone" placeholder="Enter your phone" required>
            <button type="submit" class="btn">Login</button>
        </form>       
        <p class="error-message">
            <% if (request.getAttribute("ERROR") != null) { %>
                <%= request.getAttribute("ERROR") %>
            <% } %>
        </p>
        <a href="LoginPage.html">&larr;</a>
    </div>  
</body>
</html>
