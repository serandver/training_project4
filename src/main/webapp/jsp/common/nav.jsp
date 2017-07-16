<nav class="navbar navbar-default navbar-fixed-top">
    <div class="container-fluid">
       <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse visible-lg-inline">
            <c:choose>
                <c:when test="${sessionScope.user.role eq 'LIBRARIAN'}">
                    <ul class="nav navbar-nav">
                        <li>
                            <form action="/controller" class="navbar-form navbar-left">
                                <input type="hidden" name="command" value="librHome">
                                <input type="submit" class="btn btn-default" value="<fmt:message key="librarian.menu.home"/>">
                            </form>
                        </li>
                        <li>
                            <form action="/controller" class="navbar-form navbar-left">
                                <input type="hidden" name="command" value="catalogue">
                                <input type="submit" class="btn btn-default" value="<fmt:message key="librarian.menu.books"/>">
                            </form>
                        <li>
                            <form action="/controller" class="navbar-form navbar-left">
                                <input type="hidden" name="command" value="users">
                                <input type="submit" class="btn btn-default" value="<fmt:message key="librarian.menu.users"/>">
                            </form>
                        <li>
                            <form action="/controller" class="navbar-form navbar-left">
                                <input type="hidden" name="command" value="orders">
                                <input type="submit" class="btn btn-default" value="<fmt:message key="librarian.menu.orders"/>">
                            </form>
                        <li>
                            <a href="#" class="btn btn-default disabled" role="button">
                                <ctg:hello-tag role="${sessionScope.user.role}" userName="${sessionScope.user.firstName}"/>
                            </a>
                        </li>
                    </ul>
                </c:when>
                <c:when test="${sessionScope.user.role eq 'READER'}">
                    <ul class="nav navbar-nav">
                        <li>
                            <form action="/controller" class="navbar-form navbar-left">
                                <input type="hidden" name="command" value="readerHome">
                                <input type="submit" class="btn btn-default" value="<fmt:message key="librarian.menu.home"/>">
                            </form>
                        </li>
                        <li>
                            <form action="/controller" class="navbar-form navbar-left">
                                <input type="hidden" name="command" value="seachPage">
                                <input type="submit" class="btn btn-default" value="<fmt:message key="reader.menu.search"/>">
                            </form>
                        </li>
                        <li>
                            <form action="/controller" class="navbar-form navbar-left">
                                <input type="hidden" name="command" value="myorders">
                                <input type="submit" class="btn btn-default" value="<fmt:message key="reader.menu.myorders"/>">
                            </form>
                        </li>
                        <li>
                            <a href="#" class="btn btn-default disabled" role="button">
                                <ctg:hello-tag role="${sessionScope.user.role}" userName="${sessionScope.user.firstName}"/>
                            </a>
                        </li>
                    </ul>
                </c:when>
            </c:choose>
            <form action="/controller" class="navbar-form navbar-right">
                <input type="hidden" name="command" value="signout">
                <input type="submit" class="btn btn-default"  value="<fmt:message key="index.sign.out"/>">
            </form>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>