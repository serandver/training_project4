<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

    <title>Reader home page</title>

    <!-- Bootstrap core CSS -->
    <link href="../resources/css/bootstrap.min.css" rel="stylesheet"/>

    <!-- Custom styles for this template -->
    <link href="../resources/css/dashboard.css" rel="stylesheet"/>
</head>

<body>
<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
        </div>
        <div class="navbar-collapse collapse list-inline">
            <form class="navbar-form navbar-right">
                <input type="hidden" name="command" value="signout">
                <input type="submit" value="Sign out">
            </form>
            <form class="navbar-form navbar-right">
                <input type="hidden" name="command" value="catalogue">
                <input type="submit" value="View full catalogue">
            </form>
        </div>
    </div>
</nav>

<div class="container-fluid">
    <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
            <button class="btn btn-success btn-lg">
                Book catalogue
            </button>
        </div>

        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <h2 class="sub-header">Book orders</h2>
            <div class="table-responsive">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th>Order id</th>
                        <th>User id</th>
                        <th>User first name</th>
                        <th>User last name</th>
                        <th>Book id</th>
                        <th>Book title</th>
                        <th>Book author</th>
                        <th>Book inventory number</th>
                        <th>Date of receiving</th>
                        <th>Date of returning</th>
                        <th>Reading place</th>
                        <th></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="order" items="${orderList}">
                        <tr>
                            <td>${order.id}</td>
                            <td>${order.user.id}</td>
                            <td>${order.user.firstName}</td>
                            <td>${order.user.lastName}</td>
                            <td>${order.book.id}</td>
                            <td>${order.book.title}</td>
                            <td>${order.book.author}</td>
                            <td>${order.book.inventoryNumber}</td>
                            <td>${order.dateOfReceive}</td>
                            <td>${order.dateOfReturn}</td>
                            <td>${order.place}</td>
                            <td>
                                <form class="navbar-form navbar-right">
                                    <input type="hidden" name="command" value="createOrder">
                                    <button type="button">Approve order</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

<script src="../resources/js/jquery.min.js"></script>
<script src=../resources/js/bootstrap.min.js"></script>
<script src="../resources/js/reader.js"></script>

</body>
</html>
