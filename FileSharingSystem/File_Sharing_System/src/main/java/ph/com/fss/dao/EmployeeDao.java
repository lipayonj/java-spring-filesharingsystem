package ph.com.fss.dao;

import javax.persistence.EntityManager;

import org.hibernate.Session;

import ph.com.fss.entity.fss.Employee;


public interface EmployeeDao {
 
	public Employee selectEmployee(EntityManager em, String pstrEmailAddress);
	public void insertEmployee(Session s, Employee employee); 
	public void deleteAllEmployee(Session s); 
	public Integer selectEmployee(String pstrEmailAddress, EntityManager em);
}
