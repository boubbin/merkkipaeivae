package sprinki.paivat.com.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import sprinki.paivat.com.domain.UserBean;
import sprinki.paivat.com.services.UserService;
import sprinki.paivat.com.validators.UserValidator;

@Controller
@RequestMapping("/account")
@SessionAttributes("user")
public class RegistrationController {
	
	private UserService userService;
	
	private UserValidator userValidator;
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String showForm(Model model) {
		UserBean user = new UserBean();
		model.addAttribute("registrationForm", user);
		return "account/create";
	}
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String add(Model model, @ModelAttribute("registrationForm") @Valid UserBean user, BindingResult result) {
		userValidator = new UserValidator();
		userValidator.validate(user, result);
		if (result.hasErrors()) {
			System.out.println(result.getAllErrors());
			return "account/create";
		}
		userService.add(user);
		return "account/created";
	}
}
