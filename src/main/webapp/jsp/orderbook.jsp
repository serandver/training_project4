<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="ru_RU" scope="session"/>
<fmt:setBundle basename="pagecontent"/>
<html>
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>

    <link rel="icon" href="../resources/favicon.ico"/>
    <title><fmt:message key="reader.neworders.title"/></title>

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
                <li><a href="/reader"><fmt:message key="librarian.menu.home"/></a></li>
                <li><a href="/jsp/search.jsp"><fmt:message key="reader.menu.search"/></a></li>
            </ul>

            <form action="/controller" class="navbar-form navbar-left">
                <input type="hidden" name="command" value="myorders">
                <input type="submit" class="btn btn-default" value="<fmt:message key="reader.menu.myorders"/>">
            </form>
            <form action="/controller" class="navbar-form navbar-right">
                <input type="hidden" name="command" value="signout">
                <input type="submit" class="btn btn-default" value="<fmt:message key="index.sign.out"/>">
            </form>
            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><fmt:message key="header.language"/><span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li>
                            <form method="post" action="/controller">
                                <input type="hidden" name="command" value="local">
                                <input type="hidden" name="url" value="${pagecontext.request.requestURL}">
                                <input type="submit" class="btn btn-default" name="locale" value="en"/>
                            </form>
                        </li>
                        <li>
                            <form method="post" action="/controller">
                                <input type="hidden" name="command" value="local">
                                <input type="hidden" name="url" value="${pagecontext.request.requestURL}">
                                <input type="submit" class="btn btn-default" name="locale" value="ru"/>
                            </form>
                        </li>
                    </ul>
                </li>
            </ul>
        </div><!-- /.navbar-collapse -->
    </div>
</nav>

<div class="container-fluid main-content">
    <div class="row">
        <h2 class="sub-header"><fmt:message key="reader.neworder.subtitle"/></h2>
        <form method="post" action="/controller">
            <div class="form-group">
                <input type="hidden" class="form-control" name="bookId" value="${book.id}">
            </div>
            <div class="form-group">
                <label for="bookTitle"><fmt:message key="tables.column.book.title"/>Book title</label>
                <p class="form-control-static" id="bookTitle">${book.title}</p>
                <input type="hidden"  class="form-control" name="bookTitle" value="${book.title}">
            </div>
            <div class="form-group">
                <label for="bookAuthor"><fmt:message key="tables.column.book.author"/>Book author</label>
                <p class="form-control-static" id="bookAuthor">${book.author}</p>
                <input type="hidden"  class="form-control" name="bookAuthor" value="${book.author}">
            </div>
            <div class="form-group">
                <label for="orderPlace"><fmt:message key="tables.column.order.place"/>Reading place</label>
                <select name="orderPlace" id="orderPlace" title="<fmt:message key="reader.neworder.chooseplace"/>">
                    <option value="SUBSCRIPTION">SUBSCRIPTION</option>
                    <option value="READING_ROOM">READING_ROOM</option>
                </select>
            </div>
            <input type="hidden" name="command" value="orderBook">
            <input type="submit" class="btn btn-success btn-lg" value="<fmt:message key="form.createOrder"/>"/>
        </form>
    </div>
</div>

<script src="../resources/js/jquery.min.js"></script>
<script src="../resources/js/bootstrap.min.js"></script>
</body>
</html>
