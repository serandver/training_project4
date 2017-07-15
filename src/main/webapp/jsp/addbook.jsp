<%@include file="/jsp/common/libs.jsp" %>

<html>
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <title><fmt:message key="librarian.addbook.title"/></title>

    <!-- Bootstrap core CSS -->
    <link href="../resources/css/bootstrap.min.css" rel="stylesheet"/>

    <!-- Custom styles for this template -->
    <link href="../resources/css/custom.css" rel="stylesheet"/>

    <link rel="icon" href="../resources/favicon.ico"/>
</head>
<body>

<%@include file="/jsp/common/nav.jsp" %>

<div class="container-fluid main-content main-content">
    <h2 class="sub-header"><fmt:message key="librarian.addbook.subtitle"/></h2>
    <form method="post" action="/controller">
        <div class="form-group">
            <label for="editBookTitle"><fmt:message key="tables.column.book.title"/></label>
            <input type="text" class="form-control onfocus-title"  name="bookTitle" id="editBookTitle"/>
        </div>
        <div class="form-group">
            <label for="editBookAuthor"><fmt:message key="tables.column.book.author"/></label>
            <input type="text" class="form-control onfocus-author"  name="bookAuthor" id="editBookAuthor"/>
        </div>
        <div class="form-group">
            <label for="editInventoryNumber"><fmt:message key="tables.column.book.number"/></label>
            <input type="text" class="form-control onfocus-number"  name="bookNumber" id="editInventoryNumber"/>
        </div>
        <input type="hidden" name="command" value="addBook">
        <input type="submit" class="btn btn-success btn-lg" value="<fmt:message key="form.addbook.submit"/>"/>
    </form>
</div>
<script src="../resources/js/jquery.min.js"></script>
<script src="../resources/js/bootstrap.min.js"></script>
</body>
</html>
