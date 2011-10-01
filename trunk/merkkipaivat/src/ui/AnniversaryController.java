package ui;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import db.DBHelper;

import model.AnniversaryFormValidator;
import model.UserBean;
import model.anniversaryBean;

@SuppressWarnings("serial")
public class AnniversaryController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	       HttpSession session = req.getSession(true);
	       ServletContext context = getServletContext();
	       if (session.getAttribute("user") == null) { session.setAttribute("authed", 0); }
	       
	       if((Integer)session.getAttribute("authed") == 1)
	       {
	    	   if(req.getParameter("action") == null)
	    	   { 
	    		   RequestDispatcher dispatcher = context.getRequestDispatcher("/jsp/anniversary/all.jsp");
				   dispatcher.forward(req, resp);
	    	   }
	    	   else if(req.getParameter("action").equals("all"))
	    	   {
	    		   //näytä kaikki merkkipäivät
	    		   DBHelper helper = new DBHelper();
	    		   UserBean user = (UserBean)session.getAttribute("user");
	    		   ArrayList<anniversaryBean> anniversaries = helper.userAnniversariesToString(user.getUserid());
	    		   session.setAttribute("anniversaries", anniversaries);
	    		   RequestDispatcher dispatcher = context.getRequestDispatcher("/jsp/anniversary/all.jsp");
				   dispatcher.forward(req, resp);
				   session.setAttribute("anniversaryDeleteMessage", " ");
				   session.setAttribute("anniversaryEditMessage", " ");
	    	   }
	    	   else if(req.getParameter("action").equals("edit"))
	    	   {
	    		   //editoidaan yhtä
	    		   DBHelper helper = new DBHelper();
	    		   anniversaryBean anniversary = helper.getAnniversary((Integer.parseInt(req.getParameter("id"))));
	    		   session.setAttribute("anniversary", anniversary);
	    		   RequestDispatcher dispatcher = context.getRequestDispatcher("/jsp/anniversary/edit.jsp");
				   dispatcher.forward(req, resp);
				   session.setAttribute("anniversaryEditMessage", " ");
	    	   }
	    	   else if(req.getParameter("action").equals("create"))
	    	   {
	    		   //luo uusi merkkipäivä
					RequestDispatcher dispatcher = context.getRequestDispatcher("/jsp/anniversary/create.jsp");
				    dispatcher.forward(req, resp);
				    session.setAttribute("anniversaryCreateMessage", " ");
	    	   }
	    	   else if(req.getParameter("action").equals("delete"))
	    	   {
	    		   this.doPost(req, resp);
	    	   }
	    	   else
	    	   {
	    		   RequestDispatcher dispatcher = context.getRequestDispatcher("/jsp/anniversary/all.jsp");
				   dispatcher.forward(req, resp);
				   session.setAttribute("anniversaryDeleteMessage", " ");
	    	   }
	       }
	       else
	       {
		   	    RequestDispatcher dispatcher = context.getRequestDispatcher("/mainpage.jsp");
			    dispatcher.forward(req, resp);	    	   
	       }
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException { 
		HttpSession session = req.getSession(true);
		
		if((Integer)session.getAttribute("authed") == 1)	      
		{
			if(req.getParameter("action").equals("edit"))
			{
				AnniversaryFormValidator validator = new AnniversaryFormValidator(req);
				if(validator.validateAnniversary(req))
				{
					DBHelper helper = new DBHelper();
					anniversaryBean anniversary = (anniversaryBean)session.getAttribute("anniversary");
					anniversary.setName(req.getParameter("name"));
					anniversary.setPvm(req.getParameter("date"));
					if(helper.updateAnniversaryById(anniversary))
					{
						session.setAttribute("anniversaryEditMessage", "Anniversary successfully edited");
					}
				}
				resp.sendRedirect("/merkkipaivat/anniversary?action=all");
			}
			else if(req.getParameter("action").equals("create"))
			{
				AnniversaryFormValidator validator = new AnniversaryFormValidator(req);
				if(validator.validateAnniversary(req))
				{
					DBHelper helper = new DBHelper();
					anniversaryBean anniversary = new anniversaryBean();
					UserBean user = (UserBean)session.getAttribute("user");
					anniversary.setName(req.getParameter("name"));
					anniversary.setPvm(req.getParameter("date"));
					anniversary.setUserid(user.getUserid());
					if(helper.createAnniversaryByUserId(anniversary))
					{
						session.setAttribute("anniversaryCreateMessage", "Anniversary successfully created");
					}
				}
			resp.sendRedirect("/merkkipaivat/anniversary?action=create");
			}
			else if(req.getParameter("action").equals("delete"))
			{
				DBHelper helper = new DBHelper();
				UserBean user = (UserBean)session.getAttribute("user");
				anniversaryBean anniversary = new anniversaryBean();
				anniversary.setUserid(user.getUserid());
				anniversary.setId((Integer.parseInt(req.getParameter("id"))));
				   
				if(helper.deleteAnniversaryById(anniversary))
				{
					session.setAttribute("anniversaryDeleteMessage", "Anniversary successfully deleted");
				}
				else
				{
					session.setAttribute("anniversaryDeleteMessage", "Something funny happened");
				}
				resp.sendRedirect("/merkkipaivat/anniversary?action=all");
			}
		}
	}
}
