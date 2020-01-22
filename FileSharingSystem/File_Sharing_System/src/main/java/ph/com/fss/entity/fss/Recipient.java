package ph.com.fss.entity.fss;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="RECIPIENT")
public class Recipient implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="MESSAGE_ID")
	private Integer messageID;
	
	@Id
	@Column(name="EMPLOYEE_ID")
	private Integer employeeID;
	
	@Column(name="TYPE")
	private String type;
	
	@OneToOne
	@JoinColumn(name="MESSAGE_ID", referencedColumnName="ID", insertable=false, updatable=false)
	private Message message;
	
	@OneToOne
	@JoinColumn(name="EMPLOYEE_ID", referencedColumnName="ID", insertable=false, updatable=false)
	private Employee employee;
	
	public Integer getMessageID() {
		return messageID;
	}

	public void setMessageID(Integer messageID) {
		this.messageID = messageID;
	}

	public Integer getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(Integer employeeID) {
		this.employeeID = employeeID;
	}

	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
}
