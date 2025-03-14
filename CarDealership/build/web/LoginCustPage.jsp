<%-- 
    Document   : LoginCustPage
    Created on : Feb 21, 2025, 6:22:03 PM
    Author     : trant
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
 <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Đăng nhập khách hàng</title>
<!DOCTYPE html>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: Arial, sans-serif;
        }

        body {
            display: flex;
            align-items: center;
            justify-content: center;
            height: 100vh;
            background: linear-gradient(to right, #2c3e50, #4ca1af);
        }

        .login-container {
            background: rgba(255, 255, 255, 0.9);
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.2);
            text-align: center;
            width: 350px;
        }

        .login-container h2 {
            margin-bottom: 15px;
            color: #333;
        }

        input[type="text"] {
            width: 100%;
            padding: 10px;
            margin: 10px 0;
            border-radius: 5px;
            border: 1px solid #ccc;
            font-size: 16px;
        }

        .btn {
            display: block;
            width: 100%;
            padding: 10px;
            border: none;
            background: #27ae60;
            color: white;
            font-size: 18px;
            border-radius: 5px;
            cursor: pointer;
            transition: background 0.3s;
        }

        .btn:hover {
            background: #219150;
        }

        .error-message {
            color: red;
            margin-top: 10px;
        }


    </style>
</head>
<body>
    
    <div class="login-container">
        
        <h2>Customer Login</h2>
        <p>Enter your information</p>
        
        <form action="LoginCustServlet" method="POST" accept-charset="utf-8">
            <input type="text" name="txtname" placeholder="Enter your name" required="">
            <input type="text" name="txtphone" placeholder="Enter your phone" required="">
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
