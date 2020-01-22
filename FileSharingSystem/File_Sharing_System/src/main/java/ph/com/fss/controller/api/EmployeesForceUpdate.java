package ph.com.fss.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import ph.com.fss.exception.LdapBatchUpdateFailed;
import ph.com.fss.model.ResponseModel;
import ph.com.fss.service.FetchLdapEmployeeService;

@Controller
public class EmployeesForceUpdate {
	
	@Autowired
	private FetchLdapEmployeeService ldpaEmp;
	
	@RequestMapping(value="forceUpdate")
	@ResponseBody
	public ResponseModel<String> employeeForceUpdate(){
		ResponseModel<String> model = new ResponseModel<String>();
		try {
			ldpaEmp.updateEmployeeList();
		} catch (LdapBatchUpdateFailed e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return model;
	}
	
	
}
