package sprinki.paivat.com.services;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptionService {
	
	public static String encrypt(String plain)
	{
		
		byte[] defaultBytes = plain.getBytes();
		
		try {
			
			MessageDigest algorithm = MessageDigest.getInstance("SHA-512");
			algorithm.reset();
			algorithm.update(defaultBytes);
			byte messageDigest[] = algorithm.digest();
		            
			StringBuffer hexString = new StringBuffer();
			for (int i=0;i<messageDigest.length;i++) {
				hexString.append(Integer.toHexString(0xFF & messageDigest[i]));
			}
			/*
			String foo = messageDigest.toString();
			System.out.println("sessionid "+plain+" md5 version is "+hexString.toString());
			plain=hexString+"";
			*/
			
			return hexString.toString();
			
		} catch(NoSuchAlgorithmException nsae) { }
		
		return "";
	}

}
