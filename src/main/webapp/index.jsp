<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="${empty sessionScope.locale ? 'en_US' : sessionScope.locale}"/>
<fmt:setBundle basename="pagecontent"/>
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

    <title><fmt:message key="index.title"/></title>

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
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><fmt:message key="header.language"/><span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li>
                            <form method="post" action="/controller">
                                <input type="hidden" name="command" value="local">
                                <input type="hidden" name="url" value="${pagecontext.request.requestURL}">
                                <input type="hidden" name="query" value="${pagecontext.request.queryString}">
                                <input type="submit" class="btn btn-default" name="locale" value="en"/>
                            </form>
                        </li>
                        <li>
                            <form method="post" action="/controller">
                                <input type="hidden" name="command" value="local">
                                <input type="hidden" name="url" value="${pagecontext.request.requestURL}">
                                <input type="hidden" name="query" value="${pagecontext.request.queryString}">
                                <input type="submit" class="btn btn-default" name="locale" value="ru"/>
                            </form>
                        </li>
                    </ul>
                </li>
                <li role="presentation" class="active"><a href="jsp/signin.jsp"><fmt:message key="index.sign.in"/></a></li>
                <li role="presentation"><a href="jsp/signup.jsp"><fmt:message key="index.signup"/></a></li>
            </ul>
        </nav>
        <h3 class="text-muted"><fmt:message key="index.description"/></h3>
    </div>

    <div class="jumbotron">
        <h1><fmt:message key="index.title.h1"/></h1>
        <p class="lead"><fmt:message key="index.slogan"/></p>
        <p><a class="btn btn-lg btn-success" href="jsp/signin.jsp" role="button"><fmt:message key="index.sign.in"/></a></p>
    </div>

    <footer class="footer">
        <p><fmt:message key="index.copyright"/></p>
    </footer>

</div>
<script src="resources/js/jquery.min.js"></script>
<script src="resources/js/bootstrap.min.js"></script>
</body>
</html>
