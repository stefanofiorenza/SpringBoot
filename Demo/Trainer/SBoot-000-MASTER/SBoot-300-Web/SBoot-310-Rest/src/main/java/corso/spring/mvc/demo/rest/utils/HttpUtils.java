package corso.spring.mvc.demo.rest.utils;

import java.io.ByteArrayOutputStream;

import org.apache.commons.io.IOUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.knits.sboot.common.beans.Employee;



public class HttpUtils {

	
	public static Employee parseIntoEmployee(String employeeAsString){
		Employee emp=null;
		try {
			ObjectMapper mapper = new ObjectMapper();
			emp= mapper.readValue(employeeAsString,Employee.class);			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return emp;	    
	}
	
	public static byte[] serializeEmployee(Employee employee){
		ByteArrayOutputStream bos=null;
		try {
			ObjectMapper mapper = new ObjectMapper();
			bos= new ByteArrayOutputStream();
			mapper.writeValue(bos,employee);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			IOUtils.closeQuietly(bos);
		}
		return bos.toByteArray();
		
	}
}
