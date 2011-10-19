package sprinki.paivat.com.domain;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.web.bind.annotation.ModelAttribute;

@Entity
@Table(name = "anniversaries")
public class AnniversaryBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@GeneratedValue
	private int id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "date")
	private String date;
	
	@Column(name = "userid")
	private int userid;
	
	@Column(name = "mailed")
	private int mailed;
	
	
	public AnniversaryBean() 
	{
		
	}


	public int getMailed() {
		return mailed;
	}


	public void setMailed(int mailed) {
		this.mailed = mailed;
	}
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}


	public int getUserid() {
		return userid;
	}

	@ModelAttribute(value = "create")
	public void setUserid(int userid) {
		this.userid = userid;
		System.out.println("MOI");
	}
	public void dateToUnixtime() throws ParseException {
		Date datee;
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
		Calendar cal = Calendar.getInstance();
		datee = sdf.parse(this.getDate());
		cal.setTime(datee);
		String unixtime = Long.toString(cal.getTimeInMillis() / 1000L);
		this.setDate(unixtime);	
	}
	public void unixtimeToDate() {
		Calendar cal = Calendar.getInstance();
		Long unix = Long.valueOf(this.getDate());
		cal.setTimeInMillis(unix*1000);
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
		String date = sdf.format(cal.getTime());
		this.setDate(date);
	}

}
