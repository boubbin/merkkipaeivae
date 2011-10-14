package sprinki.paivat.com.validators;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import sprinki.paivat.com.domain.UserBean;

@Component("userValidator")
public class UserValidator {
	private static final String DATE_PATTERN = "(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/((19|20)\\d\\d)";
	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	private Pattern email_pattern;
	private Pattern date_pattern;
	private Matcher matcher;
	public UserValidator() {
		this.email_pattern = Pattern.compile(DATE_PATTERN);
		this.date_pattern  = Pattern.compile(EMAIL_PATTERN);
	}
	public void validate(Object obj, Errors errors) {
		// TODO Auto-generated method stub
		UserBean user = (UserBean) obj;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "field.required", "Required field");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "field.required", "Required field");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password2", "field.required", "Required field");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dob", "field.required", "Required field");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "field.required", "Required field");
		if (!errors.hasFieldErrors("date")) {
			if (!isValidDate(user.getDateofbirth())) {
				errors.rejectValue("date", "not_valid", "Not valid date format!");
			}
		}
		if (!errors.hasFieldErrors("username")) {
			if (user.getUsername().length() < 3) {
				errors.rejectValue("username", "not_valid", "If your name is shorter than 3 characters you may aswell die..");
			}
		}
		if (!errors.hasFieldErrors("username")) {
			if (StringUtils.hasText(user.getUsername())) {
				errors.rejectValue("username", "not_valid", "Username without a single alphabet? Even C3P0 has better name than you..");
			}
		}
		if (!errors.hasFieldErrors("password")) {
			if (user.getPassword().length() < 5) {
				errors.rejectValue("password", "too_short", "Password must be longer than your penis..");
			}
		}
		if (!errors.hasFieldErrors("password2")) {
			if (user.getFormPassword1() != user.getFormPassword2()) {
				errors.rejectValue("password2", "not_same", "Do you think you can use 2 different passwords? Idiot..");
			}
		}
		if (!errors.hasFieldErrors("email")) {
			if (StringUtils.hasText(user.getEmail())) {
				errors.rejectValue("email", "not_valid", "Is there even domain without alphabet characters?");
			}
		}
		if (!errors.hasFieldErrors("email")) {
			if (isValidEmail(user.getEmail())) {
				errors.rejectValue("email", "not_valid", "Not valid email address");
			}
		}
	}
	private boolean isValidEmail(String email) {
		matcher = date_pattern.matcher(email);
		if(matcher.matches()) {
			return true;
		}
		return false;
	}
	private boolean isValidDate(int unixtime) {
		// TODO Auto-generated method stub
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date(unixtime);
		Date parsed;
		try {
			parsed = format.parse(date.toString());
			matcher = email_pattern.matcher(parsed.toString());
			if(matcher.matches()) {
				return true;
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
		}
		return false;
	}
}
