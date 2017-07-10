<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="q" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Search a book in the catalogue</title>
    <!-- Bootstrap core CSS -->
    <link href="../resources/css/bootstrap.min.css" rel="stylesheet"/>
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
        <form name="SearchForm" method="POST" action="/controller">
            <h2><c:out value="Find book by title:"/></h2>
            <input type="text" class="form-control" name="title" value=""/>
            <input type="hidden" name="command" value="findByTitle">
            <br/>
            <input type="submit" class="btn btn-success btn-lg" value="Search">
        </form>

        <form name="SearchForm" method="POST" action="/controller">
            <h2><c:out value="Find book by author:"/></h2>
            <input type="text" class="form-control" name="author" value=""/>
            <input type="hidden" name="command" value="findByAuthor">
            <br/>
            <input type="submit" class="btn btn-success btn-lg" value="Search">
        </form>
    </div>
</div>
<script src="../resources/js/jquery.min.js"></script>
<script src="../resources/js/bootstrap.min.js"></script>
</body>
</html>
