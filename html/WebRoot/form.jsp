<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  </head>
  
  <body>
     <h2>演示使用Form</h2>
     <form action="form.jsp" method="post">
         Text : <input type="text" name="txt" /> <br />
         CheckBox : <input type="checkbox" name="chb" value="one" />One
                    <input type="checkbox" name="chb" value="two" />Two
                    <input type="checkbox" name="chb" value="three" />Three <br />
         Radio : <input type="radio" name="rd" value = "1">1
                 <input type="radio" name="rd" value = "2">2
                 <input type="radio" name="rd" value = "3">3 <br />
         Select : <select name="fruit" >
					<option value="apple" />苹果
					<option value="orange" />桔子
					<option value="mango" />芒果
				  </select>
         <input type="submit" />
     </form>
     <h2>提交的值：</h2>
     <p>Text ： <%=request.getParameter("txt") %> | ${param.txt}</p>
     <p>CheckBox : <% String[] chb = request.getParameterValues("chb");
         if(chb != null) for(int i=0; i<chb.length; i++)
        	 out.print(chb[i] + " ");
     %></p>
     <p>Radio : <%=request.getParameter("rd") %> | ${param.rd }</p>
     <p>Select : <%=request.getParameter("fruit") %>
  </body>
</html>
