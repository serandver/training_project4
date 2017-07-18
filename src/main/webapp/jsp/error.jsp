<%@ page isErrorPage="true" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="${empty sessionScope.locale ? 'en_US' : sessionScope.locale}"/>
<fmt:setBundle basename="pagecontent"/>

<!DOCTYPE html>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Error Page</title>
    <link href="../resources/css/bootstrap.min.css" rel="stylesheet"/>
    <link rel="stylesheet" type="text/css" href ="jsp/css/catalog.css">
</head>
<body>
    <div class="container-fluid " align="center">
        <div class="row-fluid ">
            <div class=" error alert alert-danger ">
                <strong> ${pageContext.errorData.statusCode}</strong><br />
                <strong>
                    <fmt:message key="library.error.serverError"/>
                </strong>
                <h3>Error Details</h3>
                <strong>Status Code</strong>: ${pageContext.errorData.statusCode}
                <br>
                <strong>Requested URI</strong>: ${pageContext.errorData.requestURI}
                <br>
                <strong>Exception Message: ${pageContext.errorData.throwable.message}</strong>

                <ul>
                    <li><a href="index.jsp">Home Page</a></li>
                </ul>
            </div>
        </div>
    </div>
</body>
</html>
