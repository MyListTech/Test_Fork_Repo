<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.pugwoo.User"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
//做一些初始化
session.setAttribute("test", "pugwoo");
User user = new User();
user.setId(1L);
user.setName("nick");
session.setAttribute("user",user);
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>My JSP 'index.jsp' starting page</title>
  </head>
  
  <body>
    <h1>演示关于session的EL表达式</h1>
    <h2>添加数据到session中</h2>
    <p>调用session.setAttribute方法将对象加入到session中</p>
    <h2>读取session中数据：使用sessionScope.属性名</h2>
    <p>读取到的test是${sessionScope.test}</p>
    <p>用户ID：${sessionScope.user.id }，用户名：${sessionScope.user.name }</p>
    <h3>【注意】sessionScope等可以省略，将按顺序查找</h3>
    <p>用户ID：${user.id }，用户名：${user.name }</p>
    <h3>使用c:set赋值和c:out设置默认输出值</h3>
    <c:set var="name" value="pgw"></c:set>
    <c:out value="${name}" default="没有用户名" escapeXml="false"></c:out>
  </body>
</html>
