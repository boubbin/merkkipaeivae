package ui;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.DBConnection;
import model.DBHelper;
import model.DBQuery;
import model.UserBean;
import model.anniversaryBean;

public class AnniversaryController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	       HttpSession session = req.getSession(true);
	       ServletContext context = getServletContext();
	       System.out.println(session.getAttribute("user"));
	       if (session.getAttribute("user") == null) { session.setAttribute("authed", 0); }
	       
	       if((Integer)session.getAttribute("authed") == 1)
	       {
	    	   if(req.getParameter("action").equals("all"))
	    	   {
	    		   //näytä kaikki merkkipäivät
	    		   DBHelper helper = new DBHelper();
	    		   UserBean user = (UserBean)session.getAttribute("user");
	    		   ArrayList<anniversaryBean> anniversaries = helper.userAnniversariesToString(user.getUserid());
	    		   session.setAttribute("anniversaries", anniversaries);
	    		   RequestDispatcher dispatcher = context.getRequestDispatcher("/jsp/anniversary/all.jsp");
				   dispatcher.forward(req, resp);
	    	   }
	    	   else if(req.getParameter("action").equals("edit"))
	    	   {
	    		   //editoidaan yhtä
					RequestDispatcher dispatcher = context.getRequestDispatcher("/jsp/anniversary/edit.jsp");
				    dispatcher.forward(req, resp);
	    	   }
	    	   else if(req.getParameter("action").equals("create"))
	    	   {
	    		   //luo uusi merkkipäivä
					RequestDispatcher dispatcher = context.getRequestDispatcher("/jsp/anniversary/create.jsp");
				    dispatcher.forward(req, resp);   		   
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
		
	       
	}

	
}
