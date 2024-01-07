<%-- 
    Document   : index
    Created on : Dec 25, 2023, 10:18:54 PM
    Author     : MSI PC
--%>

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
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" integrity="sha512-xxx" crossorigin="anonymous" />
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" ></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.min.js" integrity="sha384-Atwg2Pkwv9vp0ygtn1JAojH0nYbwNJLPhwyoVbhoPwBhjQPR5VtM2+xf0Uwh9KtT" crossorigin="anonymous"></script>
       
        <title>Admin Page </title>

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
                        <a href="ManagerAccount">Manage Account</a> <i class="fas fa-gear"></i> 
                    </div>
                    <div class="item">
                        <a href="ManagerSupplier">Manage SupplierCompany</a> <i class="fas fa-gear"></i> 
                    </div>
                    <div class="item">
                        <a href="ManagerProduct">Manage Product</a> <i class="fas fa-gaer"></i> 
                    </div>
                    <div class="item">
                        <a href="MessageReceived">Messages Received</a> <i class="fas fa-edit"></i>
                    </div>
                    <div class="item">
                        <a href="ManagerOrder">Order Received</a> <i class="fas fa-shopping-cart"></i> 
                    </div>
                    
                </div>
                <div id="account">
                    <div class="tien">
                        <i class="fas fa-user"></i>
                        <a class="btn btn-primary" href="./Logout">Logout</a>
                    </div>  

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

            <div class="footer">

                <p>Email : slccompany@gmail.com</p>
                <p>Address: 15 Nguyen Cong Tru, District 1, HCMC</p>
                <h5>&copy; Copyright 2023. Slc.com</h5>

            </div>
        </div>
        <%}%>
    </body>

</html>