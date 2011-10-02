package ui;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.MailDaemon;

@WebServlet("/DaemonStarter")
public class DaemonStarter extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void init()
	{
		MailDaemon md = new MailDaemon();
		md.start();
		getServletContext().setAttribute("daemon", md);
	}

}
