package ph.com.fss.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.DirContextAdapter;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.ldap.userdetails.UserDetailsContextMapper;
import org.springframework.stereotype.Service;

import ph.com.fss.dao.EmployeeDao;
import ph.com.fss.entity.fss.Employee;

@Service
public class EmployeeUserDetailsContextMapper implements UserDetailsContextMapper {
	
	@Autowired
	private JpaTransactionManager fssTransactionManager;
	
	@Autowired
	private EmployeeDao employeeDao;
	
	@Override
	public UserDetails mapUserFromContext(DirContextOperations ctx,
			String username, Collection<? extends GrantedAuthority> authorities) {
				
		EntityManager em = fssTransactionManager.getEntityManagerFactory().createEntityManager();
		
	    Employee account= employeeDao.selectEmployee(em, username);
	    
	    if(account == null) {
	    	throw new UsernameNotFoundException("No such user: " + username);
	    }
	    
	    List<SimpleGrantedAuthority> auth = new ArrayList<SimpleGrantedAuthority>();
	    
	    auth.add(new SimpleGrantedAuthority("ROLE_USER"));
	    UserDetails user = new User(username, "", auth);
	    
	    return user;
	}

	@Override
	public void mapUserToContext(UserDetails user, DirContextAdapter ctx) {
		// TODO Auto-generated method stub
		
	}
}
