package ph.com.fss.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginViewController {
	
	//Spring Security see this :
  	@RequestMapping(value = "/login", method = RequestMethod.GET)
  	public ModelAndView login(
  		@RequestParam(value = "error", required = false) String error,
  		@RequestParam(value = "logout", required = false) String logout
  		) {
  		
  		ModelAndView mv = new ModelAndView();
  		
  		mv.addObject("hello", "Login Failed");
  		
  		System.out.println("Login Failed");
  		
  		if (error != null) {
  			mv.addObject("error", "Invalid username and password!");
  		}
  		
  		if (logout != null) {
  			mv.addObject("msg", "You've been logged out successfully.");
  		}
  		
  		mv.setViewName("/login");
  		
  		return mv;
  	}
	
}
