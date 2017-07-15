<%@include file="/jsp/common/libs.jsp" %>

<html>
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content=""/>
    <meta name="author" content=""/>
    <link rel="icon" href="../resources/favicon.ico"/>

    <title><fmt:message key="librarian.confirmorder.title"/></title>

    <!-- Bootstrap core CSS -->
    <link href="../resources/css/bootstrap.min.css" rel="stylesheet"/>

    <!-- Custom styles for this template -->
    <link href="../resources/css/custom.css" rel="stylesheet"/>
</head>
<body>

<%@include file="/jsp/common/nav.jsp" %>

<div class="container-fluid main-content">
    <div class="row">
        <h2 class="sub-header"><fmt:message key="librarian.confirmorder.subtitle"/></h2>
        <form method="post" action="/controller">
            <div class="form-group">
                <label for="orderId"><fmt:message key="tables.column.order.id"/></label>
                <p class="form-control-static" id="orderId">${order.id}</p>
                <input type="hidden" class="form-control" name="orderId" value="${order.id}">
            </div>
            <div class="form-group">
                <label for="bookId"><fmt:message key="tables.column.book.id"/></label>
                <p class="form-control-static" id="bookId">${order.book.id}</p>
                <input type="hidden" class="form-control" name="bookId" value="${order.book.id}">
            </div>
            <div class="form-group">
                <label for="bookTitle"><fmt:message key="tables.column.book.title"/></label>
                <p class="form-control-static" id="bookTitle">${order.book.title}</p>
                <input type="hidden"  class="form-control" name="bookTitle" value="${order.book.title}">
            </div>
            <div class="form-group">
                <label for="bookAuthor"><fmt:message key="tables.column.book.author"/></label>
                <p class="form-control-static" id="bookAuthor">${order.book.author}</p>
                <input type="hidden"  class="form-control" name="bookAuthor" value="${order.book.author}">
            </div>
            <div class="form-group">
                <label for="orderPlace"><fmt:message key="tables.column.order.place"/></label>
                <p class="form-control-static" id="orderPlace">${order.place}</p>
                <input type="hidden"  class="form-control" name="orderPlace" value="${order.place}">
            </div>
            <input type="hidden" name="command" value="confirmOrder">
            <input type="submit" class="btn btn-success btn-lg" value="<fmt:message key="form.confirm"/>"/>
        </form>
    </div>
</div>
<script src="../resources/js/jquery.min.js"></script>
<script src="../resources/js/bootstrap.min.js"></script>
</body>
</html>
