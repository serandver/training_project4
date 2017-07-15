<%@include file="/jsp/common/libs.jsp" %>

<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <title><fmt:message key="librarian.users.title"/></title>

    <!-- Bootstrap core CSS -->
    <link href="../resources/css/bootstrap.min.css" rel="stylesheet"/>

    <!-- Custom styles for this template -->
    <link href="../resources/css/custom.css" rel="stylesheet"/>

    <link rel="icon" href="../resources/favicon.ico"/>
</head>
<body>

<%@include file="/jsp/common/nav.jsp" %>

    <div class="container-fluid main-content ">
        <div class="row">
            <h2 class="sub-header"><fmt:message key="librarian.users.subtitle"/></h2>
            <div class="table-responsive">
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th><fmt:message key="tables.column.user.id"/></th>
                            <th><fmt:message key="tables.column.user.firstName"/></th>
                            <th><fmt:message key="tables.column.user.lastName"/></th>
                            <th><fmt:message key="tables.column.user.email"/></th>
                            <th><fmt:message key="tables.column.user.role"/></th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="user" items="${userList}">
                            <tr>
                                <td>${user.id}</td>
                                <td>${user.firstName}</td>
                                <td>${user.lastName}</td>
                                <td>${user.email}</td>
                                <td>${user.role}</td>
                                <td>
                                    <form method="post" action="/controller">
                                        <input type="hidden" name="command" value="openuser">
                                        <input type="hidden" name="userId" value="${user.id}">
                                        <button type="submit" class="btn btn-success btn-lg"><fmt:message key="form.edit"/></button>
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
