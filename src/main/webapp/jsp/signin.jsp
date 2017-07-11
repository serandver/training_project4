<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="ru_RU" scope="session"/>
<fmt:setBundle basename="pagecontent"/>
<!DOCTYPE HTML>
<html xmlns="http://www.w3.org/1999/xhtml">

  <head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <link rel="icon" href="../resources/favicon.ico"/>

    <title><fmt:message key="signin.title"/></title>

    <!-- Bootstrap core CSS -->
    <link href="../resources/css/bootstrap.min.css" rel="stylesheet"/>

    <!-- Custom styles for this template -->
    <link href="../resources/css/signin.css" rel="stylesheet"/>

  </head>

  <body>

    <div class="container">

      <form method="get" action="/controller" class="form-signin">
        <h2 class="form-signin-heading"><fmt:message key="signin.subtitle"/></h2>
        <input type="hidden" name="command" value="login">
        <input type="email" name="login" class="form-control" placeholder="<fmt:message key="signin.email"/>" required="required" autofocus="autofocus"/>
        <input type="password" name="password" class="form-control" placeholder="<fmt:message key="signin.password"/>" required="required"/>
        <button class="btn btn-lg btn-primary btn-block" type="submit"><fmt:message key="signin.button"/></button>
      </form>

    </div> <!-- /container -->
    <script src="../resources/js/jquery.min.js"></script>
    <script src="../resources/js/bootstrap.min.js"></script>
  </body>
</html>
