package ph.com.fss.service;

import java.util.List;

import org.springframework.ldap.core.LdapTemplate;

import ph.com.fss.entity.fss.Employee;
import ph.com.fss.exception.LdapBatchUpdateFailed;

public interface FetchLdapEmployeeService {
	
	public List<Employee> getAllEmployee();
	public int updateEmployeeList() throws LdapBatchUpdateFailed;
	
}
