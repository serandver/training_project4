<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content=""/>
    <meta name="author" content=""/>
    <link rel="icon" href="../resources/favicon.ico"/>
    <title>New order page</title>

    <!-- Bootstrap core CSS -->
    <link href="../resources/css/bootstrap.min.css" rel="stylesheet"/>

    <!-- Custom styles for this template -->
    <link href="../resources/css/dashboard.css" rel="stylesheet"/>
    <link href="../resources/css/custom.css" rel="stylesheet"/>
</head>
<body>
<nav class="navbar navbar-default navbar-fixed-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
        </div>
        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse visible-lg-inline">
            <ul class="nav navbar-nav">
                <li><a href="/reader">Home</a></li>
                <li><a href="/jsp/search.jsp">Search</a></li>
            </ul>

            <form action="/controller" class="navbar-form navbar-left">
                <input type="hidden" name="command" value="show-my-orders">
                <input type="submit" class="btn btn-default" value="My orders">
            </form>
            <form action="/controller" class="navbar-form navbar-right">
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
    </div>
</nav>

<div class="container-fluid main-content">
    <div class="row">
        <h2 class="sub-header">Create new book order</h2>
        <form method="post" action="/controller">
            <div class="form-group">
                <input type="hidden" class="form-control" name="bookId" value="${book.id}">
            </div>
            <div class="form-group">
                <label for="bookTitle">Book title</label>
                <p class="form-control-static" id="bookTitle">${book.title}</p>
                <input type="hidden"  class="form-control" name="bookTitle" value="${book.title}">
            </div>
            <div class="form-group">
                <label for="bookAuthor">Book author</label>
                <p class="form-control-static" id="bookAuthor">${book.author}</p>
                <input type="hidden"  class="form-control" name="bookAuthor" value="${book.author}">
            </div>
            <div class="form-group">
                <label for="orderPlace">Reading place</label>
                <select name="orderPlace" id="orderPlace" title="Choose reading place:">
                    <option value="SUBSCRIPTION">SUBSCRIPTION</option>
                    <option value="READING_ROOM">READING_ROOM</option>
                </select>
            </div>
            <input type="hidden" name="command" value="orderBook">
            <input type="submit" class="btn btn-success btn-lg" value="Create order"/>
        </form>
    </div>
</div>

<script src="../resources/js/jquery.min.js"></script>
<script src="../resources/js/bootstrap.min.js"></script>
</body>
</html>
