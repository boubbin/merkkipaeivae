package db;
import java.sql.PreparedStatement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.anniversaryBean;

public class DBQuery {

	private Connection connection;
	
	public DBQuery(Connection conn)
	{
		this.connection = conn;
	}
	
	public ResultSet getAnniversariesByUserId(int userid) throws SQLException
	{
		String query = "SELECT * FROM anniversaries WHERE userid = ?";
		PreparedStatement prepared = connection.prepareStatement(query);
		prepared.setInt(1, userid);
		ResultSet result = prepared.executeQuery();
		return result;
	}
	public boolean createNewUserAccount(String username, String password, String email, String dob) throws SQLException 
	{
		String query = "INSERT INTO userbase VALUES (NULL, ?, UNIX_TIMESTAMP(), UNIX_TIMESTAMP(), ?, MD5(?))";
		PreparedStatement prepared = this.connection.prepareStatement(query);
		prepared.setString(1, username);
		prepared.setString(2, email);
		prepared.setString(3, password);
		int result = prepared.executeUpdate();
		if (result == 1) { return true; }
		else { return false; }
	}

	public ResultSet getAnniversaryById(int id) throws SQLException {
		String query = "SELECT * FROM anniversaries WHERE id = ?";
		PreparedStatement prepared = this.connection.prepareStatement(query);
		prepared.setInt(1, id);
		ResultSet result = prepared.executeQuery();
		return result;
	}
	public ResultSet getUserinfoForUserid(int userid) throws SQLException {
		String query = "SELECT * FROM userbase WHERE id = ?";
		PreparedStatement prepared = this.connection.prepareStatement(query);
		prepared.setInt(1, userid);
		ResultSet result = prepared.executeQuery();
		return result;
	}

	public ResultSet getUseridForPasswordAndUsernameCombination(String username, String password) throws SQLException {
		// TODO Auto-generated method stub
		String query = "SELECT id FROM userbase WHERE username = ? AND password = MD5(?)";
		PreparedStatement prepared = this.connection.prepareStatement(query);
		prepared.setString(1, username);
		prepared.setString(2, password);
		ResultSet result = prepared.executeQuery();
		return result;
	}

	public boolean updateAnniversaryById(anniversaryBean anniversary, long unixtime) throws SQLException {
		String query = "UPDATE anniversaries SET date = ?, name = ? WHERE id = ?;";
		PreparedStatement prepared = this.connection.prepareStatement(query);
		prepared.setLong(1, unixtime);
		prepared.setString(2, anniversary.getName());
		prepared.setInt(3, anniversary.getId());
		int result = prepared.executeUpdate();
		if (result == 1) { return true; }
		else { return false; }	
	}

	public boolean createAnniversaryById(anniversaryBean anniversary, long unixtime) throws SQLException {
		String query = "INSERT INTO anniversaries(userid, date, name) values(?, ?, ?);";
		PreparedStatement prepared = this.connection.prepareStatement(query);
		prepared.setInt(1, anniversary.getUserid());
		prepared.setLong(2, unixtime);
		prepared.setString(3, anniversary.getName());	
		int result = prepared.executeUpdate();
		if (result == 1) { return true; }
		else { return false; }	
	}
}
