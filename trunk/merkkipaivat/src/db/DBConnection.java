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
	
	public int connect()
	{
		DataSource ds;
		int i = 0; //palautetaan käytetty tietokanta; 1=oma kone, 0=metroSQL
		
		try {
        	Context initCtx = new InitialContext();
        	Context envCtx = (Context) initCtx.lookup("java:comp/env");
        	try {
        		ds = (DataSource) envCtx.lookup("jdbc/mysliDB");
        		i=1;
        	}catch(NamingException ex) {
	        	ds = (DataSource) envCtx.lookup("jdbc/metroSQL");
	        	i=0;
	        }
        	this.connection = ds.getConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}       	
        	catch (NamingException ex)
	        {
	        	System.out.println(ex.getMessage());
	        	ex.printStackTrace();
	        }
		return i;
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
