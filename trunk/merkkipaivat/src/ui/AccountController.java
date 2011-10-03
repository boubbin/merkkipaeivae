package ui;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
				UserBean user = (UserBean)session.getAttribute("user");
				Calendar cal = Calendar.getInstance();
				cal.setTimeInMillis(user.getDateofbirth()*1000L);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String dob = sdf.format(cal.getTime());
				session.setAttribute("dob", dob);
			    RequestDispatcher dispatcher = context.getRequestDispatcher("/jsp/account/edit.jsp");
			    dispatcher.forward(req, resp);
			    this.eraseErrorMessages(session);
			} 
			else if (req.getParameter("action").equals("logout")) 
			{
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
				this.eraseErrorMessages(session);
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
				this.eraseErrorMessages(session);
			    RequestDispatcher dispatcher = context.getRequestDispatcher("/mainpage.jsp");
			    dispatcher.forward(req, resp);
			}
			else 
			{
			    this.doGet(req, resp);		
			}
		}
		else if((Integer)session.getAttribute("authed") == 1 && req.getParameter("action").equals("edit"))
		{
			CreateAccountFormValidator accValidator = new CreateAccountFormValidator(req);
			if(accValidator.validateEditRequest(req))
			{
				this.eraseErrorMessages(session);
				DBHelper helper = new DBHelper();
				UserBean user = (UserBean)session.getAttribute("user");
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				
				Date date;
				try {
					Calendar cal = Calendar.getInstance();
					date = sdf.parse(req.getParameter("dob"));
					cal.setTime(date);
					long dob = cal.getTimeInMillis() / 1000L;
					if(helper.updateUserEmailAndDateOfBirthById((String)req.getParameter("email"), dob, user.getUserid()))
					{
						UserBean Updateduser = helper.getUserinfoForUserid(user.getUserid());
						session.setAttribute("user", Updateduser);
						session.setAttribute("accountEditMessage", "Account edited successfully!");
					}					
				} catch (ParseException e) {
					e.printStackTrace();
				}

				resp.sendRedirect("/merkkipaivat/account?action=edit");
			}
			else
			{
				resp.sendRedirect("/merkkipaivat/account?action=edit");
			}
		}
		else
		{
			this.doGet(req, resp);
		}
		
	}
	

	public void eraseErrorMessages(HttpSession session)
	{
		session.setAttribute("accountEditMessage", " ");
		session.setAttribute("anniversaryDeleteMessage", " ");
		session.setAttribute("anniversaryEditMessage", " ");
		session.setAttribute("anniversaryCreateMessage", " ");
		session.setAttribute("usernameMessage", " ");
		session.setAttribute("passwordMessage", " ");
		session.setAttribute("emailMessage", " ");
		session.setAttribute("dobMessage", " ");
	}
}
