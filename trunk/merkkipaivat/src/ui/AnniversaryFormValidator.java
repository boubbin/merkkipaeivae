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
		return true;
	}

	private boolean validateName(String name) {
		return true;
	}
}
