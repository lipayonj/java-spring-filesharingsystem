package ph.com.fss.dao;

import javax.persistence.EntityManager;

import ph.com.fss.entity.fss.File;

public interface FileDao {

	public void insertFile(File pAttachmentLink, EntityManager em);
}
