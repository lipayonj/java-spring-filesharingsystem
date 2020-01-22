package ph.com.fss.entity.pms;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="employee")
public class Employee implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue
	@Column(name="EMPLOYEE_ID")
	private Integer employeeId;
	
	@Column(name="ACCESS_RIGHT_ID")
	private boolean accessRightId;
	
	@Column(name="FIRST_NAME")
	private String firstName;
	
	@Column(name="LAST_NAME")
	private String lastName;
	
	@Column(name="EMAIL")
	private String email;
	
	@Column(name="PASSWORD")
	private String password;
	
	@Column(name="RESIGNED")
	private boolean resigned;
	
	@Column(name="CREATED_DATE")
	private Date createdDate;
	
	@Column(name="CREATED_BY")
	private Integer createdy;
	
	@Column(name="MODIFIED_DATE")
	private Date modifiedDate;
	
	@Column(name="MODIFIED_BY")
	private Integer modifiedBy;
	
	@OneToMany
	@JoinColumn(name="EMPLOYEE_ID", referencedColumnName="EMPLOYEE_ID", insertable=false, updatable=false)
	private	List<ProjectMember> projectMembers;
	
	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public boolean isAccessRightId() {
		return accessRightId;
	}

	public void setAccessRightId(boolean accessRightId) {
		this.accessRightId = accessRightId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isResigned() {
		return resigned;
	}

	public void setResigned(boolean resigned) {
		this.resigned = resigned;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Integer getCreatedy() {
		return createdy;
	}

	public void setCreatedy(Integer createdy) {
		this.createdy = createdy;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public Integer getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(Integer modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public List<ProjectMember> getProjectMembers() {
		return projectMembers;
	}

	public void setProjectMembers(List<ProjectMember> projectMembers) {
		this.projectMembers = projectMembers;
	}


}
