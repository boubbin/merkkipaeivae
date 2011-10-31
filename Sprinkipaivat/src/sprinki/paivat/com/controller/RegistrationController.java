package sprinki.paivat.com.controller;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import sprinki.paivat.com.domain.UserBean;
import sprinki.paivat.com.services.UserService;
import sprinki.paivat.com.validators.UserValidator;

@Controller
@SessionAttributes("user")
public class RegistrationController {
	
	@Resource(name="userService")
	private UserService userService;
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String showForm(Model model) {
		UserBean user = new UserBean();
		model.addAttribute("registrationForm", user);
		return "create";
	}
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String add(Model model, @ModelAttribute("registrationForm") @Valid UserBean user, BindingResult result) {
		UserValidator userValidator = new UserValidator();
		userValidator.validate(user, result);
		if (result.hasErrors()) {
			System.out.println(result.getAllErrors());
			return "create";
		}
		userService.add(user);
		return "added";
	}
}
