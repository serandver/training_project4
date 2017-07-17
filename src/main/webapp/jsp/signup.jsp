<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<fmt:setLocale value="${empty sessionScope.locale ? 'en_US' : sessionScope.locale}"/>
<fmt:setBundle basename="pagecontent"/>

<!DOCTYPE HTML>
<html>
  <head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content=""/>
    <meta name="author" content=""/>
    <link rel="icon" href="../resources/favicon.ico"/>

    <title><fmt:message key="signup.title"/></title>

    <!-- Bootstrap core CSS -->
    <link href="../resources/css/bootstrap.min.css" rel="stylesheet"/>

    <!-- Custom styles for this template -->
    <link href="../resources/css/signin.css"  rel="stylesheet"/>
  </head>

  <body>

    <div class="container">

      <form method="post" action="/controller" class="form-signin">
        <%@include file = "/jsp/common/listerrors.jsp"%>
        <h2 class="form-signin-heading"><fmt:message key="signup.subtitle"/></h2>
          <input type="hidden" name="command" value="register">
          <div class="form-group">
          <input type="text" class="form-control" name="firstName" placeholder="<fmt:message key="signup.firstName"/>" required="required" autofocus="autofocus"/>
        </div>
        <div class="form-group">
          <input type="text" class="form-control" name="lastName" placeholder="<fmt:message key="signup.lastName"/>" required="required"/>
        </div>
        <div class="form-group">
          <input type="email" class="form-control" name="email" placeholder="<fmt:message key="signup.email"/>" required="required"/>
        </div>
        <div class="form-group">
          <input type="password" class="form-control" name="password" placeholder="<fmt:message key="signup.password"/>" required="required"/>
        </div>
        <div class="form-group">
          <input type="password" class="form-control" name="confirmPassword" placeholder="<fmt:message key="signup.confirmpass"/>" required="required"/>
        </div>
          <button class="btn btn-lg btn-primary btn-block" type="submit"><fmt:message key="signup.button"/></button>
      </form>

    </div> <!-- /container -->
    <script src="../resources/js/jquery.min.js"></script>
    <script src="../resources/js/bootstrap.min.js"></script>
  </body>
</html>
