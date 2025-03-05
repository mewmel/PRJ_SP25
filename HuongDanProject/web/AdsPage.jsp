<%-- 
    Document   : AdsPage
    Created on : Feb 21, 2025, 7:38:52 PM
    Author     : trant
--%>

<%@page import="model.Car"%>
<%@page import="dao.CarDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ads</title>
    </head>
    <body>
        <%
        String model = "";
        Cookie[] arr = request.getCookies();
        if (arr != null) {
                for (Cookie c : arr) {
                        if (c != null && "model_Ads".equals(c.getName())) {
                                model=c.getValue();
                            }
                    }
                if (model != null && !model.trim().isEmpty()) {
                    CarDAO d = new CarDAO();
                        ArrayList<Car> list_car = d.getNewCars(model);
                        if (list_car != null && !list_car.isEmpty()) {
                                for (Car car : list_car) {
                            %>
                            <div style="background-color: yellow; width: 20%; float: left">
                                    <%= car.getModel()+"-"+ car.getColour() %>
                            </div>
                            
                            <%         
                                }
                        }
                }
        }
        %>
    </body>
</html>
