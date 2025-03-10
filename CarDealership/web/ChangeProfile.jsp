<%-- 
    Document   : Changeprofile.jsp
    Created on : Mar 3, 2025, 9:02:52 PM
    Author     : ThinkPad
--%>

<%@page import="model.Customer"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <% if (session.getAttribute("customer")!= null) {                    
        %>
          
        <nav style="width: 100%; display: inline-block; list-style-type: none; background: yellowgreen">
            <h1>CHANGE PROFILE</h1>   
        </nav> 
                 <form action="ChangeProfileServlet" method="POST">
            <table>
                <tr><td>Name</td><td> <input name="name" value="<%= ((Customer) session.getAttribute("customer")).getCusName()%> " required=""> </td></tr>
                <tr><td>Phone</td><td> <input name="phone" value="<%= ((Customer) session.getAttribute("customer")).getPhone()%>" required=""> </td></tr>
                <tr><td>Sex</td><td> <input name="sex" value="<%= ((Customer) session.getAttribute("customer")).getSex()%>"required=""> </td></tr>
                <tr><td>Address</td><td> <input name="address" value="<%= ((Customer) session.getAttribute("customer")).getCusAddress()%>"required=""> </td></tr>                                

                <tr>
                    <td><input type="submit" value="Save"></td>
                </tr>
            </table>
         </form>
         <p><%        
            if(request.getAttribute("ERROR")!=null)
               out.print(request.getAttribute("ERROR"));
            %></p>
        
         <%
            } else {
                request.getRequestDispatcher("LoginCustPage.jsp").forward(request, response);     
            }
                %>
    </body>
</html>
