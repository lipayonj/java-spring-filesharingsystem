package ph.com.fss.controller.view;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Example controller class that handles request for the application root.
 * 
 *
 */
@Controller

public class HomeController {
	
    @RequestMapping( value="/", method=RequestMethod.GET)
    public String loadMainMenu(HttpServletRequest request, HttpServletResponse response, ModelMap map){
		return "/login";
    }
    
  	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
  	public String welcome(HttpServletRequest request) {
  		return "/index";
  	}
  	
  	
}
