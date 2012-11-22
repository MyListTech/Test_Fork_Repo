<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.pugwoo.User"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
  </head>
  
  <body>
    <h1>演示关于参数的EL表达式</h1>
    <p>对应的EL容器是param</p>
    <form action="param.jsp">
      <input type="text" name="username" />
      <input type="submit" />
    </form>
    <p>提交的用户名：${param.username}</p>
  </body>
</html>
