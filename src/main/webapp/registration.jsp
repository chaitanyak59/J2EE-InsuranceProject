<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <!DOCTYPE html>
        <html>

        <head>
            <meta charset="ISO-8859-1">
            <link href="main.css" rel="stylesheet" type="text/css">
            <link href="https://fonts.googleapis.com/css?family=Ubuntu" rel="stylesheet">
            <meta name="viewport" content="width=device-width, initial-scale=1" />
            <link rel="stylesheet" href="fontawesome.min.css">
            <title>Product Registration</title>
        </head>

        <body>

            <body>
                <div class="main">
                    <p class="sign" align="center">Register your purchase</p>
                    <div style="text-align: center;">
                        <font color="red">
                            <c:if test="${param.status == false}">
                                <c:out value="Failed to Register/Invalid Details" />
                            </c:if>
                        </font>
                        <font color="green">
                            <c:if test="${param.status}">
                                <c:out value="You have registered Successfully" />
                            </c:if>
                        </font>
                    </div>
                    <form action="Registration" method="POST">
                        <br><input type="text" class="un" id="name" name="name" placeholder="Enter Name" required />

                        <input type="text" class="un" id="serialNo" name="serialNo"
                            placeholder="Enter Product's Serial No" required />

                        <input type="date" id="purchase_date" name="purchase_date" class="un" required />

                        <div class="flex-btn-submit">
                            <button type="submit" class="submit">Register</button>
                            <button class="submit" onclick="window.location='Home'" id="myButton"
                                class="submit">Home</button>
                        </div>

                    </form>
                </div>

            </body>
        </body>

        </html>