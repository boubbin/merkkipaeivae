package sprinki.paivat.com.services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;
import java.util.TimerTask;

import javax.annotation.Resource;

import sprinki.paivat.com.domain.AnniversaryBean;
import sprinki.paivat.com.domain.Email;
import sprinki.paivat.com.domain.UserBean;


public class MailDaemon extends TimerTask {
	
	@Resource(name="anniversaryService")
	private AnniversaryService anniversaryService;
	
	@Resource(name="userService")
	private UserService userService;
	
	public void run()
	{
			List<AnniversaryBean> anniversaries = anniversaryService.getAll();
			checkAnniversariesForMail(anniversaries);
			System.out.println("dates checked!");
	}
	
	private void checkAnniversariesForMail(List<AnniversaryBean> anniversaries) 
	{
		
		Calendar currentCal = Calendar.getInstance();
		currentCal.add(Calendar.DAY_OF_MONTH, 1);
			for(AnniversaryBean anniversary : anniversaries)
			{
				Calendar anniversaryCal = Calendar.getInstance();
				anniversaryCal.setTimeInMillis(Long.parseLong(anniversary.getDate())*1000L);
				if(anniversary.getMailed() == 0)
				{
					if(currentCal.get(Calendar.MONTH) == anniversaryCal.get(Calendar.MONTH) && currentCal.get(Calendar.DAY_OF_MONTH) == anniversaryCal.get(Calendar.DAY_OF_MONTH))
					{
						UserBean user = userService.get(anniversary.getUserid());
						String userEmail = user.getEmail();
						String subject = "Reminder from merkkipaeivaet!";
						String content = anniversary.getName() + " in 1 day!";
						try {
							Email email = new Email();
							email.sendGmail(userEmail, "merkkipaeivaet@gmail.com", subject, content);
							//email.sendMetropolia(userEmail, "merkkipaeivaet@metropolia.fi", subject, content);
							System.out.println("Mail sent!");
							anniversary.setMailed(1);
							anniversaryService.editMailed(anniversary);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
				else if(anniversary.getMailed() == 1 && currentCal.get(Calendar.MONTH) != anniversaryCal.get(Calendar.MONTH) && currentCal.get(Calendar.DAY_OF_MONTH) != anniversaryCal.get(Calendar.DAY_OF_MONTH))
				{
					anniversary.setMailed(0);

					anniversaryService.editMailed(anniversary);
					System.out.println("Mailed set to 0!");
				}
			}			
	}
}
