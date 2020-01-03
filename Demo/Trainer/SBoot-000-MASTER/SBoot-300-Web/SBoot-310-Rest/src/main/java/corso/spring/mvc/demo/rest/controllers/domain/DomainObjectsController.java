package corso.spring.mvc.demo.rest.controllers.domain;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.knits.sboot.common.beans.Employee;
import corso.spring.mvc.demo.rest.controllers.exceptions.UserException;
import corso.spring.mvc.demo.rest.utils.HttpUtils;


@Controller
@RequestMapping("/domain")
public class DomainObjectsController {

	@RequestMapping(value ="/demo/employee/get/xml", method = RequestMethod.GET, 
			produces = MediaType.APPLICATION_XML_VALUE)	
	public @ResponseBody Employee getEmployeeAsXml(){
		return new Employee("stefano","stefano@email.it");
	}
	
	@RequestMapping(value ="/demo/employee/get/json", method = RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Employee getEmployeeAsJson(){
		//throw new UserException("User not found");
		return new Employee("stefano","stefano@email.it");
	}
	
	@RequestMapping(value ="/demo/employee/echo/xml", method = RequestMethod.POST, 
			produces = MediaType.APPLICATION_XML_VALUE,
			consumes= MediaType.APPLICATION_XML_VALUE)
	public @ResponseBody Employee echoEmployeeAsXml(@RequestBody Employee inserted){
		return inserted;
	}
	
	@RequestMapping(value ="/demo/employee/echo/json", method = RequestMethod.POST, 
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes= MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Employee echoEmployeeAsJson(@RequestBody Employee inserted){
		return inserted;
	}
	
	
	
	@RequestMapping(value ="/demo/employee/echo/entity/json", method = RequestMethod.POST, 
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes= MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody  ResponseEntity<Employee> echoEmployeeAsHttpEntity(@RequestBody Employee inserted){
		 return new ResponseEntity<Employee>(inserted,HttpStatus.OK);		 
	}
	

	
	@RequestMapping(value ="/demo/employee/echo/entity/slow/json", method = RequestMethod.POST, 
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes= MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody  ResponseEntity<Employee> echoEmployeeAsHttpEntityPut(@RequestBody Employee inserted){
		try {
			Thread.sleep(3000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		 return new ResponseEntity<Employee>(inserted,HttpStatus.OK);		 
	}
	
	
	
}
