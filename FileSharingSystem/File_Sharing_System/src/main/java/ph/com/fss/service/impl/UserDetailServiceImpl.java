package ph.com.fss.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ph.com.fss.dao.EmployeeDao;
import ph.com.fss.entity.fss.Employee;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

	@Autowired
	private JpaTransactionManager fssTransactionManager;
	
	@Autowired
	private EmployeeDao employeeDao;
	
	@Override
	public UserDetails loadUserByUsername(String username)
        throws UsernameNotFoundException {
		
		EntityManager em = fssTransactionManager.getEntityManagerFactory().createEntityManager();
		
	    Employee account= employeeDao.selectEmployee(em, username);
	    
	    System.out.println("Email:  "+account.getEmail()+ " ");
	    
	    if(account == null) {
	    	throw new UsernameNotFoundException("No such user: " + username);
	    }
	    
	    List<SimpleGrantedAuthority> auth = new ArrayList<SimpleGrantedAuthority>();
	    
	    auth.add(new SimpleGrantedAuthority("ROLE_USER"));
	    UserDetails user = new User(username, "", auth);
	    
	    return user;
    }
}
