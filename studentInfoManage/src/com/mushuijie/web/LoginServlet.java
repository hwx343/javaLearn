package com.mushuijie.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mushuijie.dao.UserDao;
import com.mushuijie.impl.UserDaoImpl;
import com.mushuijie.model.User;

public class LoginServlet extends HttpServlet{
	private UserDao ud=new UserDaoImpl();
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		request.setAttribute("username", username);
		request.setAttribute("password", password);
		if(username.equals("")||password.equals("")){
			request.setAttribute("error", "用户名或密码为空！");
			request.getRequestDispatcher("index.jsp").forward(request, response);
			return;
		}
		User u=ud.loginIn(username, password);
		if(u==null){
			request.setAttribute("error", "用户名或密码错误！");
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}else{
			HttpSession hs=request.getSession();
			hs.setAttribute("currentUser", u);
			response.sendRedirect("main.jsp");
		}
		
	}

}
