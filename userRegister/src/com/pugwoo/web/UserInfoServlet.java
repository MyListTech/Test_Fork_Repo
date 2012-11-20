package com.pugwoo.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 2012年10月28日 15:49:54
 */
@SuppressWarnings("serial")
public class UserInfoServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
		
		String loginUserName = (String) req.getSession().getAttribute("login");

		if(loginUserName != null && !loginUserName.isEmpty()) {
			resp.getWriter().print("您好," + loginUserName + ",您已登录.");
		}
		else {
			resp.getWriter().print("您尚未登录");
		}
	}
}
