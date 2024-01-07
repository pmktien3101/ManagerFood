<%-- 
    Document   : login
    Created on : Dec 22, 2023, 11:26:09 PM
    Author     : MSI PC
--%>

<%@page import="error.ValidationError"%>
<%@page import="java.util.List"%>
<%@page import="model.Account"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>ForgetPass Page</title>
        <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css">


        <%
            String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
                    + request.getContextPath();
        %>
        <link href="<%=url%>/css/login.css" rel="stylesheet">
    </head>
    <body>

        <%
            List<ValidationError> errors = (List<ValidationError>) request.getAttribute("errors");

            String error = request.getAttribute("error") + "";
            if (error.equals("null")) {
                error = "";
            }
        %>

        <div class="bg-img">
            <div class="content">
                <header>Change Password</header>
                <div class="red"><%=error%></div>

                <form action="ForgetPass" method="post">
                    <div class="field">
                        <span class="fa fa-user"></span>
                        <input type="text" id="email" name="email" required placeholder="Email">
                    </div>
                    <div class="field space">
                        <span class="fa fa-lock"></span>
                        <input type="password" class="pass-key" id="password" name="password" required placeholder="NewPassword">
                        <span class="show">SHOW</span>
                    </div>
                    <div class="field space">
                        <span class="fa fa-lock"></span>
                        <input type="password" class="pass-key" id="confirm" name="confirm" required placeholder="Confirm">
                        <span class="show">SHOW</span>
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
                    <div class="field">
                        <input type="submit" value="Save">
                    </div>
                </form>

                <div class="signup">

                    <a href="Login">Login Now</a>
                </div>
            </div>
        </div>
        <script>
            const newPasswordField = document.getElementById('password');
            const confirmField = document.getElementById('confirm');
            const showBtns = document.querySelectorAll('.show');

            showBtns.forEach(function (showBtn) {
                showBtn.addEventListener('click', function () {
                    if (newPasswordField.type === "password" || confirmField.type === "password") {
                        newPasswordField.type = "text";
                        confirmField.type = "text";
                        showBtn.textContent = "HIDE";
                        showBtn.style.color = "#3498db";
                    } else {
                        newPasswordField.type = "password";
                        confirmField.type = "password";
                        showBtn.textContent = "SHOW";
                        showBtn.style.color = "#222";
                    }
                });
            });
        </script>


    </body>
</html>