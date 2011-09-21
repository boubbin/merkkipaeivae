package ui;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.DBConnection;

@SuppressWarnings({ "serial", "unused" })
public class IndexController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//TEST
		
        HttpSession session = req.getSession(true);
        if (session.getAttribute("user") == null) { session.setAttribute("authed", 0); }
        
	    ServletContext context = getServletContext();
	    RequestDispatcher dispatcher = context.getRequestDispatcher("/mainpage.jsp");
	    dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
}
