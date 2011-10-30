package sprinki.paivat.com.controller;

import java.text.ParseException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import sprinki.paivat.com.domain.AnniversaryBean;
import sprinki.paivat.com.domain.UserBean;
import sprinki.paivat.com.services.AnniversaryService;
import sprinki.paivat.com.services.AuthManager;
import sprinki.paivat.com.services.UserService;
import sprinki.paivat.com.validators.AnniversaryValidator;


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

	@RequestMapping(method=RequestMethod.GET, value="anniversary/create")
	public String createAnniversaryForm(Model model)
	{
		AnniversaryBean anniversary = new AnniversaryBean();
		model.addAttribute("anniversary", anniversary);
		return "anniversary/create";
	}
	@RequestMapping(method=RequestMethod.POST, value="anniversary/create")
	public String createAnniversary(@ModelAttribute("anniversary") AnniversaryBean anniversary, BindingResult result) throws ParseException
	{
		AnniversaryValidator validator = new AnniversaryValidator();
		validator.validate(anniversary, result);
		if (result.hasErrors()) {
			return "anniversary/create";
		} else {
			UserDetails userdetails = AuthManager.getPrincipal();
			String username = userdetails.getUsername();
			anniversary.setUserid(userService.getByUsername(username).getUserid());
			anniversaryService.add(anniversary);
			return "redirect:/anniversary/all";
		}
	}
	@RequestMapping(method=RequestMethod.GET, value="anniversary/edit")
	public String editAnniversary(@RequestParam(value="id",required=true) Integer anniversaryId, Model model)
	{
		AnniversaryBean anniversary = anniversaryService.get(anniversaryId);
		model.addAttribute("anniversary", anniversary);
		return "anniversary/edit";
	}
	
	@RequestMapping(method=RequestMethod.GET, value="anniversary/delete")
	public String deleteAnniversary(@RequestParam(value="id",required=true) Integer anniversaryId, Model model)
	{
		AnniversaryBean anniversary = anniversaryService.get(anniversaryId);
		UserDetails userdetails = AuthManager.getPrincipal();
		UserBean user = userService.getByUsername(userdetails.getUsername());
		if(anniversary != null)
		{
			if(user.getUserid() == anniversary.getUserid())
			{
				anniversaryService.delete(anniversary.getId());
			}
		}
		return "redirect:/anniversary/all";
	}
	
	@RequestMapping(method=RequestMethod.POST, value="anniversary/edit")
	public String updateAnniversary(@ModelAttribute("anniversary") AnniversaryBean anniversary, BindingResult result)
	{
		AnniversaryValidator validator = new AnniversaryValidator();
		validator.validate(anniversary, result);
		if(result.hasErrors())
		{
			return "anniversary/edit";
		}
		else
		{
			anniversaryService.edit(anniversary);
			return "redirect:/anniversary/all";
		}
	}
}
