<%@ page import="model.Cart" %>
<%@ page import="java.util.List" %>
<%@ page import="model.ShoppingCart" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="model.Account" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>My Cart</title>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
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
        <h1 class="red">You are not logged into the system. Please return to the <a href="Login">Login Page</a>!</h1>
        <%
        } else {
            String error = request.getAttribute("error") + "";
            if (error.equals("null")) {
                error = "";
            }
        %>
       
        <div class="container">
            <div class="table-wrapper">
                <div class="table-title">
                    <div class="row">
                        <div class="col-sm-6">
                            <h2 class="titleP">MY CART</h2>
                            <div style="color: yellow"><%=error%></div>

                        </div>
                        <div class="col-sm-6 ">
                            <a class="return" href="./Home">Return Home</a>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <table class="table table-success table-hover">
                        <thead>
                            <tr>
                                <th>Name</th>
                                <th>Category</th>
                                <th>Price</th>
                                <th>Quantity</th>
                                <th>Subtotal</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:if test="${sessionScope.cart != null}" var="cartIsNotNull">
                                <c:forEach items="${sessionScope.cart}" var="cartProduct" varStatus="cartProductStatus">
                                    <tr>
                                        <td>${cartProduct.product.name}</td>
                                        <td>${cartProduct.product.category.name}</td>
                                        <td>${cartProduct.product.price}</td>
                                        <td>
                                            <form action="ShoppingCart" method="Post">
                                                <div class="input-group update">
                                                    <input type="hidden" name="index" value="${cartProductStatus.index}">
                                                    <input class="form-control form-control-sm small-input" type="number" min="1"
                                                           max="1000" value="${cartProduct.quantity}" name="quantity">
                                                    <button class="btn btn-sm btn-secondary" type="submit"
                                                            name="action" value="updatecartproduct">Update</button>
                                                </div>
                                            </form>
                                        </td>
                                        <td>${cartProduct.subtotal}</td>
                                        <td>
                                            <form action="ShoppingCart" method="Post">
                                                <input type="hidden" name="index" value="${cartProductStatus.index}">
                                                <button class="btn btn-sm btn-danger remove" type="submit" name="action"
                                                        value="removecartproduct">Remove</button>
                                            </form>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </c:if>
                        </tbody>
                        <tfoot>
                            <tr>
                                <th colspan="2">
                                    Total:
                                </th>
                                <th>
                                    <c:if test="${pageScope.cartIsNotNull}">
                                        ${sessionScope.cart.getTotal()}
                                    </c:if>
                                </th>
                                 <form action="CheckOut" method="post">
                                <th>
                                    
                                    <button class="btn btn-sm btn-success" type="submit" name="action" value="loadPayment">Checkout</button>
                                </th>
                                 </form>
                            </tr>

                        </tfoot>
                        

                    </table>
                </div>
            </div>
        </div>
       
                            
        <%}%>
    </body>

</html>
