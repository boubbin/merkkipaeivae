package sprinki.paivat.com.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import sprinki.paivat.com.domain.AnniversaryBean;

public class AnniversaryValidator implements Validator {
	private static final String DATE_PATTERN = "(0?[1-9]|[12][0-9]|3[01]).(0?[1-9]|1[012]).((19|20)\\d\\d)";
	private Pattern pattern;
	private Matcher matcher;
	public AnniversaryValidator() {
		this.pattern = Pattern.compile(DATE_PATTERN);
	}
	@Override
	public boolean supports(Class<?> arg0) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void validate(Object obj, Errors errors) {
		AnniversaryBean anniversary = (AnniversaryBean) obj;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "date", "field.required", "Required field");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "field.required", "Required field");
		if (!errors.hasFieldErrors("date")) {
			if (!isValidDate(anniversary.getDate())) {
				errors.rejectValue("date", "not_valid", "Not valid date format!");
			}
		}
		if (!errors.hasFieldErrors("name")) {
			if (anniversary.getName().length() < 5) {
				errors.rejectValue("name", "not_valid", "Who even bothers to celebrate anniversary which name is shorter than 5 characters? Go fuck yourself... in the ass");
			}
		}
		if (!errors.hasFieldErrors("name")) {
			if (!StringUtils.hasText(anniversary.getName())) {
				errors.rejectValue("name", "not_valid", "Anniversary without a single alphabet?");
			}
		}
	}

	private boolean isValidDate(String date) {
		// TODO Auto-generated method stub
		matcher = pattern.matcher(date);
		if(matcher.matches()) {
			return true;
		}
		return false;
	}


}
