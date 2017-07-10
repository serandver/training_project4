<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>My orders page</title>

    <!-- Bootstrap core CSS -->
    <link href="../resources/css/bootstrap.min.css" rel="stylesheet"/>
</head>
<body>
<nav class="navbar navbar-default navbar-fixed-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
        </div>
        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse visible-lg-inline">
            <ul class="nav navbar-nav">
                <li><a href="/reader">Home</a></li>
                <li><a href="/jsp/search.jsp">Search</a></li>
            </ul>

            <form action="/controller" class="navbar-form navbar-left">
                <input type="hidden" name="command" value="show-my-orders">
                <input type="submit" class="btn btn-default" value="My orders">
            </form>
            <form action="/controller" class="navbar-form navbar-right">
                <input type="hidden" name="command" value="signout">
                <input type="submit" class="btn btn-default" value="Sign out">
            </form>
            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Language<span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="#">Ru</a></li>
                        <li><a href="#">En</a></li>
                    </ul>
                </li>
            </ul>
        </div><!-- /.navbar-collapse -->
    </div>
</nav>

    <div class="container-fluid">
        <div class="row">
            <div class="col-sm-3 col-md-2 sidebar">
                <p>
                    <a class="btn btn-lg btn-success" href="jsp/reader.jsp" role="button">Home page</a>
                </p>
            </div>

            <!-- Modal #editModal-->
            <div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-labelledby="editModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal"> <span aria-hidden="true" class="">×   </span><span class="sr-only">Close</span>
                            </button>
                            <h4 class="modal-title" id="editModalLabel">Edit your order</h4>
                        </div>
                        <div class="modal-body">
                            <form role="form" id="editContactForm">
                                <div class="form-group">
                                    <label for="contactId">Order id</label>
                                    <p class="form-control-static" id="contactId"></p>
                                </div><div class="form-group">
                                <label for="editFirstName">Book title</label>
                                <input type="text" class="form-control" id="editFirstName"/>
                            </div>
                                <div class="form-group">
                                    <label for="editLastName">Book author</label>
                                    <input type="text" class="form-control" id="editLastName"/>
                                </div>
                                <div class="form-group">
                                    <label for="editPatronymic">Date of receiving</label>
                                    <input type="text" class="form-control" id="editPatronymic"/>
                                </div>
                                <div class="form-group">
                                    <label for="editMobilePhoneNumber">Date of returning</label>
                                    <input type="tel" class="form-control" id="editMobilePhoneNumber"/>
                                </div>
                                <div class="form-group">
                                    <label for="editHomePhoneNumber">Home phone</label>
                                    <input type="tel" class="form-control" id="editHomePhoneNumber"/>
                                </div>
                                <div class="form-group">
                                    <label for="editAddress">Address</label>
                                    <input type="text" class="form-control" id="editAddress"/>
                                </div>
                                <div class="form-group">
                                    <label for="editInputEmail">Email address</label>
                                    <input type="email" class="form-control" id="editInputEmail"/>
                                </div>
                            </form>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-primary" id="editContact" data-dismiss="modal">Save changes</button>
                            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                        </div>
                    </div>
                </div>
            </div>
            <!-- End modal #editModal-->

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
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
<script src="../resources/js/jquery.min.js"></script>
<script src="../resources/js/bootstrap.min.js"></script>
</body>
</html>
