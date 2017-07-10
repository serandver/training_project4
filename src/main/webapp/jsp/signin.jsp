<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html xmlns="http://www.w3.org/1999/xhtml">

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
    <link href="../resources/css/bootstrap.min.css" rel="stylesheet"/>

    <!-- Custom styles for this template -->
    <link href="../resources/css/signin.css" rel="stylesheet"/>

  </head>

  <body>

    <div class="container">

      <form method="get" action="/controller" class="form-signin">
        <h2 class="form-signin-heading">Please sign in</h2>
        <input type="hidden" name="command" value="login">
        <input type="email" name="login" class="form-control" placeholder="Email address" required="required" autofocus="autofocus"/>
        <input type="password" name="password" class="form-control" placeholder="Password" required="required"/>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
      </form>

    </div> <!-- /container -->
    <script src="../resources/js/jquery.min.js"></script>
    <script src="../resources/js/bootstrap.min.js"></script>
  </body>
</html>
