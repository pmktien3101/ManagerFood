<%@page import="model.Region"%>
<%@page import="error.ValidationError"%>
<%@page import="java.util.List"%>
<%@page import="model.Account"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Change Name</title>
        <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css" rel="stylesheet" >
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.min.js" integrity="sha384-Atwg2Pkwv9vp0ygtn1JAojH0nYbwNJLPhwyoVbhoPwBhjQPR5VtM2+xf0Uwh9KtT" crossorigin="anonymous"></script>
        <%
            String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
                    + request.getContextPath();
        %>
        <link href="<%=url%>/css/login.css" rel="stylesheet">
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
        <h1>You are not logged into the system. Please return to the <a href="Login">Login page</a>!</h1>
        <%
        } else {
            List<ValidationError> errors = (List<ValidationError>) request.getAttribute("errors");

            String name = account.getName();
            int region = account.getRegion().getId();

            String error = request.getAttribute("error") + "";
            if (error.equals("null")) {
                error = "";
            }

        %>
        <div class="bg-img">
            <div class="content">
                <header>Change Name</header>
                <div class="red"><%=error%></div>
                <div>
                    <% if (errors != null) { %>
                    <% for (ValidationError err : errors) { %>
                    <% if ("name".equals(err.getName())) {%>
                    <div class="red"><%= err.getMessage()%></div>
                    <% } %>
                    <% } %>
                    <% }%>

                </div>

                <form action="ChangeName" method="post">
                    <div class="field">
                        <span class="fa fa-user"></span>
                        <input type="text" id="name" name="name" required placeholder="OldCompanyName" value="<%=name%>" readonly>
                    </div>
                    <div class="field">
                        <span class="fa fa-user"></span>
                        <input type="text" id="newName" name="newName" required placeholder="NewCompanyName">
                    </div>

                    <div class="field">
                        <input class="btn btn-primary form-control" type="submit" value="Save" name="submit" id="submit" />
                    </div>
                </form>

                <div class="signup">
                    <a href="./Home">Return</a>
                </div>
            </div>
        </div>

        <%
            }
        %>
    </body>
</html>
