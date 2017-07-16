<%@include file="/jsp/common/libs.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <title><fmt:message key="reader.myorders.title"/></title>

    <!-- Bootstrap core CSS -->
    <link href="../resources/css/bootstrap.min.css" rel="stylesheet"/>

    <link href="../resources/css/custom.css" rel="stylesheet"/>

</head>
<body>

<%@include file="/jsp/common/nav.jsp" %>

    <div class="container-fluid main-content">
        <div class="row">
            <h2 class="sub-header"><fmt:message key="reader.myorders.subtitle"/></h2>
            <div class="table-responsive">
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th><fmt:message key="tables.column.order.id"/></th>
                            <th><fmt:message key="tables.column.book.title"/></th>
                            <th><fmt:message key="tables.column.book.author"/></th>
                            <th><fmt:message key="tables.column.order.receiveDate"/></th>
                            <th><fmt:message key="tables.column.order.returnDate"/></th>
                            <th><fmt:message key="tables.column.order.place"/></th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="order" items="${orderList}">
                            <tr>
                                <td>${order.id}</td>
                                <td>${order.book.title}</td>
                                <td>${order.book.author}</td>
                                <td><fmt:formatDate value="${order.dateOfReceive}"/></td>
                                <td><fmt:formatDate value="${order.dateOfReturn}"/></td>
                                <td>${order.place}</td>
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
