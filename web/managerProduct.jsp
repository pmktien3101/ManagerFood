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
        <title>Manager Product</title>
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
        <h1 class="red">You are not logged into the system. Please return to the <a href="Login">Login page</a>!</h1>
        <%
        } else {%>

        <div class="container">
            <div class="table-wrapper">
                <div class="table-title">
                    <div class="row">
                        <div class="col-sm-6">
                            <h2 class="titleP">MANAGER PRODUCT</h2>
                        </div>
                        <div class="col-sm-6 ">
                            <div class="dess">
                                <form action="ManagerProduct" method="post" class="desss">
                                <input type="hidden" name="action" value="loadProductA">
                                <button type="submit" class="add">Add Product</button>
                            </form>

                            <a class="home" href="adminHome.jsp">Return Home</a>
                            </div>
                        </div>
                    </div>
                </div>
                <table class="table table-striped table-hover">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Name</th>
                            <th>Image</th>
                            <th>Price</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${products}" var="product">
                            <tr>

                                <td>${product.id}</td>
                                <td>${product.name}</td>
                                <td>
                                    <img class="imageP" src="${product.image}">
                                </td>
                                <td>${product.price} $</td>
                                <td>
                                    <div class="icons">
                                        <form action="ManagerProduct" method="post">
                                            <input type="hidden" name="action" value="loadProduct">
                                            <input type="hidden" name="pid" value="${product.id}">
                                            <button type="submit" class="edit">
                                                <i style="color: yellow" class="material-icons" data-toggle="tooltip" title="Edit">&#xE254;</i>
                                            </button>
                                        </form>

                                        <a href="ManagerProduct?action=deleteProduct&id=${product.id}" class="delete" data-toggle="modal">
                                            <i style="color: red" class="material-icons" data-toggle="tooltip" title="Delete">&#xE872;</i>
                                        </a>
                                    </div>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>

            </div>
        </div>

        <div class="footer">

            <p>Email : slccompany@gmail.com</p>
            <p>Address: 15 Nguyen Cong Tru, District 1, HCMC</p>
            <h5>&copy; Copyright 2023. Slc.com</h5>

        </div>

        <%}%>
    </body>
</html>