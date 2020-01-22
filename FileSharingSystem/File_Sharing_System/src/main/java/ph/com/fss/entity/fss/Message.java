package ph.com.fss.entity.fss;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="MESSAGE")
public class Message implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue
	@Column(name="ID")
	private Integer messageID;
	
	@Column(name="SUBJECT")
	private String subject;
	
	@Column(name="BODY")
	private String body;
	
	@Column(name="DATE_CREATED")
	private String dateCreated;
	
	@Column(name="SENDER_ID")
	private String senderID;
	
	@OneToMany
	@JoinColumn(name="MESSAGE_ID", referencedColumnName="ID", insertable=false, updatable=false)
	private List<Recipient> recipients;
	
	@OneToMany
	@JoinColumn(name="MESSAGE_ID", referencedColumnName="ID", insertable=false, updatable=false)
	private List<File> files;
	
	public Integer getMessageID() {
		return messageID;
	}

	public void setMessageID(Integer messageID) {
		this.messageID = messageID;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}

	public String getSenderID() {
		return senderID;
	}

	public void setSenderID(String senderID) {
		this.senderID = senderID;
	}

	public List<Recipient> getRecipients() {
		return recipients;
	}

	public void setRecipients(List<Recipient> recipients) {
		this.recipients = recipients;
	}

	public List<File> getFiles() {
		return files;
	}

	public void setFiles(List<File> files) {
		this.files = files;
	}
}
