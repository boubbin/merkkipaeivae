package model;

public class UserBean implements java.io.Serializable {

	private int userid;
	private String name;
	private String email;
	private int dateofbirth;
	
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
	
	
	
}
