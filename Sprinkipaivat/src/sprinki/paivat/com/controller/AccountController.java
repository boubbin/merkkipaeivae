package sprinki.paivat.com.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import sprinki.paivat.com.domain.UserBean;
import sprinki.paivat.com.services.UserService;
import sprinki.paivat.com.validators.UserValidator;

@Controller
public class AccountController {
	
	@Resource(name="userService")
	private UserService userService;
	
	@RequestMapping(method=RequestMethod.GET, value="account/edit")
	public String editAccount(@RequestParam(value="name",required=true) String name, Model model)
	{
		UserBean user = userService.getByUsername(name);
		model.addAttribute("user", user);
		return "account/edit";
	}
	
	@RequestMapping(method=RequestMethod.POST, value="account/edit")
	public String updateAccount(@ModelAttribute("user") UserBean user, BindingResult result)
	{
		UserValidator validator = new UserValidator();
		validator.validate(user, result);
		if(result.hasErrors())
		{
			return "account/edit";
		}
		else
		{
			userService.edit(user);
			return "redirect:/homepage";
		}
	}

}
