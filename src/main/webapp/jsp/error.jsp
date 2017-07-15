<%@include file="/jsp/common/libs.jsp" %>

<!DOCTYPE html>

<html>
<link rel="stylesheet" type="text/css" href ="jsp/css/catalog.css">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Error Page</title>
</head>
<body>
<fieldset>
    <h2><c:out value="${parametersMap.get('ErrorLogMessage')}"/></h2>
    <form name="logoutForm" method="POST" action="controller">
        <input type="hidden" name="command" value="logout">
        <input type="submit" value="${parametersMap.get('MainPage')}" class="subo">
    </form>
</fieldset>
</body>
</html>
