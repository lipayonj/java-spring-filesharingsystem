package ph.com.fss.controller.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ph.com.fss.entity.fss.Employee;
import ph.com.fss.exception.DBException;
import ph.com.fss.model.ResponseModel;
import ph.com.fss.service.FileSharingService;
import ph.com.fss.service.LoginService;

@Controller
public class EmailAPIController {

	@Autowired
	private FileSharingService fssService;
	
	@Autowired
	private LoginService loginService;
	
	@RequestMapping(value= "/send-email", method=RequestMethod.POST)
	@ResponseBody
	public ResponseModel sendEmail(@RequestParam(value="strToRecipients") String pstrToRecipients,
			@RequestParam(value="strCCRecipients") String pstrCCRecipients,
			@RequestParam(value="strSubject") String pstrSubject,
			@RequestParam(value="strBody") String pstrBody,
			@RequestParam(value="strAttachmentId") String pstrAttachmentId) {
		
		System.out.println("----------------------------");
		System.out.println(pstrToRecipients);
		System.out.println(pstrCCRecipients);
		System.out.println(pstrSubject);   
		System.out.println(pstrBody);
		System.out.println(pstrAttachmentId);
		System.out.println("----------------------------");

		ResponseModel response = new ResponseModel();
		Integer intSenderId = 972;//to get from session
		String strFrom = "";
		boolean isSuccessful = false;
		try {
			String[] strTempFromArray = pstrAttachmentId.split("=");
			if (strTempFromArray.length > 1) {
				if (isValidEmailAddress(strTempFromArray[0].trim())) {
					strFrom = strTempFromArray[0].trim();
				} else {
					response.setSuccessFlag(false);
					response.setMessage("Invalid email address.");
					return response;
				}
			} else {
				response.setSuccessFlag(false);
				response.setMessage("Invalid email address.");
				return response;
			}

			if (pstrToRecipients ==  null || pstrToRecipients.equals("")) {
				response.setSuccessFlag(false);
				response.setMessage("To text field is required.");
				return response;
			} else if (pstrSubject == null || pstrSubject.equals("")) {
				response.setSuccessFlag(false);
				response.setMessage("Subject is required.");
				return response;
			} else {
				List<String> toRecipientsList = new ArrayList<String>(Arrays.asList(pstrToRecipients.replace(" ", "").split(",")));
				List<String> CCRecipientsList = new ArrayList<String>(Arrays.asList(pstrCCRecipients.replace(" ", "").split(",")));
				for (String strRecipient : toRecipientsList) {
					if (!strRecipient.equals("") && !isValidEmailAddress(strRecipient)) {
						response.setSuccessFlag(false);
						response.setMessage("Invalid email address.");
						return response;
					}
					if (CCRecipientsList.contains(strRecipient)) {
						response.setSuccessFlag(false);
						response.setMessage("Duplicate email address.");
						return response;
					}
				}
				for (String strRecipient : CCRecipientsList) {
					if (!strRecipient.equals("") && !isValidEmailAddress(strRecipient)) {
						response.setSuccessFlag(false);
						response.setMessage("Invalid email address.");
						return response;
					}
				}
				
				List<String> autoCCList = new ArrayList<String>();
				autoCCList = fssService.getPlOrPm(strFrom);
				System.out.println("Result from autocc call:");
				for (String strCCEmailAdress : autoCCList) {
					System.out.println(strCCEmailAdress);
					//if (!CCRecipientsList.contains(strCCEmailAdress) && !toRecipientsList.contains(strCCEmailAdress)) {
					//	CCRecipientsList.add(strCCEmailAdress);
					//}
				}
				isSuccessful = fssService.sendEmail(toRecipientsList, CCRecipientsList, pstrSubject, pstrBody, strFrom, intSenderId);
			}	
		} catch (DBException deb) {
			response.setSuccessFlag(false);
			response.setMessage(deb.getMessage());
			return response;
		} catch (Exception e) {
			response.setSuccessFlag(false);
			response.setMessage("Error.");
			return response;
		}
		
		if (isSuccessful) {
			response.setSuccessFlag(true);
			response.setMessage("Message sent.");
		} else {
			response.setSuccessFlag(false);
			response.setMessage("Message not sent.");
		}
		return response;
	}
	
	public boolean isValidEmailAddress(String pstrEmailAdress) {
		
		Pattern pattern = Pattern.compile("[a-zA-Z0-9_.]*@[a-zA-Z0-9_-]*.[a-zA-Z]*");
		Matcher matcher = pattern.matcher(pstrEmailAdress);
		return matcher.matches();
	}
	
	
	@RequestMapping(value= "/getEmployees", method=RequestMethod.POST)
	@ResponseBody
	public List<Map<String, String>> selectEmployeeList(){ 
		List<Map<String, String>> list = loginService.selectEmployeeList(); 
		return list;
	}
}
