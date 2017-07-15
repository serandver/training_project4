<%@include file="/jsp/common/libs.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><fmt:message key="reader.search.title"/></title>
    <!-- Bootstrap core CSS -->
    <link href="../resources/css/bootstrap.min.css" rel="stylesheet"/>
    <!-- Custom styles for this template -->
    <link href="../resources/css/custom.css" rel="stylesheet"/>
</head>
<body>

<%@include file="/jsp/common/nav.jsp" %>

<div class="container-fluid main-content">
    <div class="row">
        <form name="SearchForm" method="POST" action="/controller">
            <h2 class="sub-header"><fmt:message key="reader.search.bytitle"/></h2>
            <input type="text" class="form-control" name="title" value=""/>
            <input type="hidden" name="command" value="findByTitle">
            <br/>
            <input type="submit" class="btn btn-success btn-lg" value="<fmt:message key="form.search"/>">
        </form>

        <form name="SearchForm" method="POST" action="/controller">
            <h2 class="sub-header"><fmt:message key="reader.search.byauthor"/></h2>
            <input type="text" class="form-control" name="author" value=""/>
            <input type="hidden" name="command" value="findByAuthor">
            <br/>
            <input type="submit" class="btn btn-success btn-lg" value="<fmt:message key="form.search"/>">
        </form>
    </div>
</div>
<script src="../resources/js/jquery.min.js"></script>
<script src="../resources/js/bootstrap.min.js"></script>
</body>
</html>
