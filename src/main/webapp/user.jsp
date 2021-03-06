<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>用户列表</title>
</head>
<body>
<h1>${message }</h1>

<h1>用户列表--<a href="/user/add">添加用户</a>---<a href="/logout">退出登录</a></h1>

<h2>权限列表</h2>
<shiro:authenticated>用户已经登录显示此内容<br/></shiro:authenticated>
<shiro:hasRole name="管理员">管理员角色登录显示此内容<br/></shiro:hasRole>
<shiro:hasRole name="普通用户">普通用户角色登录显示此内容<br/></shiro:hasRole>

<shiro:hasAnyRoles name="管理员,普通用户">**管理员 or 普通用户 角色用户登录显示此内容**<br/></shiro:hasAnyRoles>
<shiro:principal/>-显示当前登录用户名<br/>
<shiro:hasPermission name="功能一">功能一权限用户显示此内容<br/></shiro:hasPermission>
<shiro:hasPermission name="user:query">query权限用户显示此内容<shiro:principal/><br/></shiro:hasPermission>
<shiro:lacksPermission name="功能一"> 不具有功能一权限的用户显示此内容<br/></shiro:lacksPermission>
<ul>
    <c:forEach items="${userList }" var="user">
        <li>用户名：${user.name }----
            密码：${user.password }----
            <a href="/user/edit/${user.id}">修改用户</a>----
            <a href="javascript:;" class="del" ref="${user.id }">删除用户</a>
        </li>
    </c:forEach>
</ul>

<script type="text/javascript" src="http://cdn.staticfile.org/jquery/1.9.1/jquery.min.js"></script>
<script>
    $(function () {
        $(".del").click(function () {
            var id = $(this).attr("ref");
            $.ajax({
                type: "delete",
                url: "/user/del/" + id,
                success: function (e) {

                }
            });
        });
    });
</script>
</body>
</html>