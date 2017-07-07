<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <title>Edit order page</title>

    <!-- Bootstrap core CSS -->
    <link href="../resources/css/bootstrap.min.css" rel="stylesheet"/>

    <!-- Custom styles for this template -->
    <link href="../resources/css/editbook.css" rel="stylesheet"/>

    <link rel="icon" href="../resources/favicon.ico"/>
</head>
<body>
<nav class="navbar navbar-default navbar-fixed-top">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse visible-lg-inline">
            <ul class="nav navbar-nav">
                <li><a href="/librarian">Home</a></li>
                <li><a href="/books">Books</a></li>
                <li><a href="/users">Users</a></li>
                <li><a href="/orders">Orders</a></li>
            </ul>
            <form action="/controller" class="navbar-form navbar-right ">
                <input type="hidden" name="command" value="signout">
                <input type="submit" class="btn btn-default" value="Sign out">
            </form>
            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Language<span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="#">Ru</a></li>
                        <li><a href="#">En</a></li>
                    </ul>
                </li>
            </ul>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>
<div class="container-fluid main-content">
    <h2 class="sub-header">Edit order:</h2>
    <form method="post" action="/controller">
        <div class="form-group">
            <label for="orderId">Order id</label>
            <p class="form-control-static" id="orderId">${order.id}</p>
            <input type="hidden" name="orderId" value="${order.id}">
        </div>
        <div class="form-group">
            <label for="userId">User id</label>
            <p class="form-control-static" id="userId">${order.user.id}</p>
            <input type="hidden" class="form-control"  name="userId" value="${order.user.id}"/>
        </div>
        <div class="form-group">
            <label for="bookId">Book id</label>
            <p class="form-control-static" id="bookId">${order.book.id}</p>
            <input type="hidden" class="form-control"  name="bookId" value="${order.book.id}"/>
        </div>
        <div class="form-group">
            <label for="dateReceive">Date of receive</label>
            <p class="form-control-static" id="dateReceive">${order.dateOfReceive}</p>
            <input type="hidden" class="form-control"  name="dateReceive" value="${order.dateOfReceive}"/>
        </div>
        <c:set var="datereturn" value="${order.dateOfReturn}"/>
        <c:if test="${empty datereturn}">
            <div class="form-group">
                <label for="dateReturn">Set date of return</label>
                <input type="date" class="form-control" name="dateReturn" id="dateReturn">
            </div>
        </c:if>
        <c:set var="status" value="${order.status}"/>
        <c:if test="${status eq 'OPEN' }">
            <select name="orderStatus">
                <p>Set order status:</p>
                <option value="closed">CLOSED</option>
                <option value="open">OPEN</option>
            </select>
        </c:if>
        <br/>

        <input type="hidden" name="readPlace" value="${order.place}">
        <input type="hidden" name="orderStatus" value="${order.status}">
        <input type="hidden" name="command" value="editOrder">
        <input type="submit" class="btn btn-success btn-lg" value="Save changes"/>
    </form>
</div>
<script src="../resources/js/jquery.min.js"></script>
<script src="../resources/js/bootstrap.min.js"></script>
</body>
</html>
