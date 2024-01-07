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
        <h1 class="red">You are not logged into the system. Please return to the <a href="Login">Login Page</a>!</h1>
        <%
        } else {
            String error = request.getAttribute("error") + "";
            if (error.equals("null")) {
                error = "";
            }
        %>
        <div class="container">
            <div class="success-icon">&#10004;</div>
            <h2>Order Successfully</h2>
            <p>Congratulations on your successful order, see you next time!!!</p>
            <p><a href="./Home">Go to Home Page to continue buy products</a></p>
        </div>
          <%}%>
    </body>
</html>

