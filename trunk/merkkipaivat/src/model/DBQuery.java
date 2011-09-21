package model;

import java.sql.Connection;
import java.sql.ResultSet;

public class DBQuery {

	private Connection connection;
	private ResultSet rs;
	
	public DBQuery(Connection conn)
	{
		this.connection = conn;
	}
	
}
