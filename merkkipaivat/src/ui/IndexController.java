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

@SuppressWarnings({ "serial", "unused" })
public class IndexController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
        HttpSession session = req.getSession(true);
        session.setAttribute("authed", "boubbin");
        
	    ServletContext context = getServletContext();
	    RequestDispatcher dispatcher = context.getRequestDispatcher("/mainpage.jsp");
	    dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
	    //ServletContext context = getServletContext();
	    //RequestDispatcher dispatcher = context.getRequestDispatcher("/jsp/index.jsp");
	    //dispatcher.forward(req, resp);

	}
}
