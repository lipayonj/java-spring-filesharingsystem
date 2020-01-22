package ph.com.fss.service;

import java.util.List;
import java.util.Map;

import ph.com.fss.entity.fss.Employee;
import ph.com.fss.model.LdapUserModel;

public interface LoginService {
	
	public List<String> selectEmailList();
	
	public List<Map<String, String>> selectEmployeeList();
	
	public boolean loginLdapAuthentication(LdapUserModel ldapUserModel);
}
