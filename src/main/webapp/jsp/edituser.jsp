<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="ru_RU" scope="session"/>
<fmt:setBundle basename="pagecontent"/>
<html>
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <title><fmt:message key="librarian.edituser.title"/></title>

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
    <h2 class="sub-header"><fmt:message key="librarian.edituser.subtitle"/></h2>
    <form method="post" action="/controller">
        <div class="form-group">
            <label for="userId"><fmt:message key="tables.column.user.id"/></label>
            <p class="form-control-static" id="userId">${user.id}</p>
            <input type="hidden" name="userId" value="${user.id}">
        </div>
        <div class="form-group">
            <label for="firstName"><fmt:message key="tables.column.user.firstName"/></label>
            <p class="form-control-static" id="firstName">${user.firstName}</p>
            <input type="hidden" class="form-control"  name="firstName" value="${user.firstName}"/>
        </div>
        <div class="form-group">
            <label for="lastName"><fmt:message key="tables.column.user.lastName"/></label>
            <p class="form-control-static" id="lastName">${user.lastName}</p>
            <input type="hidden" class="form-control"  name="lastName" value="${user.lastName}"/>
        </div>
        <div class="form-group">
            <label for="role"><fmt:message key="tables.column.user.role"/></label>
            <select name="role" id="role">
                <p><fmt:message key="form.edituser.setrole"/></p>
                <option value="READER">READER</option>
                <option value="LIBRARIAN">LIBRARIAN</option>
            </select>
        </div>
        <br/>
        <input type="hidden" name="command" value="edituser">
        <input type="submit" class="btn btn-success btn-lg" value="<fmt:message key="form.save"/>"/>
    </form>
</div>
<script src="../resources/js/jquery.min.js"></script>
<script src="../resources/js/bootstrap.min.js"></script>
</body>
</html>
