package corso.spring.mvc.demo.controllers.rest;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.knits.sboot.common.beans.Employee;

import corso.spring.mvc.demo.rest.utils.HttpUtils;


@Controller
public class RequestMappingController {

	@RequestMapping(value ="/domain/demo/employee/json/httpRequest", method = RequestMethod.POST, 			
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes= MediaType.APPLICATION_JSON_VALUE)
	public Employee echoReceivedAsLowerApi(@RequestBody HttpServletRequest httpRequest) throws IOException{		
		String employeeAsString=new String(IOUtils.toByteArray(httpRequest.getInputStream()),"UTF-8");
		return HttpUtils.parseIntoEmployee(employeeAsString);
	}
	
	@RequestMapping(value ="/domain/demo/employee/json/is", method = RequestMethod.POST, 			
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes= MediaType.APPLICATION_JSON_VALUE)
	public Employee echoReceivedAsLowerApi(@RequestBody InputStream inputStream) throws IOException{	
		String employeeAsString=new String(IOUtils.toByteArray(inputStream),"UTF-8");		
		return HttpUtils.parseIntoEmployee(employeeAsString);
	}
	
	
	@RequestMapping(value ="/domain/demo/employee/json/bytes", method = RequestMethod.POST, 			
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes= MediaType.APPLICATION_JSON_VALUE)
	public Employee echoReceivedAsLowerApi(@RequestBody byte[] bytes) throws IOException{	
		String employeeAsString=new String(bytes,"UTF-8");		
		return HttpUtils.parseIntoEmployee(employeeAsString);
	}
	
	
	@RequestMapping(value ="/domain/demo/employee/json/string", method = RequestMethod.POST, 			
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes= MediaType.APPLICATION_JSON_VALUE)
	public Employee echoReceivedAsLowerApi(@RequestBody String employeeAsString) throws IOException{			
		return HttpUtils.parseIntoEmployee(employeeAsString);
	}
}
