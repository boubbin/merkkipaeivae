package ui;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;

public class AnniversaryFormValidator {

	public AnniversaryFormValidator()
	{
		
	}

	public boolean validateAnniversary(HttpServletRequest req) {
		if(!this.validateName(req.getParameter("name"))) { return false; }
		if(!this.validateDate(req.getParameter("date"))) { return false; }
		return true;
	}

	private boolean validateDate(String date) {
		String[] tokens = date.split("-");
		try {
			if (tokens[0] == null || tokens[1] == null || tokens[2] == null) 	{ return false; }
			if (!this.validateInt(tokens[0])) 									{ return false; }
			if (!this.validateInt(tokens[1])) 									{ return false; }
			if (!this.validateInt(tokens[2])) 									{ return false; }
		} catch (ArrayIndexOutOfBoundsException e) {
			return false;
		}
		return true;
	}

	private boolean validateName(String name) {
		if(name.length() < 2 || name.length() > 30)
		{ return false; }
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
}
