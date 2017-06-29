<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content=""/>
    <meta name="author" content=""/>
    <link rel="icon" href="resources/favicon.ico"/>

    <title>Library</title>

    <!-- Bootstrap core CSS -->
    <link href="resources/css/bootstrap.css" rel="stylesheet"/>

    <!-- Custom styles for this template -->
    <link href="resources/css/start-page.css" rel="stylesheet"/>
</head>

<body>

<div class="container">
    <div class="header clearfix">
        <nav>
            <ul class="nav nav-pills pull-right">
                <li role="presentation" class="active"><a href="jsp/signin.jsp">Sign in</a></li>
                <li role="presentation"><a href="jsp/signup.jsp">Sign up</a></li>
            </ul>
        </nav>
        <h3 class="text-muted">Project 4 - Library System</h3>
    </div>

    <div class="jumbotron">
        <h1>Library System</h1>
        <p class="lead">Here you can read e-books for free!</p>
        <p><a class="btn btn-lg btn-success" href="jsp/signin.jsp" role="button">Sign in</a></p>
    </div>

    <footer class="footer">
        <p>&copy; 2017 Sergey Khomenko</p>
    </footer>

</div>
</body>
</html>
