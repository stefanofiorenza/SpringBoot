package corso.spring.mvc.demo.controllers.rest;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.knits.sboot.common.beans.Employee;

import corso.spring.mvc.demo.rest.utils.HttpUtils;


@Controller
public class ResponseMappingController {

	@RequestMapping(value ="/domain/demo/employee/json/res/http", method = RequestMethod.POST, 			
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes= MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody HttpServletResponse echoReceivedAsLowerApiHttp(@RequestBody Employee inserted,HttpServletResponse response) throws IOException{		
		response.getOutputStream().write(HttpUtils.serializeEmployee(inserted));
		return response;
	}
	
	@RequestMapping(value ="/domain/demo/employee/json/res/os", method = RequestMethod.POST, 			
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes= MediaType.APPLICATION_JSON_VALUE)
	public OutputStream echoReceivedAsLowerApiOs(@RequestBody Employee inserted) {
		ByteArrayOutputStream bos=null;
		try{
			bos=new ByteArrayOutputStream();
			bos.write(HttpUtils.serializeEmployee(inserted));
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			IOUtils.closeQuietly(bos);
		}		
		return bos;
	}
	
	
	@RequestMapping(value ="/domain/demo/employee/res/bytes", method = RequestMethod.POST, 			
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes= MediaType.APPLICATION_JSON_VALUE)
	public byte[] echoReceivedAsLowerApiBytes(@RequestBody Employee inserted) {					
		return HttpUtils.serializeEmployee(inserted);
	}
	
	
	
}
