package ph.com.fss.service.impl;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.stereotype.Service;

import ph.com.fss.bootstrap.RootConfig;
import ph.com.fss.dao.LoginDao;
import ph.com.fss.entity.fss.Employee;
import ph.com.fss.model.LdapUserModel;
import ph.com.fss.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private LoginDao loginDao;
	
	@Autowired
	private JpaTransactionManager fssTransactionManager;
	
	@Autowired
	private LdapTemplate ldapTemplate;
	
	@Override
	public List<String> selectEmailList() {

		EntityManager em = fssTransactionManager.getEntityManagerFactory().createEntityManager(); 
		List<String> list = loginDao.selectAllEmail(em);
		return list;
	}

	@Override
	public boolean loginLdapAuthentication(LdapUserModel ldapUserModel) { 
		boolean authenticated = ldapTemplate.authenticate("", "(mail=" + ldapUserModel.getUsername() + ")", ldapUserModel.getPassword());
		System.out.println(authenticated);
		return authenticated;
	}

	@Override
	public List<Map<String, String>> selectEmployeeList() {
		EntityManager em = fssTransactionManager.getEntityManagerFactory().createEntityManager(); 
		List<Map<String, String>> list = loginDao.selectAllEmployee(em);
		return list;
	}
	
	

}
