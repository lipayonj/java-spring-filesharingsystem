package ph.com.fss.dao;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import ph.com.fss.entity.fss.Employee;

public interface LoginDao { 
	
	public List<String> selectAllEmail(EntityManager em);
	
	public List<Map<String, String>> selectAllEmployee(EntityManager em);
}
