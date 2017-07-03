<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>My orders page</title>

    <!-- Bootstrap core CSS -->
    <link href="../resources/css/bootstrap.min.css" rel="stylesheet"/>
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
            </div>
        </div>
    </nav>

    <div class="container-fluid">
        <div class="row">
            <div class="col-sm-3 col-md-2 sidebar">
                <p>
                    <a class="btn btn-lg btn-success" href="jsp/reader.jsp" role="button">Home page</a>
                </p>
            </div>
            <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
                <h2 class="sub-header">My orders</h2>
                <div class="table-responsive">
                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <th>Order id</th>
                            <th>Book title</th>
                            <th>Book author</th>
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
                                <td>${order.book.title}</td>
                                <td>${order.book.author}</td>
                                <td>${order.dateOfReceive}</td>
                                <td>${order.dateOfReturn}</td>
                                <td>${order.place}</td>
                                <td>
                                    <form class="navbar-form navbar-right">
                                        <input type="hidden" name="command" value="login">
                                        <button type="button">Edit order</button>
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

</body>
</html>
