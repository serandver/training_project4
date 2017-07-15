<%@include file="/jsp/common/libs.jsp" %>

<html>
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <title><fmt:message key="librarian.book.catalogue"/></title>

    <!-- Bootstrap core CSS -->
    <link href="../resources/css/bootstrap.min.css" rel="stylesheet"/>

    <!-- Custom styles for this template -->
    <link href="../resources/css/dashboard.css" rel="stylesheet"/>

    <link rel="icon" href="../resources/favicon.ico"/>
</head>
<body>

<%@include file="/jsp/common/nav.jsp" %>

<div class="container-fluid">
    <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
            <form action="/controller" class="navbar-form navbar-right">
                <input type="hidden" name="command" value="goToAddBook">
                <input type="submit" class="btn btn-success btn-lg" value="<fmt:message key="librarian.button.addBook"/>">
            </form>
        </div>

        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <h2 class="sub-header"><fmt:message key="librarian.catalogue.title"/></h2>
            <div class="table-responsive">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th><fmt:message key="librarian.catalogue.column.bookId"/></th>
                        <th><fmt:message key="librarian.catalogue.column.bookTitle"/></th>
                        <th><fmt:message key="librarian.catalogue.column.bookAuthor"/></th>
                        <th><fmt:message key="librarian.catalogue.column.bookNumber"/></th>
                        <th></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="book" items="${bookList}">
                        <tr>
                            <td>${book.id}</td>
                            <td>${book.title}</td>
                            <td>${book.author}</td>
                            <td>${book.inventoryNumber}</td>
                            <td>
                                <form action="/controller" method="post" class="navbar-form navbar-right">
                                    <input type="hidden" name="command" value="openBook">
                                    <input type="hidden" name="bookId" value="${book.id}">
                                    <input type="hidden" name="bookTitle" value="${book.title}">
                                    <input type="hidden" name="bookAuthor" value="${book.author}">
                                    <input type="hidden" name="bookNumber" value="${book.inventoryNumber}">
                                    <input type="submit" value="<fmt:message key="librarian.button.editBook"/>" class="btn btn-success btn-lg">
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
<script src="../resources/js/jquery.min.js"></script>
<script src="../resources/js/bootstrap.min.js"></script>
</body>
</html>
