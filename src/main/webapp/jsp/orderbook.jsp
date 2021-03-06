<%@include file="/jsp/common/libs.jsp" %>

<!DOCTYPE html>
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

<%@include file="/jsp/common/nav.jsp" %>

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
