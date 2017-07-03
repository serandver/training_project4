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
</head>
<body>
<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
        </div>
        <div class="navbar-collapse collapse list-inline">
            <p>
                <a class="btn btn-lg btn-success navbar-left" href="jsp/reader.jsp" role="button">Home page</a>
            </p>
            <form class="navbar-form navbar-right">
                <input type="hidden" name="command" value="signout">
                <input type="submit" value="Sign out">
            </form>
        </div>
    </div>
</nav>

<div class="container-fluid">
    <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
        </div>

        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

            <form name="SearchForm" method="POST" action="controller">
                <h2><c:out value="Find book by title:"/></h2>
                <p><span><c:out value="Book title"/></span><input type="text" name="title" value=""><span></span></p>
                <input type="hidden" name="command" value="findByTitle">
                <input type="submit" value="Search" class="subo">
            </form>

            <form name="SearchForm" method="POST" action="controller">
                <h2><c:out value="Find book by title:"/></h2>
                <p><span><c:out value="Book author"/></span><input type="text" name="author" value=""><span></span></p>
                <input type="hidden" name="command" value="findByAuthor">
                <input type="submit" value="Search" class="subo">
            </form>

        </div>
    </div>
</div>



    <div class="container-fluid">
        <div class="row">
            <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">


            </div>
        </div>
    </div>
</body>
</html>
