package ph.com.fss.entity.fss;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="FILE")
public class File implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id  @GeneratedValue
	@Column(name="ID")
	private Integer id;
	
	@Column(name="FULL_PATH")
	private String name;
	
	@Column(name="MESSAGE_ID")
	private String messageID;
	
	@OneToOne
	@JoinColumn(name="MESSAGE_ID", referencedColumnName="ID", insertable=false, updatable=false)
	private Message message;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMessageID() {
		return messageID;
	}

	public void setMessageID(String messageID) {
		this.messageID = messageID;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}
	
}
