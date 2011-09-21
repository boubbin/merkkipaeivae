package ui;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.CreateAccountFormValidator;
import model.DBConnection;
import model.DBHelper;
import model.DBQuery;
import model.UserBean;

@SuppressWarnings("serial")
public class AccountController extends HttpServlet {

	private boolean CreateUserAccount(HttpServletRequest request) {
		String username = request.getParameter("username");
		String password = request.getParameter("password1");
		String email    = request.getParameter("email");
		boolean success;
		DBConnection conn = new DBConnection();
		DBQuery query = new DBQuery(conn.getConnection());
		try {
			success = query.createNewUserAccount(username, password, email);
		} catch (SQLException e) {
			return false;
		}
		if (success) { return true; }
		return false;
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(true);
		ServletContext context = getServletContext();
		if(session.getAttribute("authed") == null) { session.setAttribute("authed", 0); }
		
		if((Integer)session.getAttribute("authed") == 1)
		{
			if(req.getParameter("action").equals("edit"))
			{
			    RequestDispatcher dispatcher = context.getRequestDispatcher("/jsp/account/edit.jsp");
			    dispatcher.forward(req, resp);
			} else if (req.getParameter("action").equals("logout")) {
				session.setAttribute("authed", 0);
				session.invalidate();
			    RequestDispatcher dispatcher = context.getRequestDispatcher("/mainpage.jsp");
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
			if (req.getParameterMap().size() == 6) {
				// if request contains 6 items it means that the form has been submitted
				// it doesnt mean that all 6 fields are set tho.. they might be null!
				CreateAccountFormValidator validator = new CreateAccountFormValidator(req);
				if (validator.validateRequest(req)) {
					// the data is valid, but is not converted or sanitized!
					// so save it to mysql and make sure to use prepared statements
					session.setAttribute("account_created_ok", "1");
					if (this.CreateUserAccount(req)) { session.setAttribute("account_created_ok", "1"); }
					else { session.setAttribute("account_created_ok", "0"); }
				} else {
					// the data wasn't valid, it was submitted tho!
				}
			} else {
				// First time here, do we need to do anything special? no?
				// well reset some MESSAGES so they don't appear as null
				session.setAttribute("usernameMessage", " ");
				session.setAttribute("passwordMessage", " ");
				session.setAttribute("emailMessage", " ");
				session.setAttribute("dobMessage", " ");
			}
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
				user.setUserid(1);
				session.setAttribute("user", user);
			    RequestDispatcher dispatcher = context.getRequestDispatcher("/mainpage.jsp");
			    dispatcher.forward(req, resp);
			}
			else
			{
				//TODO kerro ett� login fail
			    this.doGet(req, resp);				
			}
		}
		else
		{
			this.doGet(req, resp);
		}
	}
	

}
