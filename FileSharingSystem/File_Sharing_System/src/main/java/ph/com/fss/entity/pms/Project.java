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
@Table(name="project")
public class Project implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue
	@Column(name="PROJECT_ID")
	private Integer projectId;
	
	@Column(name="PROJECT_NAME")
	private String projectName;
	
	@Column(name="SOW_NUMBER")
	private String sowNo;
	
	@Column(name="CLIENT")
	private String client;
	
	@Column(name="TECHNOLOGIES")
	private String technologies;
	
	@Column(name="REMARKS")
	private String remarks;
	
	@Column(name="START_DATE")
	private Date startDate;
	
	@Column(name="END_DATE")
	private Date endDate;
	
	@Column(name="PROGRESS")
	private boolean progress;
	
	@Column(name="STATUS")
	private boolean status;
	
	@Column(name="PHASES_APPLICABLE")
	private String phasesApplicable;
	
	@Column(name="DELETED_FLAG")
	private boolean deletedFlag;
	
	@Column(name="DATE_CREATED")
	private Date dateCreated;
	
	@Column(name="CREATED_BY")
	private Integer createdBy;
	
	@Column(name="DATE_MODIFIED")
	private Date dateModified;
	
	@Column(name="MODIFIED_BY")
	private Integer modifiedBy;
	
	@OneToMany
	@JoinColumn(name="PROJECT_ID", referencedColumnName="PROJECT_ID", insertable=false, updatable=false)
	private	List<ProjectMember> projectMembers;

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getSowNo() {
		return sowNo;
	}

	public void setSowNo(String sowNo) {
		this.sowNo = sowNo;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public String getTechnologies() {
		return technologies;
	}

	public void setTechnologies(String technologies) {
		this.technologies = technologies;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
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

	public boolean isProgress() {
		return progress;
	}

	public void setProgress(boolean progress) {
		this.progress = progress;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getPhaseApp() {
		return phasesApplicable;
	}

	public void setPhaseApp(String phaseApp) {
		this.phasesApplicable = phaseApp;
	}

	public boolean isDeletedFlag() {
		return deletedFlag;
	}

	public void setDeletedFlag(boolean deletedFlag) {
		this.deletedFlag = deletedFlag;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Date getDateModified() {
		return dateModified;
	}

	public void setDateModified(Date dateModified) {
		this.dateModified = dateModified;
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
