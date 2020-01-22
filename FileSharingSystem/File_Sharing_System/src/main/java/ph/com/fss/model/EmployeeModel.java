package ph.com.fss.model;

public class EmployeeModel {
	
	private Integer employeeId;
	
	private String employeeName;
	
	private String employeeEmail;

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getEmployeeEmail() {
		return employeeEmail;
	}

	public void setEmployeeEmail(String employeeEmail) {
		this.employeeEmail = employeeEmail;
	}

	@Override
	public String toString() {
		return "EmployeeModel [employeeId=" + employeeId + ", employeeName="
				+ employeeName + ", employeeEmail=" + employeeEmail + "]";
	}
	
}
