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
    <h1>循环</h1>
    <p>采用c:forEach进行循环</p>
    <p>简单循环
		<c:forEach var="i" begin="1" end="10" step="1"> 
      ${i} 
		</c:forEach></p>
		
    <p>循环List
    <%  List<String> list = new ArrayList<String>(); 
		list.add("aa"); 
		list.add("bb"); 
		list.add("cc");
		request.setAttribute("list", list);
    %>
    <c:forEach items="${list}" var="element"> 
	    ${element}
    </c:forEach>    
    </p>
    
    <p>使用forToken对字符串进行分割
    <c:forTokens items="aa,bb,cc,dd" begin="0" end="4" step="1" delims="," var="aValue"> 
	${aValue} 
</c:forTokens> 
    </p>
	</body>
</html>
