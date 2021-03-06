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
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">
                    <li class="nav-item">
                        <a class="nav-link" href="./login.jsp">User login</a>
                    </li>
                    <li class="nav-item active">
                        <a class="nav-link" href="./register.jsp">
                            User Registration<span class="sr-only">(current)</span>
                        </a>
                    </li>
            </ul>
        </div>
    </nav>
<div class="container">
    <div class="card">
        <div class="card-body">
            <div class="card-body">
                <c:if test="${user != null}">
                <form action="update" method="post">
                    </c:if>
                    <c:if test="${user == null}">
                    <form action="register" method="post">
                        </c:if>

                        <caption>
                            <h2>
                                <c:if test="${user != null}">
                                    Edit User
                                </c:if>
                                <c:if test="${user == null}">
                                    Add New User
                                </c:if>
                            </h2>
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
                <button type="submit" class="btn btn-success">Save</button>
                </form>
        </div>
    </div>
    </div>
</div>
</body>
</html>