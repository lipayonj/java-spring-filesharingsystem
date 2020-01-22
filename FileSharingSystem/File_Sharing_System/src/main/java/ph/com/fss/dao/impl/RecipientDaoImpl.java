package ph.com.fss.dao.impl;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import ph.com.fss.dao.RecipientDao;
import ph.com.fss.entity.fss.Recipient;

@Repository
public class RecipientDaoImpl implements RecipientDao {

	@Override
	public void insertRecipient(Recipient pRecipient, EntityManager em) {
		em.persist(pRecipient);
	}

}
