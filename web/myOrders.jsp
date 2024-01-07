<%@page import="model.Account"%>
<%@ page import="java.util.List" %>
<%@ page import="model.Cart" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Check out</title>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

        <style>
            body {
                background-color: #f2f2f2;
                font-family: 'Roboto', sans-serif;
            }

            .container {
                background-color: #ffffff;
                margin-top: 50px;
                padding: 20px;
                border-radius: 10px;
                box-shadow: 0px 0px 10px 0px #000000;
            }

            h3 {
                color: #333333;
            }

            table {
                width: 100%;
                border-collapse: collapse;
                margin-bottom: 20px;
            }

            th,
            td {
                padding: 10px;
                text-align: left;
                border-bottom: 1px solid #ddd;
            }

            th {
                background-color: #f2f2f2;
            }

            label {
                margin-top: 10px;
                display: block;
                color: #333333;
            }

            input,
            select {
                width: 100%;
                padding: 8px;
                margin-top: 5px;
                margin-bottom: 15px;
                display: inline-block;
                border: 1px solid #ccc;
                box-sizing: border-box;
                border-radius: 4px;
            }

            button {
                background-color: #4CAF50;
                color: white;
                padding: 10px 15px;
                border: none;
                border-radius: 4px;
                cursor: pointer;
                font-size: 16px;
            }

            button:hover {
                background-color: #45a049;
            }
            .break{
                display: flex;
            }
            .breakin{
                width: 50%;
            }
            .container{
                background-color: rgb(225, 238, 250);
            }
        </style>
    </head>

    <body>
        <%
            Object obj = session.getAttribute("account");
            Account account = null;
            if (obj != null) {
                account = (Account) obj;
            }
            if (account == null) {
        %>
        <h1 class="red">You are not logged into the system. Please return to the <a href="Login">Login Page</a>!</h1>
        <%
        } else {
            String error = request.getAttribute("error") + "";
            if (error.equals("null")) {
                error = "";
            }
        %>
        <div class="container">
            <div style="color: black; text-align: center; font-size: 30px; font-weight: 600">Order of ${account.email} <i class='fab fa-elementor'></i></div>

            <form action="CheckOut" method="post">
                <h3>Ordered Products</h3>
                <div style="color: yellow"><%=error%></div>
                <table>
                    <thead>
                        <tr>
                            <th>Name</th>
                            <th>Category</th>
                            <th>Price</th>
                            <th>Quantity</th>
                            <th>Subtotal</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="cartProduct" items="${sessionScope.cart}">
                            <tr>
                                <td>${cartProduct.product.name}</td>
                                <td>${cartProduct.product.category.name}</td>
                                <td>${cartProduct.product.price}</td>
                                <td>${cartProduct.quantity}</td>
                                <td>${cartProduct.subtotal}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <h3>Ordered Information</h3>
                <div class="break">
                    <div class="breakin">
                        <label for="name">CompanyName</label>
                        <input type="text" name="name" value="${account.name}" readonly> 
                        <label for="address">Delivery Address</label>
                        <input type="text" name="address" required>
                    </div>

                    <div class="breakin">
                        <label for="name">Phone</label>
                        <input type="text" name="phone" value="${account.phone}" readonly> 
                        <label for="payment">Payment Method:</label>
                        <select name="payment" required>
                            <c:forEach var="payment" items="${paymentList}">
                                <option value="${payment.id}">${payment.paymentForm}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <strong>Total:<td name="total">${sessionScope.cart.getTotal()}</td></strong>

                <input type="hidden" name="action" value="addOrder">
                <button type="submit">Order</button>

            </form>
        </div>
        <%}%>
    </body>

</html>
