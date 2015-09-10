<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>SpringCase</title>
</head>
<body>
Login
<form action="/login" method="post">
    <input type="text" name="name"/><br/>
    <input type="password" name="password"/><br/>
    <input type="submit" name="loginSubmit" vale="登录"/><br/>
    ${message}
</form>
</body>
</html>
