<nav class="navbar navbar-default navbar-fixed-top">
    <div class="container-fluid">
       <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse visible-lg-inline">
            <c:choose>
                <c:when test="${sessionScope.user.role eq 'LIBRARIAN'}">
                    <ul class="nav navbar-nav">
                        <li><a href="/librarian"><fmt:message key="librarian.menu.home"/></a></li>
                        <li><a href="/books"><fmt:message key="librarian.menu.books"/></a></li>
                        <li><a href="/users"><fmt:message key="librarian.menu.users"/></a></li>
                        <li><a href="/orders"><fmt:message key="librarian.menu.orders"/></a></li>
                        <li>
                            <a href="#" class="btn btn-default disabled" role="button">
                                <ctg:hello-tag role="${sessionScope.user.role}" userName="${sessionScope.user.firstName}"/>
                            </a>
                        </li>
                    </ul>
                </c:when>
                <c:when test="${sessionScope.user.role eq 'READER'}">
                    <ul class="nav navbar-nav">
                        <li><a href="/reader"><fmt:message key="librarian.menu.home"/></a></li>
                        <li><a href="/jsp/search.jsp"><fmt:message key="reader.menu.search"/></a></li>
                        <li>
                            <a href="#" class="btn btn-default disabled" role="button">
                                <ctg:hello-tag role="${sessionScope.user.role}" userName="${sessionScope.user.firstName}"/>
                            </a>
                        </li>
                    </ul>
                    <form action="/controller" class="navbar-form navbar-right">
                        <input type="hidden" name="command" value="myorders">
                        <input type="submit" class="btn btn-default" value="<fmt:message key="reader.menu.myorders"/>">
                    </form>
                </c:when>
            </c:choose>
            <form action="/controller" class="navbar-form navbar-right">
                <input type="hidden" name="command" value="signout">
                <input type="submit" class="btn btn-default"  value="<fmt:message key="index.sign.out"/>">
            </form>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>