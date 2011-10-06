package sprinki.paivat.com.controller;

import java.io.Serializable;
import java.util.Date;

public class GreetingBean implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String message;
	private boolean addTimeStamp;
	private int loopCounter;
	
	public void greetMe()
	{
		for(int i = 0; i < loopCounter; i++)
		{
			System.out.println(message + "höhö");
			if(addTimeStamp) 
			{
				Date date = new Date(300);
				System.out.println(date.toString());
			}
		}
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public boolean isAddTimeStamp() {
		return addTimeStamp;
	}
	public void setAddTimeStamp(boolean addTimeStamp) {
		this.addTimeStamp = addTimeStamp;
	}
	public int getLoopCounter() {
		return loopCounter;
	}
	public void setLoopCounter(int loopCounter) {
		this.loopCounter = loopCounter;
	}
	
	
}
