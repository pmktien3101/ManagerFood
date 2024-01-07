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

        <div id="wrapper">       
            <div id="header">
                <a href="" class="logo">
                    <img src="assets/logo1.png" alt="">
                </a>
                <div id="menu">
                    <div class="item">
                        <button>Home</button>
                    </div>
                    <div class="item">
                        <div class="main">
                            <button class="openbtn">Product</button>
                        </div>
                    </div>
                    <div class="item">
                        <button>About Us</button>
                    </div>
                    <div class="item">
                        <button>Contact</button>
                    </div>
                </div>
                <div class="cart">
                    <i class="fas fa-shopping-cart"></i> Cart 
                </div>
                <div class="search">
                    <input class="form-control me-2" type="search"
                           placeholder="Search" aria-label="Search">
                    <button class="btn btn-outline-success" type="submit">Search</button>
                </div>

                <div id="account">

                    <div class="tien">
                        <i class="fas fa-user"></i>
                        <a class="btn btn-primary" href="Login">Login</a>
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
        
    </body>

</html>