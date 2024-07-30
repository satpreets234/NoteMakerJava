package Notes;

import java.util.Date;

import Users.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class Note {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(length = 100,nullable = false)
	private String title;
	@Column(nullable = false)
	private String content;
	@Temporal(TemporalType.DATE)
	private Date createdOn;
	@ManyToOne		
	private User user;
	
	
	public Note() {
		super();
		// TODO Auto-generated constructor stub
	}


	public int getId() {
		return id;
	}
	public User getUser() {
		return user;
	}

	public void setId(int id) {
		this.id = id;
	}
	public void setUser(User user) {
		this.user = user;
	}

	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public Date getCreatedOn() {
		return createdOn;
	}


	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}


	public Note(int id, String title, String content, Date createdOn, User user) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.createdOn = createdOn;
		this.user =user;
	}
	
	
}
