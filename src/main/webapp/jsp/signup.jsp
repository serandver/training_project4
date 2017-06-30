<!DOCTYPE HTML>
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:th="http://www.thymeleaf.org"
      xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity3">

  <head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content=""/>
    <meta name="author" content=""/>
    <link rel="icon" href="../resources/favicon.ico"/>

    <title>Sign in page</title>

    <!-- Bootstrap core CSS -->
    <link href="../resources/css/bootstrap.min.css" th:href="@{/css/bootstrap.css}" rel="stylesheet"/>

    <!-- Custom styles for this template -->
    <link href="../resources/css/signin.css" th:href="@{/css/signin.css}" rel="stylesheet"/>
  </head>

  <body>

    <div class="container">

      <form method="post" action="/controller" class="form-signin">
        <h2 class="form-signin-heading">Please sign up</h2>
          <input type="hidden" name="command" value="register">
          <div class="form-group">
          <input type="text" class="form-control" name="firstName" placeholder="First name" required="required" autofocus="autofocus"/>
        </div>
        <div class="form-group">
          <input type="text" class="form-control" name="lastName" placeholder="Last name" required="required"/>
        </div>
        <div class="form-group">
          <input type="email" class="form-control" name="email" placeholder="Email" required="required"/>
        </div>
        <div class="form-group">
          <input type="password" class="form-control" name="password" placeholder="Your password" required="required"/>
        </div>
        <div class="form-group">
          <input type="password" class="form-control" name="confirmPassword" placeholder="Confirm password" required="required"/>
        </div>
          <button class="btn btn-lg btn-primary btn-block" type="submit">Sign up</button>
      </form>

    </div> <!-- /container -->

  </body>
</html>
