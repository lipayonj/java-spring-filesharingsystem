package ph.com.fss.util;

import java.util.List;
import java.util.Properties;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.PasswordAuthentication;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import ph.com.fss.exception.MessagingException;

@Component
public class EmailSender {
	//Reminder: not force
	private final String strDummySender = "fssojt@gmail.com";  
	private final String strPassword = "filesharingsystem";     
																
   public boolean sendEmail(List<String> pToRecipientsList,
		List<String> pCCRecipientsList, String pstrSubject,
		String pstrBody, String pstrFrom, List<String> pAttachmentsList) {

	   Properties properties = new Properties();
	   properties.put("mail.smtp.auth", "true");
	   properties.put("mail.smtp.starttls.enable", "true"); 
	   properties.put("mail.smtp.host", "smtp.gmail.com");
	   properties.put("mail.smtp.port", "587");
	   
	   Session session = Session.getInstance(properties,
	      new javax.mail.Authenticator() {
	         protected PasswordAuthentication getPasswordAuthentication() {
	            return new PasswordAuthentication(strDummySender, strPassword);
	         }
	      });

	   try {
		   MimeMessage message = new MimeMessage(session);
		   Multipart multipart = new MimeMultipart();
		   System.out.println("from: " + pstrFrom);
		   message.setFrom(new InternetAddress(strDummySender, pstrFrom));
		   System.out.println("Subject: " + pstrSubject);
		   message.setSubject(pstrSubject, "utf-8");
		   System.out.println("TO:");
		   for (String strTo: pToRecipientsList) {
			   System.out.println(strTo);
			   if (!strTo.equals("")) {
				   InternetAddress to = new InternetAddress(strTo);
				   message.addRecipient(Message.RecipientType.TO, to);
			   }  
		   }
		   System.out.println("CC");
		   for (String strCC: pCCRecipientsList) {
			   System.out.println(strCC);
			   if (!strCC.equals("")) {
				   message.addRecipient(Message.RecipientType.CC, new InternetAddress(strCC));
			   }
		   }
		   
		   BodyPart messageBodyPart = new MimeBodyPart();
		   System.out.println("Body: " + pstrBody);
		   messageBodyPart.setContent(pstrBody, "text/plain; charset=UTF-8");
		   multipart.addBodyPart(messageBodyPart);
	       BodyPart fileLink = new MimeBodyPart();
	       System.out.println("Link: ");
	       for (String strLink: pAttachmentsList) {
	    	   String strAttachmentLink ="<a href =" + strLink + ">" + strLink + "</a>";
	    	   System.out.println(strAttachmentLink);
	    	   fileLink.setContent(strAttachmentLink, "text/html");	
	    	   multipart.addBodyPart(fileLink);
	       }
	       
		   message.setContent(multipart, "text/plain; charset=UTF-8");
		   Transport.send(message);
           System.out.println("Sent message successfully....");
           return true;
      } catch (Exception e) {
    	  //throw new MessagingException(e.getMessage());
        e.printStackTrace();
        return false;
      }
   }
	   
}