package ph.com.fss.entity.pms;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="project_member")
public class ProjectMember implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue
	@Column(name="PROJECT_ID")
	private Integer projectId;
	
	@Id @GeneratedValue
	@Column(name="EMPLOYEE_ID")
	private Integer employeeId;
	
	@Column(name="ROLE")
	private boolean role;
	
	@Column(name="PERCENTAGE_EFFORT")
	private int percentEffot;
	
	@Column(name="START_DATE")
	private Date startDate;
	
	@Column(name="END_DATE")
	private Date endDate;
	
	@Column(name="DELETED_FLAG")
	private boolean deletedFlag;
	
	@Column(name="CREATED_DATE")
	private Date createdDate;
	
	@Column(name="CREATED_BY")
	private Integer createdBy;
	
	@Column(name="MODIFIED_DATE")
	private Date modifiedDate;
	
	@Column(name="MODIFIED_BY")
	private Integer modifiedBy;
	
	@OneToOne
	@JoinColumn(name="EMPLOYEE_ID", referencedColumnName="EMPLOYEE_ID", insertable=false, updatable=false)
	private Employee employee;
	
	@OneToOne
	@JoinColumn(name="PROJECT_ID", referencedColumnName="PROJECT_ID", insertable=false, updatable=false)
	private Project project;
	
	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public boolean isRole() {
		return role;
	}

	public void setRole(boolean role) {
		this.role = role;
	}

	public int getPercentEffot() {
		return percentEffot;
	}

	public void setPercentEffot(int percentEffot) {
		this.percentEffot = percentEffot;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public boolean isDeletedFlag() {
		return deletedFlag;
	}

	public void setDeletedFlag(boolean deletedFlag) {
		this.deletedFlag = deletedFlag;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

}
