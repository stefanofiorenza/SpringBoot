package corso.spring.mvc.demo.rest.controllers.basic;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import corso.spring.mvc.demo.rest.controllers.exceptions.ServiceException;

@Controller
public class Demo01ControllerHandlerMatching {

	@RequestMapping(value = "/basic/path")
	@ResponseBody
	public String getSomeResourceAsStringByPathMapping() {
		//throw new ServiceException("Duplicate Key on table <SomeTable>", -900);
		return "value from uri path mapping";
	}
	
	@RequestMapping(value = "/basic/path",method = RequestMethod.POST)
	@ResponseBody
	public String getSomeResourceAsStringByPathMappingPost() {
		return "value from uri path mapping method POST";
	}
		
	@RequestMapping(value = "/basic/path/headers", headers = "key=val")
	@ResponseBody
	public String getSomeResourceAsStringByPathMappingWithHeader() {
		return "value from uri path mapping method Header key=val";
	}
	
	@RequestMapping(value = "/basic/path/multiple/headers", headers = { "key1=val1", "key2=val2" })
	@ResponseBody
	public String getSomeResourceAsStringByPathMappingWithMultipleHeader() {
		return "value from uri path mapping method multiple Headers key1=val1, key2=val2";
	}
	
	@RequestMapping(value = "/basic/path/headers/accept", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public String getSomeResourceAsStringAcceptHeader() {
		return "value from uri path mapping method Accept Headers";
	}
	
	@RequestMapping(value = "/basic/path/headers/produces", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public String getSomeResourceAsStringProducer() {
		return "value from uri path mapping method Produce json";
	}
	
	
	
}
