package sprinki.paivat.com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value={"/mainpage", "/"})
public class IndexController{

	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView index()
	{
		return new ModelAndView("mainpage");
	}

}
