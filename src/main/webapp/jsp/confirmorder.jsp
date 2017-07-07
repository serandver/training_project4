<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content=""/>
    <meta name="author" content=""/>
    <link rel="icon" href="../resources/favicon.ico"/>

    <title>Confirm order page</title>

    <!-- Bootstrap core CSS -->
    <link href="../resources/css/bootstrap.min.css" rel="stylesheet"/>

    <!-- Custom styles for this template -->
    <link href="../resources/css/custom.css" rel="stylesheet"/>
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
                <input type="submit" class="btn btn-default"  value="Sign out">
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
    <div class="row">
        <h2 class="sub-header">Confirm new book order</h2>
        <form method="post" action="/controller">
            <div class="form-group">
                <label for="orderId">Order id</label>
                <p class="form-control-static" id="orderId">${order.id}</p>
                <input type="hidden" class="form-control" name="orderId" value="${order.id}">
            </div>
            <div class="form-group">
                <label for="bookId">Book id</label>
                <p class="form-control-static" id="bookId">${order.book.id}</p>
                <input type="hidden" class="form-control" name="bookId" value="${order.book.id}">
            </div>
            <div class="form-group">
                <label for="bookTitle">Book title</label>
                <p class="form-control-static" id="bookTitle">${order.book.title}</p>
                <input type="hidden"  class="form-control" name="bookTitle" value="${order.book.title}">
            </div>
            <div class="form-group">
                <label for="bookAuthor">Book author</label>
                <p class="form-control-static" id="bookAuthor">${order.book.author}</p>
                <input type="hidden"  class="form-control" name="bookAuthor" value="${order.book.author}">
            </div>
            <div class="form-group">
                <label for="orderPlace">Reading place</label>
                <p class="form-control-static" id="orderPlace">${order.place}</p>
                <input type="hidden"  class="form-control" name="orderPlace" value="${order.place}">
            </div>
            <input type="hidden" name="command" value="confirmOrder">
            <input type="submit" class="btn btn-success btn-lg" value="Confirm order"/>
        </form>
    </div>
</div>

</body>
</html>