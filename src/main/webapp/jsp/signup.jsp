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

    <title>Signin Template for Bootstrap</title>

    <!-- Bootstrap core CSS -->
    <link href="../resources/css/bootstrap.min.css" th:href="@{/css/bootstrap.css}" rel="stylesheet"/>

    <!-- Custom styles for this template -->
    <link href="../resources/css/signin.css" th:href="@{/css/signin.css}" rel="stylesheet"/>
  </head>

  <body>

    <div class="container">

      <form class="form-signin">
        <h2 class="form-signin-heading">Please sign up</h2>
        <div class="form-group">
          <label for="userName" class="sr-only">Login</label>
          <input type="text" class="form-control" userId="userName" placeholder="Login" required="required" autofocus="autofocus"/>
        </div>
        <div class="form-group">
          <label for="name" class="sr-only">Full name</label>
          <input type="text" class="form-control" userId="name" placeholder="Full name" required="required"/>
        </div>
        <div class="form-group">
          <label for="inputPassword" class="sr-only">Your password</label>
          <input type="password" userId="inputPassword" class="form-control" placeholder="Your password" required="required"/>
        </div>
        <div class="form-group">
          <label for="confirmPassword" class="sr-only">Confirm password</label>
          <input type="passwouserId" id="inputPassword" class="form-control" placeholder="Confirm password" required>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Sign up</button>
        </div>
      </form>

    </div> <!-- /container -->

  </body>
</html>
