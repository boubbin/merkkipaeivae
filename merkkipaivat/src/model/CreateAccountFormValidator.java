package model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class CreateAccountFormValidator {
	// HttpServletRequest request;
	private HttpSession session;

	public CreateAccountFormValidator() {
	}
	
	public CreateAccountFormValidator(HttpServletRequest request) {
		super();
		HttpSession session = request.getSession(true);
		this.session = session;
	}
	
	public void emptyset() {
		// TODO Auto-generated method stub
		this.session.setAttribute("usernameMessage", " ");
		this.session.setAttribute("passwordMessage", " ");
		this.session.setAttribute("emailMessage", " ");
		this.session.setAttribute("dobMessage", " ");
	}

	public void pushMessageUsername() {
		this.session.setAttribute("usernameMessage", "Username length between 5-14 characters (for example: vagina)");
	}
	public void pushMessagePassword() {
		this.session.setAttribute("passwordMessage", "Password length longer than 5 characters (example: cocksucker)");  
	}
	public void pushMessageEmail() {
		this.session.setAttribute("emailMessage", "Not valid email address (example: javaeemaster@jogging.com)");
	}
	public void pushMessageDOB() {
		this.session.setAttribute("dobMessage", "Format: yyyy-mm-dd (example 2001-09-11)");
	}

	public boolean validateRequest(HttpServletRequest request) {
		this.emptyset();
		if (this.validateUsername(request.getParameter("username")) == false) { return false; }
		if (this.validatePasswords(request.getParameter("password1"), request.getParameter("password2")) == false) { return false; }	
		if (this.validateEmail(request.getParameter("email")) == false)       { return false; }
		if (this.validateDOB(request.getParameter("dob")) == false)           { return false; }
		return true;
	}
	
	public boolean validateEditRequest(HttpServletRequest request) {
		if (this.validateEmail(request.getParameter("email")) == false)       { return false; }
		if (this.validateDOB(request.getParameter("dob")) == false)           { return false; }
		return true;
	}
	
	private boolean validateInt(String integer) {
		if (integer == null) { return false; }
		try {
			Integer.parseInt(integer);
			return true;
		} catch(NumberFormatException nfe) {
			return false;
		}
	}
	private boolean validateUsername(String username) {
		this.pushMessageUsername();
		if (username == null) { return false; }
		if (username.length() < 4 || username.length() > 15) { return false; }
		session.setAttribute("usernameMessage", " ");
		return true;
	}
	private boolean validateEmail(String email) {
		this.pushMessageEmail();
	    String[] tokens = email.split("@");
	    try {
		    if (tokens[0] == null || tokens[1] == null) 			{ return false; }
		    if (tokens[0].length() == 0 || tokens[1].length() == 0) { return false; }	    	
	    } catch (ArrayIndexOutOfBoundsException e) {
	    	return false;
	    }
	    session.setAttribute("emailMessage", " ");
	    return true;
	}
	private boolean validatePasswords(String password1, String password2) {
		this.pushMessagePassword();
		if (password1 == null || password2 == null) { return false; }
		if (!password1.equals(password2))			{ return false; }
		if (password1.length() < 5)					{ return false; }
		session.setAttribute("passwordMessage", " ");
		return true;
	}
	private boolean validateDOB(String dob) {
		this.pushMessageDOB();
		String[] tokens = dob.split("-");
		try {
			if (tokens[0] == null || tokens[1] == null || tokens[2] == null) 	{ return false; }
			if (!this.validateInt(tokens[0])) 									{ return false; }
			if (!this.validateInt(tokens[1])) 									{ return false; }
			if (!this.validateInt(tokens[2])) 									{ return false; }
		} catch (ArrayIndexOutOfBoundsException e) {
			return false;
		}
		session.setAttribute("dobMessage", " ");
		return true;
	}
}
