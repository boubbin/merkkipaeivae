package sprinki.paivat.com.services;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.*;

public class AuthManager {

	public static UserDetails getPrincipal()
	{
		return (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}
}
