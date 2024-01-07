<%-- 
    Document   : Detail
    Created on : Dec 29, 2020, 5:43:04 PM
    Author     : trinh
--%>

<%@page import="model.Account"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Product Detail</title>
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <%
            String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
                    + request.getContextPath();
        %>
        <link href="<%=url%>/css/productDetail.css" rel="stylesheet">

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
        <h1 class="red">You are not logged into the system. Please return to the <a href="./Login">Login page</a>!</h1>
        <%
        } else {

        %>
        <div class="row">
            <div class="container">
                <div class="card">
                    <div class="row">
                        <aside class="col-sm-3 border-right">
                            <article class="gallery-wrap"> 
                                <div class="img-big-wrap">
                                    <div> <a href="#"><img src="${detail.image}"></a></div>

                                    <div class="img-small-wrap">
                                    </div> <!-- slider-nav.// -->
                                    </aside>
                                    <aside class="col-sm-7">
                                        <article class="card-body p-5">
                                            <h3 class="title mb-3">${detail.name}</h3>

                                            <p class="price-detail-wrap"> 
                                                <span class="price h3 text-warning"> 
                                                    <span class="currency"> $</span><span class="num">${detail.price}</span>
                                                </span> 
                                            </p> <!-- price-detail-wrap .// -->
                                            <dl class="item-property">
                                                <dt>Description</dt>
                                                <dd><p>
                                                        ${detail.description}
                                                    </p></dd>
                                            </dl>

                                            <hr>
                                            <div class="row">
                                                <div class="col-sm-5">
                                                    <dl class="param param-inline">
                                                        <dt>Quantity: </dt>
                                                        <dd>
                                                            <select class="form-control form-control-sm" style="width:70px;">
                                                                <option> 1 </option>
                                                                <option> 2 </option>
                                                                <option> 3 </option>
                                                                <option> 4 </option>
                                                                <option> 5 </option>

                                                            </select>
                                                        </dd>
                                                    </dl> 
                                                </div> 

                                            </div> 
                                            <hr>
                                            <a href="#" class="btn btn-lg btn-primary text-uppercase"> Buy now </a>
                                            <a href="#" class="btn btn-lg btn-outline-primary text-uppercase"> <i class="fas fa-shopping-cart"></i> Add to cart </a>
                                            <a href="./Home" class="btn btn-lg btn-outline-primary text-uppercase"> <i class="fas fa-shopping-cart"></i> Return Home</a>

                                        </article> 
                                    </aside>
                                </div> 
                                </div>

                                </div>
                                </div>
                                <div class="footer">

                                    <p>Email : slccompany@gmail.com</p>
                                    <p>Address: 15 Nguyen Cong Tru, District 1, HCMC</p>
                                    <h5>&copy; Copyright 2023. Slc.com</h5>

                                </div>

                                <%            }
                                %>
                                </body>
                                </html>
