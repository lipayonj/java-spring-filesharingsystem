package ph.com.fss.controller.api;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ph.com.fss.model.LdapUserModel;
import ph.com.fss.service.LoginService; 

@Controller
public class LoginAPIController {
	
	@Autowired
	private LoginService loginService; 
	
	@RequestMapping(value="/loadList", method=RequestMethod.POST) 
	@ResponseBody
	public List<String> selectEmailList(){ 
		List<String> list = loginService.selectEmailList(); 
		return list;
	}
	
	@RequestMapping(value= "/userAuthenticate", method=RequestMethod.GET)  
	public String userAuthenticate(@RequestParam String username ,@RequestParam String pwd, HttpServletRequest request){

		HttpSession session = request.getSession(false);
		
		LdapUserModel ldapUserModel = new LdapUserModel();
		
		ldapUserModel.setUsername(username);
		ldapUserModel.setPassword(pwd);
		
		boolean test = loginService.loginLdapAuthentication(ldapUserModel);  
		
		if(test){  
				session.invalidate(); 
				request.setAttribute("User", 1);
				request.setAttribute("Message", "Successfully log in");
				return "/email";
		}else{ 
			request.setAttribute("User", 2);
			request.setAttribute("Message", "Invalid Credentials"); 
			return "/login";
		} 
	}
	
	@RequestMapping(value= "/logout", method=RequestMethod.POST) 
	public String logout(HttpServletRequest request){ 
		System.out.println("logout");
		request.getSession().invalidate();
		return "/login";
	} 
}
