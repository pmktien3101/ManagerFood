<%-- 
    Document   : login
    Created on : Dec 22, 2023, 11:26:09 PM
    Author     : MSI PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Login Page</title>
        <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css">


        <%
            String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
                    + request.getContextPath();
        %>
        <link href="<%=url%>/css/login.css" rel="stylesheet">
    </head>
    <body>
        <%
            String error = request.getAttribute("error") + "";
            error = (error.equals("null")) ? "" : error;
        %>
        <div class="bg-img">
            <div class="content">
                <header>Form Login</header>
                <div class="red"><%=error%></div>

                <form action="Login" method="post">
                    <div class="field">
                        <span class="fa fa-user"></span>
                        <input type="text" id="email" name="email" required placeholder="Email">
                    </div>
                    <div class="field space">
                        <span class="fa fa-lock"></span>
                        <input type="password" class="pass-key" id="password" name="password" required placeholder="Password">
                        <span class="show">SHOW</span>
                    </div>
                    
                    <div class="pass">
                        <a href="forgotPassword.jsp">Forgot Password?</a>
                    </div>
                    <div class="field">
                        <input type="submit" value="LOGIN">
                    </div>
                    
                </form>

                <div class="signup">
                    Don't have account?
                   <a href="<%=request.getContextPath()%>/Register">Register Now</a>

                </div>
            </div>
        </div>
        <script>

            const pass_field = document.querySelector('.pass-key');
            const showBtn = document.querySelector('.show');
            showBtn.addEventListener('click', function () {
                if (pass_field.type === "password") {
                    pass_field.type = "text";
                    showBtn.textContent = "HIDE";
                    showBtn.style.color = "#3498db";
                } else {
                    pass_field.type = "password";
                    showBtn.textContent = "SHOW";
                    showBtn.style.color = "#222";
                }
            });

        </script>
    </body>
</html>