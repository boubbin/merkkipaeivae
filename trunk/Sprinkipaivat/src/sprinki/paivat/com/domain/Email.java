package sprinki.paivat.com.domain;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Email {

	public void sendGmail(String to, String from, String subject, String content) throws Exception
	{
		Properties properties = new Properties();
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.port", "587");
		properties.put("mail.smtp.socketFactory.fallback", "false");
		properties.put("mail.smtp.quitwait", "false");
		properties.put("mail.smtp.starttls.enable", "true");
		Authenticator auth = new SMTPAuthenticator();
		Session session = Session.getDefaultInstance(properties, auth);
		
        Message msg = new MimeMessage(session);
        Address fromAddress = new InternetAddress(from);
        msg.setFrom(fromAddress);
        Address[] toAddresses = InternetAddress.parse(to);
        msg.setRecipients(Message.RecipientType.TO, toAddresses);
        msg.setSubject(subject);
        msg.setSentDate(new Date());
        msg.setContent(content, "text/plain");
        
		Transport transport = session.getTransport("smtp");
		transport.connect("smtp.gmail.com","merkkipaeivaet@gmail.com", "kolmasjalka");
		Transport.send(msg);
		transport.close();

	}
	
	public void sendMetropolia(String to, String from, String subject, String content) throws Exception
	{
		Properties properties = new Properties();
		properties.put("mail.smtp.host", "smtp.metropolia.fi");
		properties.put("mail.smtp.auth", "false");
		properties.put("mail.smtp.port", "25");
		properties.put("mail.smtp.socketFactory.fallback", "false");
		properties.put("mail.smtp.quitwait", "false");
		properties.put("mail.smtp.starttls.enable", "false");
		Session session = Session.getDefaultInstance(properties, null);
		
        Message msg = new MimeMessage(session);
        Address fromAddress = new InternetAddress(from);
        msg.setFrom(fromAddress);
        Address[] toAddresses = InternetAddress.parse(to);
        msg.setRecipients(Message.RecipientType.TO, toAddresses);
        msg.setSubject(subject);
        msg.setSentDate(new Date());
        msg.setContent(content, "text/plain");
        
		Transport transport = session.getTransport("smtp");
		//transport.connect("smtp.metropolia.fi","merkkipaeivaet@gmail.com", "kolmasjalka");
		Transport.send(msg);
		transport.close();

	}
	
	private class SMTPAuthenticator extends Authenticator
	{
	    public PasswordAuthentication getPasswordAuthentication()
	    {
	        return new PasswordAuthentication("merkkipaeivaet@gmail.com", "kolmasjalka");
	    }
	}
}
