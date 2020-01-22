package ph.com.fss.service;

import java.util.List;

import ph.com.fss.exception.DBException;


public interface FileSharingService  {
	public boolean sendEmail(List<String> pToRecipientsList, List<String> pCCRecipientsList
			, String pstrSubject, String pstrBody, String pstrFrom, Integer intSenderId) throws DBException;
	
	public List<String> getPlOrPm(String pstrEmailAddress);
}
