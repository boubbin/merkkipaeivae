package sprinki.paivat.com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController{

	@RequestMapping("/")
	public ModelAndView index()
	{
		String sunmutsis = "index";
		return new ModelAndView("hello", "greets", sunmutsis);
	}

}