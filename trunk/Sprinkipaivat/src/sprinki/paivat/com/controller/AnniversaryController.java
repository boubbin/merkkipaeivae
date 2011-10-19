package sprinki.paivat.com.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
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

	@Resource(name="anniversaryService")
	private AnniversaryService anniversaryService;
	
	@Resource(name="userService")
	private UserService userService;
	
	@RequestMapping(method=RequestMethod.GET, value="anniversary/all")
	public String allAnniversaries(Model model)
	{
		UserDetails userdetails = AuthManager.getPrincipal();
		String username = userdetails.getUsername();
		UserBean user = userService.getByUsername(username);
		List<AnniversaryBean> anniversaries = anniversaryService.getAllByUserid(user.getUserid());
		model.addAttribute("anniversaries", anniversaries);
		return "anniversary/all";
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/anniversary/create")
	public ModelAndView createAnniversary()
	{
		return new ModelAndView("anniversary/create");
	}
	
	@RequestMapping(method=RequestMethod.GET, value="anniversary/edit")
	public ModelAndView editAnniversary(@RequestParam(value="id",required=true) Integer anniversaryId, Model model)
	{
		return new ModelAndView("anniversary/edit", "anniversary", anniversaryService.get(anniversaryId));
	}
	
	@RequestMapping(method=RequestMethod.POST, value="anniversary/edit")
	public String updateAnniversary(@ModelAttribute("anniversary") AnniversaryBean anniversary)
	{
		//TODO VALIDATION
		System.out.println(anniversary.toString());
		anniversaryService.edit(anniversary);
		return "redirect:anniversary/edit?id=" + anniversary.getId();
	}
}
