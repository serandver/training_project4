<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="ru_RU" scope="session"/>
<fmt:setBundle basename="pagecontent"/>
<html>
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <title><fmt:message key="librarian.editbook.title"/></title>

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
                <li><a href="/librarian"><fmt:message key="librarian.menu.home"/></a></li>
                <li><a href="/books"><fmt:message key="librarian.menu.books"/></a></li>
                <li><a href="/users"><fmt:message key="librarian.menu.users"/></a></li>
                <li><a href="/orders"><fmt:message key="librarian.menu.orders"/></a></li>
            </ul>
            <form action="/controller" class="navbar-form navbar-right">
                <input type="hidden" name="command" value="signout">
                <input type="submit" class="btn btn-default"  value="<fmt:message key="index.sign.out"/>">
            </form>
            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
                        <fmt:message key="header.language"/>
                        <span class="caret"></span>
                    </a>
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
    <form method="post" action="/controller">
        <h2 class="sub-header"><fmt:message key="librarian.editbook.subtitle"/></h2>
        <div class="form-group">
            <label for="bookId"><fmt:message key="tables.column.book.id"/></label>
            <p class="form-control-static" id="bookId">${book.id}</p>
            <input type="hidden" name="bookId" value="${book.id}">
        </div>
        <div class="form-group">
            <label for="editBookTitle"><fmt:message key="tables.column.book.title"/></label>
            <input type="text" class="form-control"  name="bookTitle" id="editBookTitle" value="${book.title}"/>
        </div>
        <div class="form-group">
            <label for="editBookAuthor"><fmt:message key="tables.column.book.author"/></label>
            <input type="text" class="form-control"  name="bookAuthor" id="editBookAuthor" value="${book.author}"/>
        </div>
        <div class="form-group">
            <label for="editInventoryNumber"><fmt:message key="tables.column.book.number"/></label>
            <input type="text" class="form-control"  name="bookNumber" id="editInventoryNumber" value="${book.inventoryNumber}"/>
        </div>
        <input type="hidden" name="command" value="editBook">
        <input type="submit" class="btn btn-success btn-lg" value="<fmt:message key="form.editbook.save"/>"/>
    </form>
</div>
<script src="../resources/js/jquery.min.js"></script>
<script src="../resources/js/bootstrap.min.js"></script>
</body>
</html>
