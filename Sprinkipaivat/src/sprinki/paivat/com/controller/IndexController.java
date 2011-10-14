package sprinki.paivat.com.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
	public ModelAndView index(HttpServletRequest request)
	{
		UserDetails user = AuthManager.getPrincipal();
		HttpSession session = request.getSession();
		session.setAttribute("username", user.getUsername());
		return new ModelAndView("mainpage");
	}

}
