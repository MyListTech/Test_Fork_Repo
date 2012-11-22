<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">   
    <title>JSP EL表达式</title>

  </head>
  
  <body>
     <h2>基本操作</h2>
     <p>演示<a href="operator.jsp" target="_blank">operator</a></p>
     <h2>条件判断</h2>
     <p>演示<a href="if.jsp" target="_blank">if</a></p>
     <h2>循环</h2>
     <p>演示<a href="loop.jsp" target="_blank">loop</a></p>
     <h2>容器变量</h2>
     <p>有pageScope requestScope sessionScope applicationScope</p>
     <p>以session为例，演示：<a href="session.jsp" target="_blank">session</a></p>
     <h2>传递参数</h2>
     <p>演示<a href="param.jsp" target="_blank">param</a></p>
     <h4>更多参考资料：http://www.javabeat.net/tips/4-expression-language-in-jsp-20.html</h4>
  </body>
</html>
