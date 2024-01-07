<%@page import="model.Account"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Message Received</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
        <%
            String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
                    + request.getContextPath();
        %>
        <link href="<%=url%>/css/messageReceived.css" rel="stylesheet">
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
        <div class="container">
            <div class="title">Messages Received <i class='fas fa-comment-alt'></i></div>
        <table>
            <thead>
                <tr>
                    <th scope="col">ID</th>
                    <th scope="col">Email</th>
                    <th scope="col">Subject</th>
                    <th scope="col">Body</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${messages}" var="m">
                    <tr>

                        <td>${m.id}</td>
                        <td>${m.email}</td>
                        <td>${m.subject}</td>
                        <td>${m.body}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <div class="footer">

            <p>Email : slccompany@gmail.com</p>
            <p>Address: 15 Nguyen Cong Tru, District 1, HCMC</p>
            <h5>&copy; Copyright 2023. Slc.com</h5>

        </div>
        </div>
        <%}%>
    </body>
</html>
