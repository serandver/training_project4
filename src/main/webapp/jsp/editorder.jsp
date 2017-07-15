<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <title><fmt:message key="librarian.editorder.title"/></title>

    <!-- Bootstrap core CSS -->
    <link href="../resources/css/bootstrap.min.css" rel="stylesheet"/>

    <!-- Custom styles for this template -->
    <link href="../resources/css/custom.css" rel="stylesheet"/>

    <link rel="icon" href="../resources/favicon.ico"/>
</head>
<body>

<%@include file="/jsp/common/nav.jsp" %>

<div class="container-fluid main-content">
    <h2 class="sub-header"><fmt:message key="librarian.editorder.subtitle"/></h2>
    <form method="post" action="/controller">
        <div class="form-group">
            <label for="orderId"><fmt:message key="tables.column.order.id"/></label>
            <p class="form-control-static" id="orderId">${order.id}</p>
            <input type="hidden" name="orderId" value="${order.id}">
        </div>
        <div class="form-group">
            <label for="userId"><fmt:message key="tables.column.user.id"/></label>
            <p class="form-control-static" id="userId">${order.user.id}</p>
            <input type="hidden" class="form-control"  name="userId" value="${order.user.id}"/>
        </div>
        <div class="form-group">
            <label for="bookId"><fmt:message key="librarian.catalogue.column.bookId"/></label>
            <p class="form-control-static" id="bookId">${order.book.id}</p>
            <input type="hidden" class="form-control"  name="bookId" value="${order.book.id}"/>
        </div>
        <div class="form-group">
            <label for="dateReceive"><fmt:message key="tables.column.order.receiveDate"/></label>
            <p class="form-control-static" id="dateReceive">${order.dateOfReceive}</p>
            <input type="hidden" class="form-control"  name="dateReceive" value="${order.dateOfReceive}"/>
        </div>
        <c:set var="datereturn" value="${order.dateOfReturn}"/>
        <c:if test="${empty datereturn}">
            <div class="form-group">
                <label for="dateReturn"><fmt:message key="tables.column.order.setDateReturn"/></label>
                <input type="date" class="form-control" name="dateReturn" id="dateReturn">
            </div>
        </c:if>
        <c:set var="status" value="${order.status}"/>
        <c:if test="${status eq 'OPEN' }">
            <select name="orderStatus">
                <p><fmt:message key="tables.column.order.setstatus"/></p>
                <option value="closed">CLOSED</option>
                <option value="open">OPEN</option>
            </select>
        </c:if>
        <br/>

        <input type="hidden" name="readPlace" value="${order.place}">
        <input type="hidden" name="orderStatus" value="${order.status}">
        <input type="hidden" name="command" value="editOrder">
        <input type="submit" class="btn btn-success btn-lg" value="<fmt:message key="form.save"/>"/>
    </form>
</div>
<script src="../resources/js/jquery.min.js"></script>
<script src="../resources/js/bootstrap.min.js"></script>
</body>
</html>
