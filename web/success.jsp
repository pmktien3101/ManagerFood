<%-- 
    Document   : success
    Created on : Dec 24, 2023, 8:38:26 PM
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
        <title>Success Page</title>
        
        <%
            String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
                    + request.getContextPath();
        %>
        <link href="<%=url%>/css/success.css" rel="stylesheet">
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
        <div class="container">
            <div class="success-icon">&#10004;</div>
            <h2>Account Created Successfully</h2>
            <p>Your account has been created successfully. Welcome to our community!</p>
            <p><a href="Login">Go to Login Page</a></p>
        </div>
           <%}%>
    </body>
</html>

