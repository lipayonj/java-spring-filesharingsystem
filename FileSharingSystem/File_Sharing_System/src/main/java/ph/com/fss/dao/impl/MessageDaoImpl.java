package ph.com.fss.dao.impl;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import ph.com.fss.dao.MessageDao;
import ph.com.fss.entity.fss.Message;

@Repository
public class MessageDaoImpl implements MessageDao {

	@Override
	public Integer insertMessage(Message pMessage, EntityManager em) {
		em.persist(pMessage);
		em.flush();
		return pMessage.getMessageID();

	}
}
