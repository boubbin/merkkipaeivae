package ui;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.UserBean;

public class AccountController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(true);
		ServletContext context = getServletContext();
		if(session.getAttribute("authed") == null)
		{
			session.setAttribute("authed", 0);
		}
		
		
		if((Integer)session.getAttribute("authed") == 1)
		{
			if(req.getParameter("action").equals("edit"))
			{
			    RequestDispatcher dispatcher = context.getRequestDispatcher("/jsp/account/edit.jsp");
			    dispatcher.forward(req, resp);
			}
			else
			{
			    RequestDispatcher dispatcher = context.getRequestDispatcher("/mainpage.jsp");
			    dispatcher.forward(req, resp);
			}
		}
		else if(req.getParameter("action").equals("create"))
		{
			RequestDispatcher dispatcher = context.getRequestDispatcher("/jsp/account/create.jsp");
		    dispatcher.forward(req, resp);
		}
		else
		{
			RequestDispatcher dispatcher = context.getRequestDispatcher("/jsp/account/login.jsp");
		    dispatcher.forward(req, resp);
		}
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(true);
		ServletContext context = getServletContext();
		if((Integer)session.getAttribute("authed") == 0 && req.getParameter("action").equals("login"))
		{
			//check login
			UserBean user = new UserBean();
			if(user.checkLogin(req.getParameter("password"), req.getParameter("username")) == true)
			{
				session.setAttribute("authed", 1);
			    RequestDispatcher dispatcher = context.getRequestDispatcher("/mainpage.jsp");
			    dispatcher.forward(req, resp);
			}
			else
			{
				//TODO kerro että login fail
			    this.doGet(req, resp);				
			}
		}
		else
		{
			this.doGet(req, resp);
		}
	}
	

}
