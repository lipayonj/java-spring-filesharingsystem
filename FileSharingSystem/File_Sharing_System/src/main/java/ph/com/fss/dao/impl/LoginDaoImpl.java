package ph.com.fss.dao.impl;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager; 

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import ph.com.fss.dao.LoginDao;
import ph.com.fss.entity.fss.Employee;

@Repository
public class LoginDaoImpl implements LoginDao {

	@Override
	public List<String> selectAllEmail(EntityManager em) {
		Session session = em.unwrap(Session.class);
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT e.email ");
		sb.append("FROM Employee as e ");
		sb.append("WHERE e.email != NULL");
		Query query = session.createQuery(sb.toString());
		List<String> list = query.list(); 
		return list;
	}

	@Override
	public List<Map<String, String>> selectAllEmployee(EntityManager em) {
		Session session = em.unwrap(Session.class);
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT NEW MAP(e.email as email, e.name as name) ");
		sb.append("FROM Employee as e WHERE email != NULL ORDER BY TRIM(name)");
		Query query = session.createQuery(sb.toString());
		List<Map<String, String>> list = query.list(); 
		return list;
	}

}
