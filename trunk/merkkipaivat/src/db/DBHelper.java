package db;

import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;

import model.UserBean;
import model.anniversaryBean;

public class DBHelper {

	public ArrayList<anniversaryBean> userAnniversariesToString(int userid)
	{
		ArrayList<anniversaryBean> anniversaries = new ArrayList<anniversaryBean>();
		DBConnection conn = new DBConnection();
		DBQuery query = new DBQuery(conn.getConnection());
		try {
			ResultSet rs = query.getAnniversariesByUserId(userid);
			
			while(rs.next())
			{
				anniversaryBean anniversary = new anniversaryBean();
				Date date = new Date(rs.getLong("date")*1000);
				anniversary.setName(rs.getString("name"));
				anniversary.setPvm(date.toLocaleString());
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
		DateFormat df = new SimpleDateFormat("dd.MM.YYYY");
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
}
