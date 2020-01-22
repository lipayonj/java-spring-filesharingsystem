package ph.com.fss.dao;

import java.util.List;

import org.springframework.ldap.core.LdapTemplate;

import ph.com.fss.entity.fss.Employee;

public interface LdapEmployeeDao {
	public List<Employee> selectAllEmployee(LdapTemplate ldapTemplate);
}
