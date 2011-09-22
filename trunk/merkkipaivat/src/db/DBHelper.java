package db;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
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
}
