package db;

import java.util.Calendar;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;

import model.Email;
import model.UserBean;
import model.anniversaryBean;

public class DBHelper {

	public ArrayList<anniversaryBean> userAnniversariesToString(int userid)
	{
		ArrayList<anniversaryBean> anniversaries = new ArrayList<anniversaryBean>();
		DBConnection conn = new DBConnection();
		DBQuery query = new DBQuery(conn.getConnection());
		DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
		try {
			ResultSet rs = query.getAnniversariesByUserId(userid);
			
			while(rs.next())
			{
				anniversaryBean anniversary = new anniversaryBean();
				Date date = new Date(rs.getLong("date")*1000);
				anniversary.setName(rs.getString("name"));
				anniversary.setPvm(df.format(date));
				anniversary.setId(rs.getInt("id"));
				anniversary.setUserid(rs.getInt("userid"));
				anniversaries.add(anniversary);
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return anniversaries;
	}
	
	public boolean CreateUserAccount(HttpServletRequest request) {
		String username = request.getParameter("username");
		String password = request.getParameter("password1");
		String email    = request.getParameter("email");
		String dob      = request.getParameter("dob");
		boolean success;
		DBConnection conn = new DBConnection();
		DBQuery query = new DBQuery(conn.getConnection());
		try {
			success = query.createNewUserAccount(username, password, email, dob);
		} catch (SQLException e) {
			return false;
		}
		if (success) { return true; }
		return false;
	}

	public anniversaryBean getAnniversary(int id) {
		DBConnection conn = new DBConnection();
		DBQuery query = new DBQuery(conn.getConnection());	
		anniversaryBean anniversary = new anniversaryBean();
		ResultSet rs;
		DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
		try {
			rs = query.getAnniversaryById(id);
		
			while(rs.next())
			{
				Date date = new Date(rs.getLong("date")*1000);
				anniversary.setName(rs.getString("name"));
				anniversary.setPvm(df.format(date));
				anniversary.setId(rs.getInt("id"));
				anniversary.setUserid(rs.getInt("userid"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return anniversary;
	}
	
	public UserBean getUserinfoForUserid(int userid) {
		ResultSet result;
		UserBean userinfo = new UserBean();
		DBConnection conn = new DBConnection();
		DBQuery query = new DBQuery(conn.getConnection());
		try {
			result = query.getUserinfoForUserid(userid);
			while(result.next()) {
				int id          = result.getInt(1);
				String name     = result.getString(2);
				int lastlogin   = result.getInt(3);
				int dob         = result.getInt(4);
				String email    = result.getString(5);
				userinfo.setUserid(id);
				userinfo.setName(name);
				userinfo.setLastlog(lastlogin);
				userinfo.setDateofbirth(dob);
				userinfo.setEmail(email);
				return userinfo;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			}
		return userinfo;
	}
	public int validateLoginForUsernameAgainstPasswordAndReturnUseridForValidRequest(String username, String password) {
		ResultSet result;
		DBConnection conn = new DBConnection();
		DBQuery query = new DBQuery(conn.getConnection());
		try {
			result = query.getUseridForPasswordAndUsernameCombination(username, password);
			while (result.next()) {
				int userid = result.getInt(1);
				return userid;
			}
		} catch (SQLException e) {}
		return 0;	
	}

	public boolean updateAnniversaryById(anniversaryBean anniversary) {
		DBConnection conn = new DBConnection();
		DBQuery query = new DBQuery(conn.getConnection());
		DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
		Date date;
		long unixtime = 0;
		
		try {
			date = (Date)formatter.parse(anniversary.getPvm());
			unixtime = date.getTime() / 1000L;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		try
		{
			if(query.updateAnniversaryById(anniversary, unixtime))
			{ return true; }
			else 
			{ return false; }
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return false;
	}

	public boolean createAnniversaryByUserId(anniversaryBean anniversary) {
		DBConnection conn = new DBConnection();
		DBQuery query = new DBQuery(conn.getConnection());
		DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
		Date date;
		long unixtime = 0;
		
		try {
			date = (Date)formatter.parse(anniversary.getPvm());
			unixtime = date.getTime() / 1000L;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		try
		{
			if(query.createAnniversaryById(anniversary, unixtime))
			{ return true; }
			else 
			{ return false; }
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return false;
	}

	public boolean deleteAnniversaryById(anniversaryBean anniversary) {
		DBConnection conn = new DBConnection();
		DBQuery query = new DBQuery(conn.getConnection());	
		try
		{
			if(query.deleteAnniversaryById(anniversary))
			{ return true; }
			else 
			{ return false; }
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return false;
	}

	public void checkAnniversariesForMail() 
	{
		DBConnection conn = new DBConnection();
		DBQuery query = new DBQuery(conn.getConnection());
		ResultSet rs;
		Calendar currentCal = Calendar.getInstance();
		currentCal.add(Calendar.DAY_OF_MONTH, 1);
		try {
			rs = query.getAllAnniversaries();
			while(rs.next())
			{
				Calendar anniversaryCal = Calendar.getInstance();
				anniversaryCal.setTimeInMillis(rs.getInt("date")*1000L);
				if(rs.getInt("mailed") == 0)
				{
					if(currentCal.get(Calendar.MONTH) == anniversaryCal.get(Calendar.MONTH) && currentCal.get(Calendar.DAY_OF_MONTH) == anniversaryCal.get(Calendar.DAY_OF_MONTH))
					{
						String userEmail = query.getUserEmailByAnniversaryId(rs.getInt("userid"));
						String subject = "Reminder from merkkipaeivaet!";
						String content = rs.getString("name") + " in 1 day!";
						try {
							Email email = new Email();
							email.send(userEmail, "merkkipaeivaet@gmail.com", subject, content);
							System.out.println("Mail sent!");
							query.updateAnniversaryToMailedById(rs.getInt("id"));
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
				else if(rs.getInt("mailed") == 1 && currentCal.get(Calendar.MONTH) != anniversaryCal.get(Calendar.MONTH) && currentCal.get(Calendar.DAY_OF_MONTH) != anniversaryCal.get(Calendar.DAY_OF_MONTH))
				{
					query.updateAnniversaryToNotMailedById(rs.getInt("id"));
					System.out.println("Mailed set to 0!");
				}
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		conn.disconnect();
	}

	public boolean updateUserEmailAndDateOfBirthById(String email, long dob, int userid) {
		DBConnection conn = new DBConnection();
		DBQuery query = new DBQuery(conn.getConnection());
		try {
			if(query.updateUserEmailAndDateOfBirthById(email, dob, userid))
			{
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
