<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit user</title>
</head>
<body>
<form action="/controller" method="post">
    USER ID: <input type="text" name="id" readonly/>${user.getId()}<br>
    FIRST NAME: <input type="text" name="firstName" readonly>${user.getFirstName()}<br>
    LAST NAME: <input type="text" name="lastName" readonly>${user.getLastName()}<br>
    USER EMAIL: <input type="text" name="name" readonly/>${user.getEmail()}<br>
    USER ROLE: <input type="radio" name="price" value="male" checked>${user.getRole().name()}<br>
</form>
</body>
</html>
