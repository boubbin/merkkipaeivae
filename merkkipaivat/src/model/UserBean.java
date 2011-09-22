package model;



import db.DBHelper;

@SuppressWarnings("serial")
public class UserBean implements java.io.Serializable {

	private int userid;
	private String name;
	private int lastlog;
	private int dateofbirth;
	private String email;
	
	public UserBean() {
		
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getDateofbirth() {
		return dateofbirth;
	}

	public void setDateofbirth(int dateofbirth) {
		this.dateofbirth = dateofbirth;
	}
	public int getLastlog() {
		return lastlog;
	}

	public void setLastlog(int lastlog) {
		this.lastlog = lastlog;
	}

	public int getUseridForCorrectCredetialCombination(String username, String password) {
		//TODO pass ja user vertaus mysliin
		DBHelper helper = new DBHelper();
		return helper.validateLoginForUsernameAgainstPassword(username, password);	
	}
	public UserBean getUserInfoForUserid(int userid) {
		//TODO pass ja user vertaus mysliin
		DBHelper helper = new DBHelper();
		return helper.getUserinfoForUserid(userid);
	}	
}
