package sprinki.paivat.com.services;

import java.io.Serializable;

import javax.annotation.Resource;

import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import sprinki.paivat.com.domain.UserBean;

public class LoginService implements UserDetailsService, Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8237171255142299225L;
	
	@Resource(name="userService")
	private UserService userService;
	
	@Resource(name="roleService")
	private RoleService roleService;
	
	public UserDetails loadUserByUsername(String username) {

		UserBean user;
		
		user = userService.getByUsername(username);

		
		if (user == null) {
			throw new UsernameNotFoundException("User does NOT exist.");
		}
		
		user.setDateofbirth(DateService.unixtimeToDate(user.getDateofbirth()));
		user.getAuthorities().add(new GrantedAuthorityImpl(roleService.get(user.getRole()).getRole()));

		return user;
	}

}
