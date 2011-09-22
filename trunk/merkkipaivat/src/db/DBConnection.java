package db;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBConnection {

	private Connection connection;
	
	public DBConnection()
	{
		this.connect();
	}
	
	public void connect()
	{
		try {
        	Context initCtx = new InitialContext();
        	Context envCtx = (Context) initCtx.lookup("java:comp/env");
        	DataSource ds = (DataSource) envCtx.lookup("jdbc/mysliDB");
        	this.connection = ds.getConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}       	
        	catch (NamingException ex)
	        {
	        	System.out.println(ex.getMessage());
	        	ex.printStackTrace();
	        }
	}
	
	public void disconnect()
	{
		try {
			this.connection.close();
		}
		catch(SQLException e) {
			e.printStackTrace();
		} 
	}
	
	public Connection getConnection()
	{
		if(this.connection == null)
		{
			this.connect();
		}
		
		return this.connection;
	}
}
