package ph.com.fss.dao;

import javax.persistence.EntityManager;

import ph.com.fss.entity.fss.Recipient;

public interface RecipientDao {

	public void insertRecipient(Recipient pRecipient, EntityManager em);
}
