<%@include file="/jsp/common/libs.jsp" %>

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

<%@include file="/jsp/common/nav.jsp" %>

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
        <input type="submit" class="btn btn-success btn-lg" value="<fmt:message key="form.save"/>"/>
    </form>
</div>
<script src="../resources/js/jquery.min.js"></script>
<script src="../resources/js/bootstrap.min.js"></script>
</body>
</html>
