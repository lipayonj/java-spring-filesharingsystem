package ph.com.fss.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.stereotype.Service;

import ph.com.fss.dao.EmployeeDao;
import ph.com.fss.dao.FileDao;
import ph.com.fss.dao.MessageDao;
import ph.com.fss.dao.ProjectMemberDao;
import ph.com.fss.dao.RecipientDao;
import ph.com.fss.entity.fss.File;
import ph.com.fss.entity.fss.Message;
import ph.com.fss.entity.fss.Recipient;
import ph.com.fss.exception.DBException;
import ph.com.fss.service.FileSharingService;
import ph.com.fss.util.EmailSender;

@Service
public class FileSharingServiceImpl implements FileSharingService {
	
	@Autowired
	private JpaTransactionManager fssTransactionManager;
	
	@Autowired
	private JpaTransactionManager pmsTransactionManager;
	
	@Autowired
	private EmailSender emailSender;

	@Autowired
	private FileDao fileDao;
	
	@Autowired 
	private MessageDao messageDao;
	
	@Autowired
	private RecipientDao recipientDao;
	
	@Autowired
	private ProjectMemberDao projectMemberDao;
	
	@Autowired
	private EmployeeDao employeeDao;
	
	
	@Override
	public boolean sendEmail(List<String> pToRecipientsList,
			List<String> pCCRecipientsList, String pstrSubject,

			String pstrBody, String pstrFrom, Integer intSenderId) throws DBException {
		
		List<String> fileAttachmentsList = new ArrayList<String>();
		//fileAttachmentsList = fileSharingService.move(pstrAttachmentId)
		boolean isSent = emailSender.sendEmail(pToRecipientsList, pCCRecipientsList, pstrSubject, pstrBody,  pstrFrom,  fileAttachmentsList); 
		if (isSent) {
			EntityManager em = fssTransactionManager.getEntityManagerFactory().createEntityManager();
			try {
				DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				Date date = new Date();
				Integer intMessageId;
				Message message = new Message(); 
				message.setBody(pstrBody);
				message.setDateCreated(dateFormat.format(date));
				message.setSubject(pstrSubject);
				message.setSenderID(intSenderId.toString());
				
				em.getTransaction().begin();
					intMessageId = messageDao.insertMessage(message, em);
					System.out.println("Message inserted");
					Recipient recipient;
					for (String strEmailAddress : pToRecipientsList) {
						if (!strEmailAddress.equals("")) {
							Integer employeeId = employeeDao.selectEmployee(strEmailAddress, em);
							recipient = new Recipient(); 
							recipient.setEmployeeID(employeeId);
							recipient.setMessageID(intMessageId);
							recipient.setType("To");
							recipientDao.insertRecipient(recipient, em);
						}

					}
					System.out.println("To inserted");
					for (String strEmailAddress: pCCRecipientsList) {
						if (!strEmailAddress.equals("")) {
							Integer employeeId = employeeDao.selectEmployee(strEmailAddress, em);
							recipient = new Recipient(); 
							recipient.setEmployeeID(employeeId);
							recipient.setMessageID(intMessageId);
							recipient.setType("CC");
							recipientDao.insertRecipient(recipient, em);
						}

					}
					System.out.println("CC inserted");
					for (String strAttachmentLink : fileAttachmentsList) {
						File file = new File();
						file.setMessageID(intMessageId.toString());
						file.setName(strAttachmentLink);
						fileDao.insertFile(file, em);
					}
					System.out.println("Link inserted");
				em.getTransaction().commit();
			} catch(Exception e) {
				
				em.getTransaction().rollback();

				e.printStackTrace();
				//throw new DBException("DB Error");	


				
			} finally {
				if (em != null && em.isOpen()) {
					em.close();
				}
			}
		}
		return isSent;
	}
	
	@Override
	public List<String> getPlOrPm(String pstrEmailAddress) {
		EntityManager em = pmsTransactionManager.getEntityManagerFactory().createEntityManager();
		List<String> allPlOrPms = projectMemberDao.selectAllPlOrPm(pstrEmailAddress, em);
		return allPlOrPms;
	}
}


