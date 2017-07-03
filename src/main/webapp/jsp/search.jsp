<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="q" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Search a book in the catalogue</title>
    <link rel="stylesheet" type="text/css" href ="../resources/css/log.css">
    <link rel="stylesheet" type="text/css" href ="../resources/css/catalog.css">
</head>
<body>
<fieldset>
    <div class="log_form">
        <fieldset>
            <form name="SearchForm" method="POST" action="controller">
                <legend><H2><c:out value="Find book by title:"/></H2></legend>
                <p><span><c:out value="Book title"/></span><input type="text" name="title" value=""><span></span></p>
                <input type="hidden" name="command" value="findByTitle">
                <input type="submit" value="Search" class="subo">
            </form>
        </fieldset>
        <fieldset>
            <form name="SearchForm" method="POST" action="controller">
                <legend><H2><c:out value="Find book by title:"/></H2></legend>
                <p><span><c:out value="Book author"/></span><input type="text" name="author" value=""><span></span></p>
                <input type="hidden" name="command" value="findByAuthor">
                <input type="submit" value="Search" class="subo">
            </form>
        </fieldset>
    </div>
    <form name="logoutForm" method="POST" action="controller">
        <input type="hidden" name="command" value="logout">
        <input type="submit" value="${parametersMap.get('Logout')}" class="subo">
    </form>
</fieldset>
</body>
</html>
