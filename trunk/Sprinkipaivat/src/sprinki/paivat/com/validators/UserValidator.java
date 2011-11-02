package sprinki.paivat.com.validators;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import sprinki.paivat.com.domain.UserBean;
import sprinki.paivat.com.services.UserService;

public class UserValidator implements Validator{

	@Resource(name="userService")
	private UserService userService;
	
	private static final String DATE_PATTERN = "(0?[1-9]|[12][0-9]|3[01]).(0?[1-9]|1[012]).((19|20)\\d\\d)";
	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	private Pattern email_pattern;
	private Pattern date_pattern;
	private Matcher matcher;
	
	public UserValidator() {
		this.date_pattern = Pattern.compile(DATE_PATTERN);
		this.email_pattern  = Pattern.compile(EMAIL_PATTERN);
	}
	
	@Override
	public void validate(Object obj, Errors errors) {
		// TODO Auto-generated method stub
		UserBean user = (UserBean) obj;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "field.required", "Required field");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "formPassword1", "field.required", "Required field");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "formPassword2", "field.required", "Required field");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dateofbirth", "field.required", "Required field");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "field.required", "Required field");
		if (!errors.hasFieldErrors("dateofbirth")) {
			if (!isValidDate(user.getDateofbirth())) {
				errors.rejectValue("dateofbirth", "not_valid", "Not valid date format!");
			}
		}
		if (!errors.hasFieldErrors("username")) {
			if (user.getUsername().length() < 3) {
				errors.rejectValue("username", "not_valid", "If your name is shorter than 3 characters you may aswell die..");
			}
		}
		if (!errors.hasFieldErrors("username")) {
			if (!StringUtils.hasText(user.getUsername())) {
				errors.rejectValue("username", "not_valid", "Username without a single alphabet? Even C3P0 has better name than you..");
			}
		}
//		if(!errors.hasFieldErrors("username")) {
//			if(userService.isUsernameFree(user.getUsername())) {
//				errors.rejectValue("username", "not_valid", "in use");
//			}
//		}
		if (!errors.hasFieldErrors("formPassword1")) {
			if (user.getFormPassword1().length() < 5) {
				errors.rejectValue("formPassword1", "too_short", "Password must be longer than your penis..");
			}
		}
		if (!errors.hasFieldErrors("formPassword2")) {
			if (!user.getFormPassword1().equals(user.getFormPassword2())) {
				errors.rejectValue("formPassword2", "not_same", "Do you think you can use 2 different passwords? Idiot..");
			}
		}
		if (!errors.hasFieldErrors("email")) {
			if (!StringUtils.hasText(user.getEmail())) {
				errors.rejectValue("email", "not_valid", "Is there even domain without alphabet characters?");
			}
		}
		if (!errors.hasFieldErrors("email")) {
			if (isValidEmail(user.getEmail())) {
				errors.rejectValue("email", "not_valid", "Not valid email address");
			}
		}
	}
	
	public void validateEdit(Object obj, Errors errors) {
		UserBean user = (UserBean) obj;
		UserBean persisted = userService.getByUsername(user.getUsername());
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "formPassword1", "field.required", "Required field");
		if(!errors.hasFieldErrors("formPassword1")) {
			if(!user.getFormPassword1().equals(persisted.getPassword())) {
				errors.rejectValue("formPassword1", "not_valid", "Wrong password!");
			}
		}
		if (!errors.hasFieldErrors("dateofbirth")) {
			if(!user.getDateofbirth().equals(null)) {
				if (!isValidDate(user.getDateofbirth())) {
					errors.rejectValue("dateofbirth", "not_valid", "Not valid date format!");
				}
			}
		}
		if (!errors.hasFieldErrors("email")) {
			if(!user.getEmail().equals(null)) {
				if (!StringUtils.hasText(user.getEmail())) {
					errors.rejectValue("email", "not_valid", "Is there even domain without alphabet characters?");
				}
				if (isValidEmail(user.getEmail())) {
					errors.rejectValue("email", "not_valid", "Not valid email address");
				}
			}
		}
	}
	
	private boolean isValidEmail(String email) {
		matcher = email_pattern.matcher(email);
		if(matcher.matches()) {
			return true;
		}
		return false;
	}
	
	private boolean isValidDate(int unixtime) {
		// TODO Auto-generated method stub
		SimpleDateFormat format = new SimpleDateFormat("dd.mm.yyyy");
		Date date = new Date(unixtime);
		try {
			Date parsed = format.parse(date.toString());
			matcher = date_pattern.matcher(parsed.toString());
			if(matcher.matches()) {
				return true;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	private boolean isValidDate(String date) {
		// TODO Auto-generated method stub
		matcher = date_pattern.matcher(date);
		if(matcher.matches()) {
			return true;
		}
		return false;
	}
	
	@Override
	public boolean supports(Class<?> arg0) {
		// TODO Auto-generated method stub
		return false;
	}
}
