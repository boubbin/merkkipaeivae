package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
}
