<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>User Management</title>
</head>
<body>
    <table>
        <c:forEach var="user" items="${userList}">
            <tr>
                <td>${user.id}</td>
                <td>${user.firstName}</td>
                <td>${user.lastName}</td>
                <td>${user.email}</td>
                <td>${user.role}</td>
                <td>
                    <form method="get" action="/controller">
                        <input type="hidden" name="command" value="edituser">
                        <button type="submit">Edit</button>
                    </form>
                </td>
                <td>
                    <form method="post" action="/controller">
                        <input type="hidden" name="command" value="deleteuser">
                        <button type="submit">Delete</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
