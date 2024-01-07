<%-- 
    Document   : index
    Created on : Dec 25, 2023, 10:18:54 PM
    Author     : MSI PC
--%>

<%@page import="model.ShoppingCart"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="model.Account"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css" rel="stylesheet" >
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" ></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.min.js" integrity="sha384-Atwg2Pkwv9vp0ygtn1JAojH0nYbwNJLPhwyoVbhoPwBhjQPR5VtM2+xf0Uwh9KtT" crossorigin="anonymous"></script>
        <title>Web SLC </title>

        <%
            String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
                    + request.getContextPath();
        %>
        <link href="<%=url%>/css/index.css" rel="stylesheet">

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
        <div id="wrapper">       
            <div id="header">
                <a href="" class="logo">
                    <img src="assets/logo1.png" alt="">
                </a>
                <div id="menu">
                    <div class="item">
                        <button> <a href="./Home" style="color: black">Home</a></button>
                    </div>
                    <div class="item">
                        <button> <a href="about.jsp" style="color: black">About Us </a></button>
                    </div>
                    <div class="item">
                        <button><a href="Message" style="color: black">Message Us</a></button>
                    </div>
                </div>
                <div class="cart">
                    <i class="fas fa-shopping-cart"></i><a class="cart" href="ShoppingCart">Cart</a>
                </div>
                <form action="Search" method="POST">
                    <div class="search">
                        <input name="txt" class="form-control me-2" type="text"
                               placeholder="Search" aria-label="Search">
                        <button class="btn btn-outline-success" type="submit">Search</button>
                    </div>
                </form>


                <div id="account">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0 bg-infor ">
                        <li class="nav-item dropdown dropstart">
                            <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">Hi, <%=account.getEmail()%></a>
                            <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                                <li><a class="dropdown-item" href="OrderList">My Order</a></li>
                                <li><a class="dropdown-item" href="changePass.jsp">Change password</a></li>
                                <li><a class="dropdown-item" href="ChangeName">Change Name</a></li>
                                <li><hr class="dropdown-divider"></li>
                                <li><a class="dropdown-item" href="Logout">Logout</a></li>
                                <li><a class="dropdown-item" href="DeleteAccount">Delete account</a></li>
                            </ul>
                        </li>
                    </ul>
                </div>

            </div>
            <div id="banner">
                <div class="box-left">
                    <h2>
                        <span>Order your</span>
                        <br>
                        <span>favourite Foods</span>
                    </h2>
                    <p>At Supreme Logics Co, We Connect More
                        Than Supply Chains We Connect Communities.</p>
                    <div class="orderN">
                        <i class="fa-light fa-cart-shopping"></i>
                        <button>Buy Now</button>
                    </div>

                </div>
                <div class="box-right">
                    <img src="assets/img_1.png" alt="">
                    <img src="assets/img_2.png" alt="">
                    <img src="assets/img_3.png" alt="">
                </div>

            </div>

            <div class="row">
                <div class="col-md-3">
                    <div class="card bg-light mb-3">
                        <div class="vertical_menu">
                            <div class="choose">
                                <div class="category">
                                    <h2>Categories</h2>
                                </div>
                                <div class="list-group ">
                                    <c:forEach items="${category}" var="o">
                                        <form action="Home" method="post">
                                            <input type="hidden" name="cid" value="${o.id}" />
                                            <button type="submit" class="list-group-item list-group-item-action ${tag == o.id ? 'active' : ''}">
                                                ${o.name}
                                            </button>
                                        </form>

                                    </c:forEach>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="card bg-light mb-3">
                        <div class="vertical_menu">
                            <div class="choose">
                                <div class="brand">
                                    <h2>Brand</h2>
                                </div>
                                <div class="list-group ">
                                    <c:forEach items="${brands}" var="brand">
                                        <form action="Home" method="post">
                                            <input type="hidden" name="bid" value="${brand.id}" />
                                            <button type="submit" class="list-group-item list-group-item-action ${tagg == brand.id ? 'active' : ''}">
                                                ${brand.name}
                                            </button>
                                        </form>

                                    </c:forEach>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>


                <div class="col-md-9">
                    <div class="row">

                        <div class="new_sale_off">
                            <div class="new_sale_off-title">
                                <h1>LIST PRODUCTS</h1>
                            </div>

                            <div class="new_sale_off-list">
                                <c:forEach items="${products}" var="product">
                                    <form action="ShoppingCart" method="Post">
                                        <div class="new_sale_off-item">
                                            <img class="productImage" src="${product.image}" />
                                            <h4><a class="nameProduct" href="$Detail?pid=${product.id}">${product.name}</a></h4>
                                            <h6>${product.category.name}</h6>
                                            <p>${product.price} $</p>

                                            <input type="hidden" name="productId" value="${product.id}">
                                            <div class="input-group">


                                                <input class="form-control form-control-sm nu" type="number" min="1" max="1000" name="quantity" required>
                                                <button type="submit" class="btn btn-sm btn-secondary" name="action" value="addtocart">Add</button>

                                            </div>
                                        </div>
                                    </form>
                                </c:forEach>

                            </div>
                            <div style="clear: both; margin-bottom: 10px;"></div>
                        </div>

                    </div>
                </div>
            </div>

            <div class="footer">

                <p>Email : slccompany@gmail.com</p>
                <p>Address: 15 Nguyen Cong Tru, District 1, HCMC</p>
                <h5>&copy; Copyright 2023. Slc.com</h5>

            </div>
        </div>
        <%}%>
    </body>

</html>