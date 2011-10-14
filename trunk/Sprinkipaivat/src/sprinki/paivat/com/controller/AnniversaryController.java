package sprinki.paivat.com.controller;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import sprinki.paivat.com.domain.AnniversaryBean;
import sprinki.paivat.com.domain.UserBean;
import sprinki.paivat.com.services.AnniversaryService;
import sprinki.paivat.com.services.AuthManager;
import sprinki.paivat.com.services.UserService;


@Controller
public class AnniversaryController {

	@RequestMapping(method=RequestMethod.GET, value="anniversary/all")
	public ModelAndView allAnniversaries()
	{
		AnniversaryService annServ = new AnniversaryService();
		UserService userServ = new UserService();
		UserDetails userdetails = AuthManager.getPrincipal();
		UserBean user = userServ.getByUsername(userdetails.getUsername());
		return new ModelAndView("anniversary/all","anniversaries", annServ.getAllByUserid(user.getUserid()));
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/anniversary/create")
	public ModelAndView createAnniversary()
	{
		return new ModelAndView("anniversary/create");
	}
	
	@RequestMapping(method=RequestMethod.GET, value="anniversary/edit")
	public ModelAndView editAnniversary(@RequestParam(value="id",required=true) Integer anniversaryId, Model model)
	{
		AnniversaryService annServ = new AnniversaryService();
		return new ModelAndView("anniversary/edit", "anniversary", annServ.get(anniversaryId));
	}
	
	@RequestMapping(method=RequestMethod.POST, value="anniversary/edit")
	public String updateAnniversary(@ModelAttribute("anniversary") AnniversaryBean anniversary)
	{
		//TODO VALIDATION
		System.out.println(anniversary.toString());
		AnniversaryService annServ = new AnniversaryService();
		annServ.edit(anniversary);
		return "redirect:anniversary/edit?id=" + anniversary.getId();
	}
}
