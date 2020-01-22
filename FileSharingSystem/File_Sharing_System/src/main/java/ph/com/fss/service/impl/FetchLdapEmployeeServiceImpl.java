package ph.com.fss.service.impl;

import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.stereotype.Service;

import ph.com.fss.dao.EmployeeDao;
import ph.com.fss.dao.LdapEmployeeDao;
import ph.com.fss.entity.fss.Employee;
import ph.com.fss.exception.LdapBatchUpdateFailed;
import ph.com.fss.service.FetchLdapEmployeeService;

@Service
public class FetchLdapEmployeeServiceImpl implements FetchLdapEmployeeService {
	
	@Autowired
	private JpaTransactionManager fssTransactionManager;
	
	@Autowired
	private LdapTemplate ldapTemplate;
	
	@Autowired
	private LdapEmployeeDao ldapEmployeeDao;
	
	@Autowired
	private EmployeeDao empDao;
	
	@Override
	public List<Employee> getAllEmployee() {
		return ldapEmployeeDao.selectAllEmployee(ldapTemplate);
	}

	@Override
	public int updateEmployeeList() throws LdapBatchUpdateFailed {
		
		int i = 0;
		
		EntityManager em = null;
		try{
			
			List<Employee> employees = ldapEmployeeDao.selectAllEmployee(ldapTemplate);
			
			em = fssTransactionManager.getEntityManagerFactory().createEntityManager();
			
			em.getTransaction().begin();
			
			Iterator<Employee> iterator = employees.iterator();
			
			Session session = em.unwrap(Session.class);
			
			empDao.deleteAllEmployee(session);
			
			while(iterator.hasNext()) {
			    
				empDao.insertEmployee(session , iterator.next() );
			    
			    if ( ( (i++) % 20 ) == 0 ) { // 20 unit per batch
			        //flush a batch of inserts and release memory:
			        session.flush();
			        session.clear();
			    }
			}
			
			em.getTransaction().commit();
			
		} catch(Exception e){
			
			em.getTransaction().rollback();
			throw new LdapBatchUpdateFailed(e.getMessage());
			
		}finally{
			
			if(em.isOpen()){
				em.close();
			}
		}
		return i;
	}

}
