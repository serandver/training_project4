<%@include file="/jsp/common/libs.jsp" %>

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

  <%@include file="/jsp/common/nav.jsp" %>

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
