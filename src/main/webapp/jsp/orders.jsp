<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <title>All orders</title>
    <!-- Bootstrap core CSS -->
    <link href="../resources/css/bootstrap.min.css" rel="stylesheet"/>

    <!-- Custom styles for this template -->
    <link href="../resources/css/custom.css" rel="stylesheet"/>

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
            <form action="/controller" class="navbar-form navbar-right">
                <input type="hidden" name="command" value="signout">
                <input type="submit" value="Sign out">
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
<div class="container-fluid padd">
    <div class="row">
        <h2 class="sub-header">Full order list</h2>
        <div class="table-responsive">
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>Order id</th>
                        <th>User id</th>
                        <th>First name</th>
                        <th>Last name</th>
                        <th>Book id</th>
                        <th>Book title</th>
                        <th>Book author</th>
                        <th>Book number</th>
                        <th>Date receive</th>
                        <th>Date return</th>
                        <th>Reading Place</th>
                        <th>Order status</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                <c:forEach var="order" items="${orders}">
                    <tr>
                        <td>${order.id}</td>
                        <td>${order.user.id}</td>
                        <td>${order.user.firstName}</td>
                        <td>${order.user.lastName}</td>
                        <td>${order.book.id}</td>
                        <td>${order.book.title}</td>
                        <td>${order.book.author}</td>
                        <td>${order.book.inventoryNumber}</td>
                        <td>${order.dateOfReceive}</td>
                        <td>${order.dateOfReturn}</td>
                        <td>${order.place}</td>
                        <td>${order.status}</td>
                        <td>
                            <form method="get" action="/controller">
                                <input type="hidden" name="bookId" value="${order.id}">
                                <input type="hidden" name="bookTitle" value="${order.user.id}">
                                <input type="hidden" name="bookAuthor" value="${order.user.firstName}">
                                <input type="hidden" name="bookNumber" value="${order.user.lastName}">
                                <input type="hidden" name="bookId" value="${order.book.id}">
                                <input type="hidden" name="bookTitle" value="${order.book.title}">
                                <input type="hidden" name="bookAuthor" value="${order.book.author}">
                                <input type="hidden" name="bookNumber" value="${order.book.inventoryNumber}">
                                <input type="hidden" name="bookId" value="${order.dateOfReceive}">
                                <input type="hidden" name="bookTitle" value="${order.dateOfReturn}">
                                <input type="hidden" name="bookAuthor" value="${order.place}">
                                <input type="hidden" name="bookNumber" value="${order.status}">
                                <input type="hidden" name="command" value="editorder">
                                <button type="submit" class="btn btn-success btn-lg">Edit</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
<script src="../resources/js/jquery.min.js"></script>
<script src="../resources/js/bootstrap.min.js"></script>
</body>
</html>
