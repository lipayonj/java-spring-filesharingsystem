package ph.com.fss.dao;

import javax.persistence.EntityManager;

import ph.com.fss.entity.fss.Message;


public interface MessageDao {
	
	public Integer insertMessage(Message pMessage, EntityManager em);
}
