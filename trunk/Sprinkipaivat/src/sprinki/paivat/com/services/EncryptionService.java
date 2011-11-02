package sprinki.paivat.com.services;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;


public class EncryptionService {
	
	public static String encrypt(String plain)
	{
		Md5PasswordEncoder encoder = new Md5PasswordEncoder();
		
		return encoder.encodePassword(plain, null);
	}

}
