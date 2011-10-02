package model;

import db.DBHelper;

public class MailDaemon extends Thread {

	public void run()
	{
		DBHelper helper = new DBHelper();
		while(true)
		{
			try {
				//System.out.println("thread sleeping!");
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			helper.checkAnniversariesForMail();
			System.out.println("dates checked!");
		}
	}
}
