package ph.com.fss.util;

import javax.naming.NamingException;
import javax.naming.directory.Attributes;

import org.springframework.ldap.core.AttributesMapper;

import ph.com.fss.entity.fss.Employee;

public class EmployeeAttributesMapper implements AttributesMapper{

	@Override
	public Employee mapFromAttributes(Attributes attr) throws NamingException {
		
		Employee employee = new Employee();
		
		StringBuilder fullname = new StringBuilder("");
		
		if(null != attr.get("givenName")){
			fullname.append(attr.get("givenName" ).get());
		}
		
		if( null != attr.get("sn")){
			fullname.append(" " + attr.get("sn" ).get());
		}
		
		
		employee.setName(fullname.toString());
		
		if(null != attr.get("mail")){
			employee.setEmail((String)attr.get("mail").get());
		}
		
		
		if(null != attr.get("uidNumber")){
			employee.setId(Integer.parseInt( (String)attr.get("uidNumber").get() ));
		}else{
			employee.setId(null);
		}
		
		return employee;
	}

}
