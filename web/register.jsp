<%-- 
    Document   : register
    Created on : Dec 23, 2023, 12:28:39 AM
    Author     : MSI PC
--%>

<%@page import="error.ValidationError"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css">
        <title>Register Page</title>

        <%
            String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
                    + request.getContextPath();
        %>
        <link href="<%=url%>/css/register.css" rel="stylesheet">
        <script type="text/javascript" src="<%=url%>/js/register.js"></script>

    </head>

    <body>
        <%
            List<ValidationError> errors = (List<ValidationError>) request.getAttribute("errors");
            String name = request.getAttribute("name") + "";
            name = (name.equals("null")) ? "" : name;
            String email = request.getAttribute("email") + "";
            email = (email.equals("null")) ? "" : email;
            String phone = request.getAttribute("phone") + "";
            phone = (phone.equals("null")) ? "" : phone;
            String region = request.getAttribute("region") + "";
            region = (region.equals("null")) ? "" : region;
            String taxCode = request.getAttribute("taxcode") + "";
            taxCode = (taxCode.equals("null")) ? "" : taxCode;
            String error = request.getAttribute("error") + "";
            error = (error.equals("null")) ? "" : error;

        %>

        <div id="wrapper">

            <form action="Register" method="post" id="form-login">
                <div id="form">
                    <h1 class="form-heading">Form Register</h1>                            
                    <div class="red"><%=error%></div>

                    <div class="infor">
                        <div class="accountInfo">
                            <h3>Company Infor</h3>
                            <div class="form-group">
                                <i class="far fa-user"></i>
                                <input type="text" class="form-input" id="name" name="name" placeholder="CompanyName" required="required" value="<%=name%>">
                                <div>
                                    <% if (errors != null) { %>
                                    <% for (ValidationError err : errors) { %>
                                    <% if ("name".equals(err.getName())) {%>
                                    <div class="red"><%= err.getMessage()%></div>
                                    <% } %>
                                    <% } %>
                                    <% }%>

                                </div>

                            </div>
                            <div class="form-group">
                                <i class="far fa-envelope"></i>
                                <input type="text" class="form-input" id="taxcode" name="taxcode" placeholder="EIN-xxxxx"
                                       required="required" value="<%=taxCode%>">
                                <div>
                                    <% if (errors != null) { %>
                                    <% for (ValidationError err : errors) { %>
                                    <% if ("taxcode".equals(err.getName())) {%>
                                    <div class="red"><%= err.getMessage()%></div>
                                    <% } %>
                                    <% } %>
                                    <% }%>

                                </div>
                            </div>
                            <div class="form-group">
                                <i class="far fa-phone"></i>
                                <input type="tel" class="form-input" id="phone" name="phone" placeholder="Phone" required="required" value="<%=phone%>">
                                <div>
                                    <% if (errors != null) { %>
                                    <% for (ValidationError err : errors) { %>
                                    <% if ("phone".equals(err.getName())) {%>
                                    <div class="red"><%= err.getMessage()%></div>
                                    <% } %>
                                    <% } %>
                                    <% }%>

                                </div>
                            </div>
                            <div class="form-group">
                                <label style="color:white">Region</label>
                                <select name="region" class="form-select" aria-label="Default select example">
                                    <c:forEach items="${regions}" var="region">
                                        <option value="${region.id}">${region.name}</option>
                                    </c:forEach>
                                </select>

                            </div>

                        </div>
                        <div class="accountInfo">
                            <h3>Account Infor</h3>
                            <div class="form-group">
                                <i class="far fa-envelope"></i>
                                <input type="text" class="form-input" id="email" name="email" placeholder="Email@gmail.com"
                                       required="required" value="<%=email%>">
                                <div>
                                    <% if (errors != null) { %>
                                    <% for (ValidationError err : errors) { %>
                                    <% if ("email".equals(err.getName())) {%>
                                    <div class="red"><%= err.getMessage()%></div>
                                    <% } %>
                                    <% } %>
                                    <% }%>

                                </div>
                            </div>

                            <div class="form-group">
                                <i class="fas fa-key"></i>
                                <input type="password" class="form-input" id="password" name="password" placeholder="Password" required="required">
                                <div class="eye">
                                    <i class="far fa-eye"></i>
                                </div>
                                <div>
                                    <% if (errors != null) { %>
                                    <% for (ValidationError err : errors) { %>
                                    <% if ("password".equals(err.getName())) {%>
                                    <div class="red"><%= err.getMessage()%></div>
                                    <% } %>
                                    <% } %>
                                    <% }%>

                                </div>
                            </div>

                            <div class="form-group">
                                <i class="fas fa-key"></i>
                                <input type="password" class="form-input" id="confirm" name="confirm" placeholder="ConfirmPassword" required="required">
                                <div class="eye">
                                    <i class="far fa-eye"></i>
                                </div>
                                <div>
                                    <% if (errors != null) { %>
                                    <% for (ValidationError err : errors) { %>
                                    <% if ("confirm".equals(err.getName())) {%>
                                    <div class="red"><%= err.getMessage()%></div>
                                    <% } %>
                                    <% } %>
                                    <% }%>

                                </div>

                            </div>

                        </div>
                    </div>

                    <div class="term">

                        <label for="terms" class="form-lable">I agree to the Terms of User <span class="red">*</span>
                        </label>
                        <input type="checkbox" class="form-check-input" id="terms" name="terms" required="required">

                    </div>
                    <input type="hidden" name="action" value="registerUser">
                    <input class="btn btn-primary form-control" type="submit" value="Register" name="submit" id="submit" />

                    <div class="haveAccount">
                        <div>Already have an account? </div>
                        <a href="Login">Login</a>
                    </div>
                </div>
            </form>
        </div>
        <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
        <script>
            $(document).ready(function () {
                $('.eye').click(function () {
                    $(this).toggleClass('open');
                    $(this).children('i').toggleClass('fa-eye-slash fa-eye');
                    if ($(this).hasClass('open')) {
                        $(this).prev().attr('type', 'text');
                    } else {
                        $(this).prev().attr('type', 'password');
                    }
                });
            });


        </script>
    </body>

</html>