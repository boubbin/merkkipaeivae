package model;

import javax.servlet.http.HttpServletRequest;

public class FormValidator {
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
		if (username == null) { return false; }
		if (username.length() < 4 || username.length() > 15) { return false; }
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
