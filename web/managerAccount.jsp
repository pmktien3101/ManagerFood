<%-- 
    Document   : ManagerProduct
    Created on : Dec 28, 2020, 5:19:02 PM
    Author     : trinh
--%>

<%@page import="model.Account"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Manager Account</title>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <%
            String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
                    + request.getContextPath();
        %>
        <link href="<%=url%>/css/managerProduct.css" rel="stylesheet">
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
            String error = request.getAttribute("error") + "";
            if (error.equals("null")) {
                error = "";
            }
        %>
        <div class="container">
            <div class="table-wrapper">
                <div class="table-title">
                    <div class="row">
                        <div class="col-sm-6">
                            <h2 class="titleP">MANAGER ACCOUNT</h2>

                        </div>
                        <div class="col-sm-6 action">
                            <div class="dess">
                                <a href="#" class="btn btn-success" data-toggle="modal" data-target="#addRegionModal">
                                    <i class="material-icons">&#xE147;</i>
                                    <span>Add New Region</span>
                                </a>
                                <a class="home" href="adminHome.jsp">Return Home</a>
                            </div>						
                        </div>
                    </div>
                </div>

                <div class="red" style="color:red; text-align:center; font-size: 50px"><%=error%></div>

                <c:if test="${empty error}">           
                    <div class="row">

                        <div class="col-sm-8">
                            <table class="table table-striped table-hover">
                                <thead>
                                    <tr>
                                        <th>Email</th>
                                        <th>Name</th>
                                        <th>TaxCode</th>
                                        <th>Region</th>
                                        <th>Phone</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${accounts}" var="account">
                                        <tr>
                                            <td>${account.email}</td>
                                            <td>${account.name}</td> 
                                            <td>${account.taxCode}</td>
                                            <td>${account.region.name}</td>
                                            <td>${account.phone}</td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                        <div class="col-sm-4">
                            <table class="table table-striped table-hover">
                                <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>Region</th>
                                        <th>Action</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${regions}" var="region">
                                        <tr>
                                            <td>${region.id}</td> 
                                            <td>${region.name}</td> 
                                            <td>
                                                <a href="ManagerAccount?action=deleteRegion&id=${region.id}" class="delete" data-toggle="modal">
                                                    <i style="color: red" class="material-icons" data-toggle="tooltip" title="Delete">&#xE872;</i>
                                                </a>


                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                   
                </c:if>
            </div>
        </div>
        <!-- Add New Region Modal -->
        <div id="addRegionModal" class="modal fade">
            <div class="modal-dialog">
                <div class="modal-content">
                    <form action="ManagerAccount" method="post">
                        <div class="modal-header">
                            <h4 class="modal-title">Add New Region</h4>
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        </div>
                        <div class="modal-body">
                            <div class="form-group">
                                <label>Region Name:</label>
                                <input type="text" class="form-control" name="name" required>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="submit" class="btn btn-success">Add Region</button>
                        </div>
                        <input type="hidden" name="action" value="addRegion">
                    </form>
                </div>
            </div>
        </div>


        <%}%>
    </body>
</html>
