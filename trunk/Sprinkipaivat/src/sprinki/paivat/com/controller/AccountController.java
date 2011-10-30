package sprinki.paivat.com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class AccountController {
	@RequestMapping(method=RequestMethod.GET, value="account/login")
	public ModelAndView index()
	{
		return new ModelAndView("account/login");
	}
	
}
