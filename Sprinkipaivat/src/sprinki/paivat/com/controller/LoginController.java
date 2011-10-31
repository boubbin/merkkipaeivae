package sprinki.paivat.com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class LoginController {
	@RequestMapping(method=RequestMethod.GET, value="/login")
	public ModelAndView index()
	{
		return new ModelAndView("/login");
	}
	
}
