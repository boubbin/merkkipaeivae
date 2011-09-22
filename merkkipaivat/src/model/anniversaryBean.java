package model;

@SuppressWarnings("serial")
public class anniversaryBean implements java.io.Serializable {

	private String name;
	private String pvm;	
	
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
	

}
