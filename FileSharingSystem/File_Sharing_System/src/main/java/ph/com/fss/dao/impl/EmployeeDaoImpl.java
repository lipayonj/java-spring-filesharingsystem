package ph.com.fss.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

/*
@author Jonald Misutla Cruz
*/
import ph.com.fss.dao.EmployeeDao;
import ph.com.fss.entity.fss.Employee;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {
	
	@Override
	public void insertEmployee(Session s, Employee employee) {
		s.save(employee);
	}

	@Override
	public void deleteAllEmployee(Session s) {
		s.createQuery("DELETE FROM Employee").executeUpdate();
	}

	@Override
	public Employee selectEmployee(EntityManager em, String pstrEmailAddress) {
		Session session = em.unwrap(Session.class);
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT e.id FROM Employee e Where email = :emailAddress ");
		Query query = session.createQuery(sb.toString());
		query.setParameter("emailAddress", pstrEmailAddress);
		Employee employee = (Employee) query.list().iterator().next();
		return employee;
	}

	@Override
	public Integer selectEmployee(String pstrEmailAddress, EntityManager em) {
		Session session = em.unwrap(Session.class);
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT e.id FROM Employee e Where email = :emailAddress ");
		Query query = session.createQuery(sb.toString());
		query.setParameter("emailAddress", pstrEmailAddress);
		
		List<Integer> idList = query.list();
		
		if (idList.size() != 0) {
			return idList.get(0);
		}
			return null;
		
	}
	
}
