<%-- 
    Document   : index
    Created on : 2010-10-21, 10:49:09
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" rel="stylesheet" href="style/my.css" />
        <script src="script/jquery-1.4.2.js" type="text/JavaScript"></script>
        <script src="script/my.js" type="text/JavaScript"></script>
        <title>User Register</title>
    </head>
    <body>
        <h1>User Register</h1>
        <form method="post" id="form">
            <label>
                Username:<input type="text" id="username" name="username" />*
                <span id="chk_username"></span>
            </label>
            <br/>
            <label>
                Password:<input type="password" id="password" name="password" />*
                <span id="chk_password"></span>
            </label>
            <br/>
            <input type="button" value="login" id="login"/>
            <input type="button" value="register" id="register"/>
        </form>
        <p>
            <a href="info">查看我的登录状态</a>
        </p>
    </body>
</html>
