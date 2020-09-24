<%--
  Created by IntelliJ IDEA.
  User: 1407162
  Date: 24-09-2020
  Time: 14:28
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
          integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    <link href="register.css" rel="stylesheet">
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <c:if test="${user != null}">
                <a class="nav-link" href="./register.jsp">Edit user<span class="sr-only">(current)</span></a>
            </c:if>
            <c:if test="${user == null}">
                <li class="nav-item">
                    <a class="nav-link" href="./login.jsp">User login</a>
                </li>
                <li class="nav-item active">
                    <a class="nav-link" href="./register.jsp">
                        User Registration<span class="sr-only">(current)</span>
                    </a>
                </li>
            </c:if>

        </ul>
    </div>
</nav>
<div class="container">
    <div class="card">
        <div class="card-body">
            <c:if test="${user != null}">
                <form action="register" method="post">
            </c:if>
            <c:if test="${user == null}">
                <form action="update" method="post">
            </c:if>
            <caption>
                <h1 class="h3 text-center mb-3 font-weight-normal">
                    <c:if test="${user != null}">
                        Registration
                    </c:if>
                    <c:if test="${user == null}">
                        Edit User
                    </c:if>
                </h1>
            </caption>
                <div class="form-group">
                    <label for="firstName">First Name</label>
                    <input type="text" class="form-control" name="firstName" id="firstName"
                           value="<c:out value='${user.firstName}' />" >
                </div>
                <div class="form-group">
                    <label for="lastName">Last Name</label>
                    <input type="text" class="form-control" name="lastName" id="lastName"
                           value="<c:out value='${user.lastName}' />">
                </div>
                <div class="form-group">
                    <label for="email">Email Id</label>
                    <input type="text" class="form-control" name="email" id="email"
                           value="<c:out value='${user.email}' />">
                </div>
                <div class="form-group">
                    <label for="password">Password</label>
                    <input type="text" class="form-control" name="password" id="password"
                           value="<c:out value='${user.password}' />">
                </div>
                <div class="form-group">
                    <label for="phoneNo">Phone No.</label>
                    <input type="text" class="form-control" name="phoneNo" id="phoneNo"
                           value="<c:out value='${user.phoneNo}' />">
                </div>
                <button type="submit" class="btn btn-primary">Save</button>
                </form>
        </div>
    </div>
    <form action="LoginServlet" method="post">

</div>
</body>
</html>