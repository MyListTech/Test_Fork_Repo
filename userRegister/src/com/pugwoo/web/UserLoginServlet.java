package com.pugwoo.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pugwoo.model.User;
import com.pugwoo.service.UserManager;

/**
 * 2011年1月5日 上午11:32:41
 */
@SuppressWarnings("serial")
public class UserLoginServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
		
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		User user = UserManager.getUserByName(username);
		
		if(user != null && user.getPassword().equals(password)){
			/**
			 * 2012年10月28日 15:36:52 新增session记录
			 */
			req.getSession().setAttribute("login", username);
			
			resp.getWriter().print("登录成功");
		}else{
			resp.getWriter().print("登录失败");
		}
		
	}

}
