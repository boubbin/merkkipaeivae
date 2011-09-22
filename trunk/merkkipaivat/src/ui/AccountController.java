package ui;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import db.DBHelper;

import model.CreateAccountFormValidator;
import model.UserBean;

@SuppressWarnings("serial")
public class AccountController extends HttpServlet {

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
				resp.sendRedirect("mainpage");
			    //RequestDispatcher dispatcher = context.getRequestDispatcher("/mainpage.jsp");
			    //dispatcher.forward(req, resp);	
			    
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
					DBHelper helper = new DBHelper();
					if (helper.CreateUserAccount(req)) { session.setAttribute("account_created_ok", "1"); }
					else { session.setAttribute("account_created_ok", "0"); }
					resp.sendRedirect("account?action=create");
				} else {
					// the data wasn't valid, it was submitted tho!
					RequestDispatcher dispatcher = context.getRequestDispatcher("/jsp/account/create.jsp");
				    dispatcher.forward(req, resp);
				}
			} else {
				// First time here, do we need to do anything special? no?
				// well reset some MESSAGES so they don't appear as null
				session.setAttribute("usernameMessage", " ");
				session.setAttribute("passwordMessage", " ");
				session.setAttribute("emailMessage", " ");
				session.setAttribute("dobMessage", " ");
				RequestDispatcher dispatcher = context.getRequestDispatcher("/jsp/account/create.jsp");
			    dispatcher.forward(req, resp);
			}
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
			DBHelper helper = new DBHelper();
			String username = req.getParameter("username");
			String password = req.getParameter("password");
			int userid = helper.validateLoginForUsernameAgainstPasswordAndReturnUseridForValidRequest(username, password);
			if(userid > 0) {
				user = helper.getUserinfoForUserid(userid);
				session.setAttribute("authed", 1);
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
