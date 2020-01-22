package ph.com.fss.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.stereotype.Repository;

import ph.com.fss.dao.LdapEmployeeDao;
import ph.com.fss.entity.fss.Employee;
import ph.com.fss.util.EmployeeAttributesMapper;

@Repository
public class LdapEmployeeDaoImpl implements LdapEmployeeDao {
	
	@Override
	public List<Employee> selectAllEmployee(LdapTemplate ldapTemplate) {
		return ldapTemplate.search("", "(objectClass=person)", new EmployeeAttributesMapper());
	}

}
