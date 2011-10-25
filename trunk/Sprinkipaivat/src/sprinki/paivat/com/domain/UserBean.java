package sprinki.paivat.com.domain;

import javax.persistence.*;
import javax.validation.constraints.*;


@SuppressWarnings("serial")
@Entity
@Table(name = "userbase")
public class UserBean implements java.io.Serializable {

	@Id
	@Column(name = "id")
	@GeneratedValue()
	@NotNull
	private int id;
	
	@Column(name = "username", unique=true)
	@Size(min=3, max=255)
	@Basic(optional=false)
	@NotNull
	private String username;
	
	@Column(name = "lastlog")
	private int lastlog;
	
	@Column(name = "dob")
	@Basic(optional=false)
	@Min(10)
	@Max(12)
	@NotNull
	private Integer dateofbirth;
	
	@Column(name = "email")
	@Pattern(regexp="^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")
	@Size(min=3, max=255)
	@Basic(optional=false)
	@NotNull
	private String email;
	
	@Column(name = "password")
	@Size(min=3, max=255)
	@Basic(optional=false)
	private String password;
	
	@Transient
	@Basic(optional=false)
	private String formPassword1;
	@Transient
	@Basic(optional=false)
	private String formPassword2;
	
	public UserBean() {
		
	}

	public int getUserid() {
		return id;
	}

	public void setUserid(int userid) {
		this.id = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String name) {
		this.username = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getDateofbirth() {
		return dateofbirth;
	}

	public void setDateofbirth(Integer dateofbirth) {
		this.dateofbirth = dateofbirth;
	}
	public int getLastlog() {
		return lastlog;
	}

	public void setLastlog(int lastlog) {
		this.lastlog = lastlog;
	}
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFormPassword1() {
		return formPassword1;
	}

	public void setFormPassword1(String formPassword1) {
		this.formPassword1 = formPassword1;
	}

	public String getFormPassword2() {
		return formPassword2;
	}

	public void setFormPassword2(String formPassword2) {
		this.formPassword2 = formPassword2;
	}
	
}
