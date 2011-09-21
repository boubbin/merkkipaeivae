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
}
