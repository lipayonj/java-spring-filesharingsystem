package ph.com.fss.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import ph.com.fss.dao.ProjectMemberDao;

@Repository
public class ProjectMemberDaoImpl implements ProjectMemberDao {


	@Override
	public List<String> selectAllPlOrPm(String pstrEmailAddress, EntityManager em) {
		Session session = em.unwrap(Session.class);
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT DISTINCT e.email FROM ProjectMember pm ");
		sb.append("INNER JOIN pm.employee e WHERE pm.projectId ");
		sb.append("IN (SELECT p.projectId FROM ProjectMember pm INNER JOIN ");
		sb.append("pm.project p WHERE pm.employeeId IN (SELECT e.id FROM Employee e WHERE e.email = :employeeAdd)  AND p.status IN (1,2)) ");
		sb.append("AND pm.role IN (2,4) AND e.email <> :employeeAdd");
		Query query = session.createQuery(sb.toString());
		query.setParameter("employeeAdd", pstrEmailAddress);
		List<String> projectheads = query.list();
		
		return projectheads;
	}

}
