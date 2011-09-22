package model;

@SuppressWarnings("serial")
public class anniversaryBean implements java.io.Serializable {

	private String name;
	private String pvm;
	private int userid;
	private int id;
	
	public anniversaryBean() 
	{
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPvm() {
		return pvm;
	}

	public void setPvm(String pvm) {
		this.pvm = pvm;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	

}
