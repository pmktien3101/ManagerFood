<%@page import="model.Account"%>
<html>
    <head>
        <script src='https://kit.fontawesome.com/a076d05399.js'></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css" rel="stylesheet" >
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" ></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.min.js" integrity="sha384-Atwg2Pkwv9vp0ygtn1JAojH0nYbwNJLPhwyoVbhoPwBhjQPR5VtM2+xf0Uwh9KtT" crossorigin="anonymous"></script>
        <title>Message Us</title>
        <%
            String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
                    + request.getContextPath();
        %>
        <link href="<%=url%>/css/message.css" rel="stylesheet">
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
        <div class="title">Message Us <i class='fas fa-comment-alt'></i></div>
            <%
                String msg = (String) request.getAttribute("msg");
                if ("valid".equals(msg)) {

            %>
        <h3 class="msg">Message successfully sent. Our company will contact you soon!</h3>
        <%}%>
        <%
            if ("invalid".equals(msg)) {
        %>
        <h3 class="msg">Some thing Went Wrong! Try Again!</h3>
        <%}%>
        <form action="Message" method="post" class="form">
            <input class="input-style subject" name="subject" type="text" placeholder="subject" required>
            <hr>
            <textarea class="input-style text" name="body" type="text" placeholder="Enter your message" required></textarea>
            <div>
                <button class="button" type="submit">Send<i class="fas fa-arrow-alt-circle-right"></i></button>
                <a class="home" href="./Home">Return Home</a>
            </div>
           
        </form>

        <div class="footer">

            <p>Email : slccompany@gmail.com</p>
            <p>Address: 15 Nguyen Cong Tru, District 1, HCMC</p>
            <h5>&copy; Copyright 2023. Slc.com</h5>

        </div>
         <%}%>
    </body>
</html>