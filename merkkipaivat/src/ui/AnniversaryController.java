package ui;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AnniversaryController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	       HttpSession session = req.getSession(true);
	       ServletContext context = getServletContext();
	       if (session.getAttribute("user") == null) { session.setAttribute("authed", 0); }
	       
	       if((Integer)session.getAttribute("authed") == 1)
	       {
	    	   if(req.getParameter("action").equals("all"))
	    	   {
	    		   //n�yt� kaikki merkkip�iv�t
					RequestDispatcher dispatcher = context.getRequestDispatcher("/jsp/anniversary/all.jsp");
				    dispatcher.forward(req, resp);
	    	   }
	    	   else if(req.getParameter("action").equals("edit"))
	    	   {
	    		   //editoidaan yht�
					RequestDispatcher dispatcher = context.getRequestDispatcher("/jsp/anniversary/edit.jsp");
				    dispatcher.forward(req, resp);
	    	   }
	    	   else if(req.getParameter("action").equals("create"))
	    	   {
	    		   //luo uusi merkkip�iv�
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
