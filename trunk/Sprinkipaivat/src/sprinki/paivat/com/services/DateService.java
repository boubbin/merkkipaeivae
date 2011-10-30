package sprinki.paivat.com.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateService {

	public static String dateToUnixtime(String date) {
		
		Date temp;
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
		Calendar cal = Calendar.getInstance();
		
		try {
			temp = sdf.parse(date);
			cal.setTime(temp);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		String unixtime = Long.toString(cal.getTimeInMillis() / 1000L);
		
		return unixtime;
	}
	
	public static String unixtimeToDate(String unixtime) {
		
		Calendar cal = Calendar.getInstance();
		Long unix = Long.valueOf(unixtime);
		
		cal.setTimeInMillis(unix*1000);
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
		String date = sdf.format(cal.getTime());
		
		return date;
	}
	
}