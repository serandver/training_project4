<%@include file="/jsp/common/libs.jsp" %>

<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <title><fmt:message key="librarian.orders.title"/></title>
    <!-- Bootstrap core CSS -->
    <link href="../resources/css/bootstrap.min.css" rel="stylesheet"/>

    <!-- Custom styles for this template -->
    <link href="../resources/css/custom.css" rel="stylesheet"/>

    <link rel="icon" href="../resources/favicon.ico"/>
</head>
<body>

<%@include file="/jsp/common/nav.jsp" %>

<div class="container-fluid padd">
    <div class="row">
        <h2 class="sub-header"><fmt:message key="librarian.orders.subtitle"/></h2>
        <div class="table-responsive">
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th><fmt:message key="tables.column.order.id"/></th>
                        <th><fmt:message key="tables.column.user.id"/></th>
                        <th><fmt:message key="tables.column.user.firstName"/></th>
                        <th><fmt:message key="tables.column.user.lastName"/></th>
                        <th><fmt:message key="tables.column.book.id"/></th>
                        <th><fmt:message key="tables.column.book.title"/></th>
                        <th><fmt:message key="tables.column.book.author"/></th>
                        <th><fmt:message key="tables.column.book.number"/></th>
                        <th><fmt:message key="tables.column.order.receiveDate"/></th>
                        <th><fmt:message key="tables.column.order.returnDate"/></th>
                        <th><fmt:message key="tables.column.order.place"/></th>
                        <th><fmt:message key="tables.column.order.orderStatus"/></th>
                        <th></th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                <c:forEach var="order" items="${orderList}">
                    <tr>
                        <td>${order.id}</td>
                        <td>${order.user.id}</td>
                        <td>${order.user.firstName}</td>
                        <td>${order.user.lastName}</td>
                        <td>${order.book.id}</td>
                        <td>${order.book.title}</td>
                        <td>${order.book.author}</td>
                        <td>${order.book.inventoryNumber}</td>
                        <td><fmt:formatDate value="${order.dateOfReceive}"/></td>
                        <td><fmt:formatDate value="${order.dateOfReturn}"/></td>
                        <td>${order.place}</td>
                        <td>${order.orderStatus}</td>
                        <td>
                            <c:set var="datereturn" value="${order.dateOfReturn}"/>
                            <c:set var="orderStatus" value="${order.orderStatus}"/>
                            <c:if test="${orderStatus eq 'OPEN' || empty datereturn}">
                                <form action="/controller" class="navbar-form navbar-right">
                                    <input type="hidden" name="orderId" value="${order.id}">
                                    <input type="hidden" name="userId" value="${order.user.id}">
                                    <input type="hidden" name="bookId" value="${order.book.id}">
                                    <input type="hidden" name="dateReceive" value="${order.dateOfReceive}">
                                    <input type="hidden" name="dateReturn" value="${order.dateOfReturn}">
                                    <input type="hidden" name="orderPlace" value="${order.place}">
                                    <input type="hidden" name="orderStatus" value="${order.orderStatus}">
                                    <input type="hidden" name="command" value="openOrder">
                                    <button type="submit" class="btn btn-success btn-lg">
                                        <fmt:message key="form.edit"/>
                                    </button>
                                </form>
                            </c:if>
                        </td>
                        <td>
                            <c:set var="datereturn" value="${order.dateOfReturn}"/>
                            <c:set var="datereceive" value="${order.dateOfReceive}"/>
                            <c:if test="${empty datereceive && empty datereturn}">
                                <form action="/controller" class="navbar-form navbar-right">
                                    <input type="hidden" name="orderId" value="${order.id}">
                                    <input type="hidden" name="command" value="deleteOrder">
                                    <button type="submit" class="btn btn-success btn-lg">
                                        <fmt:message key="form.delete"/>
                                    </button>
                                </form>
                            </c:if>
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
