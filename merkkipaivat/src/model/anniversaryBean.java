package model;

@SuppressWarnings("serial")
public class anniversaryBean implements java.io.Serializable {

	private String name;
	private int pvm;	
	
	public anniversaryBean() 
	{
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPvm() {
		return pvm;
	}

	public void setPvm(int pvm) {
		this.pvm = pvm;
	}
	

}
