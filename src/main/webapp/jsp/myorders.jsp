<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="ru_RU" scope="session"/>
<fmt:setBundle basename="pagecontent"/>
<html>
<head>
    <title><fmt:message key="reader.myorders.title"/></title>

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
        </div><!-- /.navbar-collapse -->
    </div>
</nav>

    <div class="container-fluid main-content">
        <div class="row">
            <h2 class="sub-header"><fmt:message key="reader.myorders.subtitle"/></h2>
            <div class="table-responsive">
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th><fmt:message key="tables.column.order.id"/></th>
                            <th><fmt:message key="tables.column.book.title"/></th>
                            <th><fmt:message key="tables.column.book.author"/></th>
                            <th><fmt:message key="tables.column.order.receiveDate"/></th>
                            <th><fmt:message key="tables.column.order.returnDate"/></th>
                            <th><fmt:message key="tables.column.order.place"/></th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="order" items="${orderList}">
                            <tr>
                                <td>${order.id}</td>
                                <td>${order.book.title}</td>
                                <td>${order.book.author}</td>
                                <td>${order.dateOfReceive}</td>
                                <td>${order.dateOfReturn}</td>
                                <td>${order.place}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
<script src="../resources/js/jquery.min.js"></script>
<script src="../resources/js/bootstrap.min.js"></script>
</body>
</html>
