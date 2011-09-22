package db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.DBConnection;
import model.DBQuery;
import model.HttpServletRequest;
import model.String;
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
				anniversary.setName(rs.getString("name"));
				anniversary.setPvm(rs.getInt("date"));
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
