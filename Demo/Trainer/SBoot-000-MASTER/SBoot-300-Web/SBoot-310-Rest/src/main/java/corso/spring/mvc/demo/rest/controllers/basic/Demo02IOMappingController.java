package corso.spring.mvc.demo.rest.controllers.basic;

import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Demo02IOMappingController {

	@RequestMapping(value = "/basic/category/{id}/sub/{subCatId}")
	@ResponseBody
	public String getValueWithPathVariables(@PathVariable long id, @PathVariable long subCatId) {
		return "Get Catyegory=" + id + " SubCategory=" + subCatId;
	}
	
	@RequestMapping(value = "/basic/path/io/{numericId:[\\d]+}")
	@ResponseBody
	public String getValueWithNumericPathVariables(@PathVariable final long numericId) {
		return "Get numeric value="+numericId;
	}
	
	@RequestMapping(value = "/basic/path/io/params")
	@ResponseBody
	public String getValueWithRequestParamVariables(@RequestParam("id") long id) {
		return "Get request param value="+id;
	}
	
	@RequestMapping(value = "/basic/path/io/matrix/{params}/other/{params2}")
	@ResponseBody
	public String getValueWithMatrixParamsVariables(
			@PathVariable final long params,
			@MatrixVariable String q, //this should be nullable type
			@PathVariable final long params2,
			@MatrixVariable String[] multiple)//this should be nullable type 
			{
		
			StringBuffer sb= new StringBuffer("[");
			
			for (String value: multiple){
				sb.append(value+",");
			}
			sb.deleteCharAt(sb.length()-1);//remove last comma
			sb.append("]");
			
			
			return "getValueWithMatrixParamsVariables with data : "+
				"params :" +params+
				" q :" +q+
				" params2 :" +params2+
				" multiple :" +sb.toString();
	}
	
	
    @RequestMapping(value = "/basic/path/io/matrix2/{matrixVars}/other/{otherMatrixVars}")
 	@ResponseBody
 	public String getValueWithMatrixParamsVariablesAsMap(
 			 @MatrixVariable MultiValueMap<String, String> matrixVars,
 		     @MatrixVariable(pathVar="otherMatrixVars") MultiValueMap<String, String> otherMatrixVars){
 		
    	return "matrixVars: "+
    			matrixVars.toString()+
    			" otherMatrixVars: "+otherMatrixVars.toString();
 	}
 		
    
    @RequestMapping(value = { "/basic/demo/io/headers"})
	@ResponseBody
	public String getValueMultiplePathMapping( 
			@RequestHeader("Accept-Encoding") String encoding,
			@RequestHeader("Keep-Alive") long keepAlive,
			@RequestHeader("Message-Source") String messageSource
			) {
    	    	
		return "Headers values: "+
				" encoding: "+encoding+
				" keepAlive: "+keepAlive+
				" messageSource: "+messageSource;
	}
    
	
	@RequestMapping(value = { "/basic/path/io/params1", "/basic/anotherpath/io/params2" })
	@ResponseBody
	public String getValueMultiplePathMapping() {
		return "default mapping";
	}
	
	
	
	
	@RequestMapping(value = "*")
	@ResponseBody
	public String getFallback() {
		return "Fallback for GET Requests";
	}
	
	@RequestMapping(value = "*", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String allFallback() {
		return "Fallback for All Requests";
	}
	
}
