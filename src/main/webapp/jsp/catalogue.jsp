<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Full book catalogue</title>

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
                <a class="btn btn-lg btn-success" href="jsp/librarian.jsp" role="button">Home page</a>
            </p>
        </div>

        <!-- Modal #editModal-->
        <div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-labelledby="editModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal"> <span aria-hidden="true" class="">×   </span><span class="sr-only">Close</span>
                        </button>
                        <h4 class="modal-title" id="editModalLabel">Edit book</h4>
                    </div>
                    <div class="modal-body">
                        <form role="form" id="editContactForm">
                            <div class="form-group">
                                <label for="bookId">Book id</label>
                                <p class="form-control-static" id="bookId"></p>
                            </div>
                            <div class="form-group">
                                <label for="editBookTitle">Book title</label>
                                <input type="text" class="form-control" id="editBookTitle"/>
                            </div>
                            <div class="form-group">
                                <label for="editBookAuthor">Book author</label>
                                <input type="text" class="form-control" id="editBookAuthor"/>
                            </div>
                            <div class="form-group">
                                <label for="editInventoryNumber">Inventory number</label>
                                <input type="text" class="form-control" id="editInventoryNumber"/>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-primary" id="editBook" data-dismiss="modal">Save changes</button>
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
                        <th>Book id</th>
                        <th>Book title</th>
                        <th>Book author</th>
                        <th>Inventory number</th>
                        <th></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="book" items="${bookList}">
                        <tr>
                            <td>${book.id}</td>
                            <td>${book.title}</td>
                            <td>${book.author}</td>
                            <td>${book.inventoryNumber}</td>
                            <td>
                                <button class="btn btn-success btn-lg" data-toggle="modal" data-target="#editModal">
                                    Edit book
                                </button>
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
<script src="../resources/js/bootstrap.min.js"></script>
<script src="../resources/js/catalogue.js"></script>
</body>
</html>
