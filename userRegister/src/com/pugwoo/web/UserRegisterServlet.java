package com.pugwoo.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pugwoo.model.User;
import com.pugwoo.service.UserManager;

@SuppressWarnings("serial")
public class UserRegisterServlet extends HttpServlet {

	//Get方法仅用于查询用户名是否存在
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
		String username = req.getParameter("username");
        if(username != null && !username.isEmpty()){
        	
            if(UserManager.isExistUsername(username)){
                resp.getWriter().print("<span style='color:red'>Username "+username+" EXIST!</span>");
            }
            else{
            	resp.getWriter().print("<span style='color:green'>Username "+username+" NOT EXIST!</span>");
            }
        }
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		if (username != null && !username.isEmpty() &&
			password != null && !password.isEmpty()){
			
			if(UserManager.isExistUsername(username)){
				resp.getWriter().print("username is exist.");
			}else{
				UserManager.saveUser(new User(username,password));
				resp.getWriter().print("Register successfully, your username is " + username);
			}
		}
	}

}
