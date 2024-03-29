package sprinki.paivat.com.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import sprinki.paivat.com.domain.UserBean;
import sprinki.paivat.com.services.AuthManager;
import sprinki.paivat.com.services.DateService;
import sprinki.paivat.com.services.UserService;
import sprinki.paivat.com.validators.UserValidator;

@Controller
public class AccountController {
	
	@Resource(name="userService")
	private UserService userService;
	
	@RequestMapping(method=RequestMethod.GET, value="account/edit")
	public String editAccount(Model model)
	{
		UserBean user = userService.getByUsername(AuthManager.getPrincipal().getUsername());
		user.setFormPassword1("");
		user.setDateofbirth(DateService.unixtimeToDate(user.getDateofbirth()));
		model.addAttribute("user", user);
		return "account/edit";
	}
	
	@RequestMapping(method=RequestMethod.POST, value="account/edit")
	public String updateAccount(@ModelAttribute("user") UserBean user, BindingResult result)
	{
		UserValidator validator = new UserValidator();
		UserBean original = userService.getByUsername(AuthManager.getPrincipal().getUsername());
		validator.validateEdit(user,original, result);
		if(result.hasErrors())
		{
			return "account/edit";
		}
		else
		{
			userService.edit(user);
			return "redirect:/mainpage";
		}
	}

}
