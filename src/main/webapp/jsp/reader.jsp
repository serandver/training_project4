<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="ru_RU" scope="session"/>
<fmt:setBundle basename="pagecontent"/>
<!DOCTYPE HTML>
<html>

  <head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>

    <link rel="icon" href="../resources/favicon.ico"/>

    <title><fmt:message key="reader.home.title"/></title>

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
            <h2 class="sub-header"><fmt:message key="reader.home.catalogue"/></h2>
            <div class="table-responsive">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th><fmt:message key="tables.column.book.title"/></th>
                        <th><fmt:message key="tables.column.book.author"/></th>
                        <th></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="book" items="${bookList}">
                        <tr>
                            <td>${book.title}</td>
                            <td>${book.author}</td>
                            <td>
                                <form action="/controller" method="post" class="navbar-form navbar-right">
                                    <input type="hidden" name="command" value="newOrder">
                                    <input type="hidden" name="bookId" value="${book.id}">
                                    <input type="hidden" name="bookTitle" value="${book.title}">
                                    <input type="hidden" name="bookAuthor" value="${book.author}">
                                    <input type="submit" value="<fmt:message key="reader.button.orderBook"/>" class="btn btn-success btn-lg" />
                                </form>
                            </td>
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
