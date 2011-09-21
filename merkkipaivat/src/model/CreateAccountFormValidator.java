package model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class CreateAccountFormValidator {
	HttpServletRequest request;
	private String messageUsername = "Username length between 5-14 characters (for example: vagina)";
	private String messagePassword = "Password length longer than 5 characters (example: cocksucker)";
	private String messageEmail = "Not valid email address (example: javaeemaster@jogging.com)";
	private String messageDOB = "Format: yyyy-mm-dd (example 2001-09-11)";
	private HttpSession session;

	public CreateAccountFormValidator() {
	}
	
	public CreateAccountFormValidator(HttpServletRequest request) {
		super();
		HttpSession session = request.getSession(true);
		this.session = session;
	}
	public void popMessageUsername() {
		this.session.setAttribute("usernameMessage", "");
	}
	public String popMessagePassword() {
		return messagePassword;  
	}
	public String popMessageEmail() {
		return messageEmail;
	}
	public String popMessageDOB() {
		return messageDOB;
	}
	
	public void pushMessageUsername() {
		this.session.setAttribute("usernameMessage", "trololololol");
	}
	public String pushMessagePassword() {
		return messagePassword;  
	}
	public String pushMessageEmail() {
		return messageEmail;
	}
	public String pushMessageDOB() {
		return messageDOB;
	}

	public boolean validateRequest(HttpServletRequest request) {
		if (this.validateUsername(request.getParameter("username")) == false) { return false; }
		if (this.validateEmail(request.getParameter("email")) == false)       { return false; }
		if (this.validateDOB(request.getParameter("dob")) == false)           { return false; }
		if (this.validatePasswords(request.getParameter("password1"), request.getParameter("password2")) == false) { return false; }	
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
		this.popMessageUsername();
		return true;
	}
	private boolean validateEmail(String email) {
	    String[] tokens = email.split("@");
	    try {
		    if (tokens[0] == null || tokens[1] == null) 			{ return false; }
		    if (tokens[0].length() == 0 || tokens[1].length() == 0) { return false; }	    	
	    } catch (ArrayIndexOutOfBoundsException e) {
	    	return false;
	    }
	    return true;
	}
	private boolean validatePasswords(String password1, String password2) {
		if (password1 == null || password2 == null) { return false; }
		if (!password1.equals(password2))			{ return false; }
		if (password1.length() < 5)					{ return false; }
		if (password2.length() < 5)					{ return false; }
		return true;
	}
	private boolean validateDOB(String dob) {

		String[] tokens = dob.split("-");
		if (tokens[0] == null || tokens[1] == null || tokens[2] == null) 	{ return false; }
		if (!this.validateInt(tokens[0])) 									{ return false; }
		if (!this.validateInt(tokens[1])) 									{ return false; }
		if (!this.validateInt(tokens[2])) 									{ return false; }
		return true;
	}
}
