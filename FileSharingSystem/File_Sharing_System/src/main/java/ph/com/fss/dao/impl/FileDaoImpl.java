
package ph.com.fss.dao.impl;

import javax.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import ph.com.fss.dao.FileDao;
import ph.com.fss.entity.fss.File;

@Repository
public class FileDaoImpl implements FileDao {

	@Override
	public void insertFile(File pAttachmentLink, EntityManager em) {
		em.persist(pAttachmentLink);
		
	}
}
