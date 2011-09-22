package db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import model.anniversaryBean;

public class DBQuery {

	private Connection connection;
	
	public DBQuery(Connection conn)
	{
		this.connection = conn;
	}
	
	public ResultSet getAnniversariesByUserId(int userid) throws SQLException
	{
		Statement s = this.connection.createStatement();
		s.executeQuery("SELECT * FROM anniversaries WHERE userid='" + userid + "'");
		ResultSet rs = s.getResultSet();
		return rs;
	}
	public boolean createNewUserAccount(String username, String password, String email, String dob) throws SQLException 
	{
		Statement s = this.connection.createStatement();
		String query = "INSERT INTO userbase VALUES (NULL, '" + username + "', UNIX_TIMESTAMP(), UNIX_TIMESTAMP(), '" + email +"', MD5('" + password + "'))";
		int rs = s.executeUpdate(query);
		System.out.println("RS: " + rs);
		if (rs == 1) { return true; }
		else { return false; }
	}

	public ResultSet getAnniversaryById(int id) throws SQLException {
		Statement s = this.connection.createStatement();
		s.executeQuery("SELECT * FROM anniversaries WHERE id='" + id + "'");
		ResultSet rs = s.getResultSet();
		return rs;
	}
	public ResultSet getUserinfoForPassword(int userid) throws SQLException {
		Statement s = this.connection.createStatement();
		String query = "SELECT * FROM userbase WHERE id = "+ userid;
		System.out.println("Query :" + query);
		ResultSet rs = s.executeQuery(query);
		return rs;
		
	}

	public ResultSet getUseridForPasswordAndUsernameCombination(String username, String password) throws SQLException {
		// TODO Auto-generated method stub
		Statement s = this.connection.createStatement();
		String query = "SELECT id FROM userbase WHERE username ='" + username + "' AND password = MD5('" + password + "')";
		System.out.println("Query :" + query);
		ResultSet rs = s.executeQuery(query);
		return rs;
	}
}
