<%--
  Created by IntelliJ IDEA.
  User: Хоменко Сергій
  Date: 06.07.2017
  Time: 16:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <title>Add book page</title>

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
                <li><a href="/books">Books</a></li>
                <li><a href="/users">Users</a></li>
                <li><a href="/orders">Orders</a></li>
            </ul>
            <form class="navbar-form navbar-right ">
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

<div class="container-fluid main-content">
    <form method="post" action="/books">
        <div class="form-group">
            <label for="bookId">Book id</label>
            <p class="form-control-static" id="bookId">${book.id}</p>
            <input type="hidden" name="bookId" value="${book.id}">
        </div>
        <div class="form-group">
            <label for="editBookTitle">Book title</label>
            <input type="text" class="form-control"  name="bookTitle" id="editBookTitle" value="${book.title}"/>
        </div>
        <div class="form-group">
            <label for="editBookAuthor">Book author</label>
            <input type="text" class="form-control"  name="bookAuthor" id="editBookAuthor" value="${book.author}"/>
        </div>
        <div class="form-group">
            <label for="editInventoryNumber">Inventory number</label>
            <input type="text" class="form-control"  name="bookNumber" id="editInventoryNumber" value="${book.inventoryNumber}"/>
        </div>
        <input type="hidden" name="command" value="editBook">
        <input type="submit" class="btn btn-success btn-lg" value="Save changes"/>
    </form>
</div>
<script src="../resources/js/jquery.min.js"></script>
<script src="../resources/js/bootstrap.min.js"></script>
</body>
</html>
