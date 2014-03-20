<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
  
  <body>
    <h1>演示条件判断的EL表达式</h1>
    <p>条件判断有c:if  c:choose  c:when  c:otherwise</p>
    <c:if test="${3=1}" var="result">
      3=1的结果：${3=1} <br/>
    </c:if>
    ${result }
    
    
  </body>
</html>
