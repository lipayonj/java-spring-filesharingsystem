package ph.com.fss.dao;

import java.util.List;

import javax.persistence.EntityManager;

public interface ProjectMemberDao {
	public List<String> selectAllPlOrPm(String pstEmailAddress, EntityManager em);
}

