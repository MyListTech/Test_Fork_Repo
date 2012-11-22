<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.pugwoo.User"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
  </head>
  
  <%
  // 设置一些初始值
  User user = new User();
  user.setId(3L);
  user.setName("pugwoo");
  
  int[] numbers = new int[]{1,2,3};
  request.setAttribute("numbers", numbers);
  %>
  
  <body>
    <h1>演示关于操作符的EL表达式</h1>
    <p>加减乘除等运算操作符：${10 + 20*40}，同C语言</p>
    <p>布尔运算：${3 > 4 }</p>
    <p>点操作符，获取对象的属性或map的值：${user.name }</p>
    <p>中括号[]运算符，获取数组或链表的值：${numbers[0] }</p>
  </body>
</html>
