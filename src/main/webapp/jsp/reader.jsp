<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html xmlns="http://www.w3.org/1999/xhtml">

  <head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content=""/>
    <meta name="author" content=""/>
    <link rel="icon" href="../resources/favicon.ico"/>

    <title>Reader home page</title>

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
            <h2 class="sub-header">Book catalogue</h2>
            <div class="table-responsive">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th>Book title</th>
                        <th>Book author</th>
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
                                    <input type="submit" value="Order book" class="btn btn-success btn-lg" />
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
    <script src="../resources/js/reader.js"></script>

  </body>
</html>
