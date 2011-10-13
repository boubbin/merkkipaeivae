package sprinki.paivat.com.controller;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import sprinki.paivat.com.services.AuthManager;

@Controller
@RequestMapping(value={"/mainpage", "/"})
public class IndexController{

	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView index()
	{
		UserDetails user = AuthManager.getPrincipal();
		return new ModelAndView("mainpage", "username", user.getUsername());
	}

}
