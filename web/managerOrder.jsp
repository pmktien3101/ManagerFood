<%-- 
    Document   : ManagerProduct
    Created on : Dec 28, 2020, 5:19:02 PM
    Author     : trinh
--%>

<%@page import="model.Account"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Order List</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" integrity="sha512-dT+FQ7C5tUv20Vbb1aFbVwiX+VfDO9DFteTpebZysD1Sfiu4d6bH6dwL5OXdYZJf3TAUqEizL/uGJHtCVt6Z9w==" crossorigin="anonymous" />
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" integrity="sha512-xxx" crossorigin="anonymous" />

        <%
            String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
                    + request.getContextPath();
        %>
        <link href="<%=url%>/css/managerProduct.css" rel="stylesheet">
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
        <h1 class="red">You are not logged into the system. Please return to the <a href="Login">Login page</a>!</h1>
        <%
        } else {%>

        <div class="container">
            <div class="table-wrapper">
                <div class="table-title">
                    <div class="row">
                        <div class="col-sm-6">
                            <h2 class="titleP">Order List</h2>
                        </div>
                        <div class="col-sm-6 ">
                            <a class="home dessss" href="adminHome.jsp">Return Home</a>
                        </div>
                    </div>
                </div>
            </div>
            <table class="table table-striped table-hover">
                <thead>
                    <tr>
                        <th>Email</th>
                        <th>OrderDate</th>
                        <th>OrderTotal</th>
                        <th>DeliveryDate</th>
                        <th>OrderStatus</th>
                        <th>DeliveryAddress</th>
                        <th>Payment</th>
                        <th>Order Deliveried</th>

                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${orders}" var="o">
                        <tr>

                            <td>${o.account.email}</td>
                            <td>${o.orderDate}</td>
                            <td>${o.orderTotal} $</td>
                            <td>${o.deliveryDate}</td>
                            <td>
                                ${o.orderStatus}
                            </td>
                            <td>${o.deliveryAddress}</td>
                            <td>${o.paymentForm.paymentForm}</td>
                            <td>
                                <c:if test="${!o.orderStatus.equals('Cancel') && !o.orderStatus.equals('Deliveried')}">
                                    <form action="ManagerOrder" method="post">
                                        <input type="hidden" name="orderId" value="${o.id}" />
                                        <button type="submit" name="action" value="updateOrderStatus" class="btn btn-link">
                                            <img style="width:30px" src="assets/delivery.png"/>
                                        </button>
                                    </form>
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

        </div>
    </div>

    <%}%>
</body>
</html>