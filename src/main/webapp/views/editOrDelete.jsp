<%--
  Created by IntelliJ IDEA.
  User: 1407162
  Date: 24-09-2020
  Time: 21:09
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit or delete</title>
    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
          integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
</head>
<body>
<header>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item active">
                    <a class="nav-link" href="./login.jsp">
                            Login<span class="sr-only">(current)</span>
                    </a>
                </li>
            </ul>
        </div>
    </nav>
</header>
<div class="row">
    <div class="container">
        <h2 class="text-center">
            User detail
        </h2>
        <hr>
        <br>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Email Id</th>
                <th>Password</th>
                <th>Phone No.</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td><c:out value="${user.firstName}" /></td>
                <td><c:out value="${user.lastName}" /></td>
                <td><c:out value="${user.email}" /></td>
                <td><c:out value="${user.password}" /></td>
                <td><c:out value="${user.phoneNo}" /></td>
                <td><a href="edit?id=<c:out value='${user.id}' />">Edit</a>
                    &nbsp;&nbsp;&nbsp;&nbsp; <a
                            href="delete?id=<c:out value='${user.id}' />">Delete</a></td>
            </tr>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
