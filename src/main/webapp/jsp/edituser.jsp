<%@include file="/jsp/common/libs.jsp" %>

<html>
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <title><fmt:message key="librarian.edituser.title"/></title>

    <!-- Bootstrap core CSS -->
    <link href="../resources/css/bootstrap.min.css" rel="stylesheet"/>

    <!-- Custom styles for this template -->
    <link href="../resources/css/custom.css" rel="stylesheet"/>

    <link rel="icon" href="../resources/favicon.ico"/>
</head>
<body>

<%@include file="/jsp/common/nav.jsp" %>

<div class="container-fluid main-content">
    <h2 class="sub-header"><fmt:message key="librarian.edituser.subtitle"/></h2>
    <form method="post" action="/controller">
        <div class="form-group">
            <label for="userId"><fmt:message key="tables.column.user.id"/></label>
            <p class="form-control-static" id="userId">${user.id}</p>
            <input type="hidden" name="userId" value="${user.id}">
        </div>
        <div class="form-group">
            <label for="firstName"><fmt:message key="tables.column.user.firstName"/></label>
            <p class="form-control-static" id="firstName">${user.firstName}</p>
            <input type="hidden" class="form-control"  name="firstName" value="${user.firstName}"/>
        </div>
        <div class="form-group">
            <label for="lastName"><fmt:message key="tables.column.user.lastName"/></label>
            <p class="form-control-static" id="lastName">${user.lastName}</p>
            <input type="hidden" class="form-control"  name="lastName" value="${user.lastName}"/>
        </div>
        <div class="form-group">
            <label for="role"><fmt:message key="tables.column.user.role"/></label>
            <select name="role" id="role">
                <p><fmt:message key="form.edituser.setrole"/></p>
                <option value="READER">READER</option>
                <option value="LIBRARIAN">LIBRARIAN</option>
            </select>
        </div>
        <br/>
        <input type="hidden" name="command" value="edituser">
        <input type="submit" class="btn btn-success btn-lg" value="<fmt:message key="form.save"/>"/>
    </form>
</div>
<script src="../resources/js/jquery.min.js"></script>
<script src="../resources/js/bootstrap.min.js"></script>
</body>
</html>
