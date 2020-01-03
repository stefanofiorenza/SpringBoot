package corso.spring.mvc.demo.rest.controllers.binaries;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/binaries")
public class DownloadController {

    private static final String FILE_PATH = "C:/tmp/pdf/example.pdf";
    private static final String APPLICATION_PDF = "application/pdf";

    @RequestMapping(value = "/download/httpResponse", method = RequestMethod.GET, produces = APPLICATION_PDF)
    public @ResponseBody void downloadAsHttpResponse(HttpServletResponse response)  {
    	try{
    		File file = getFile();
    		setupResponseHeaders(file,response);
            byte [] pdfAsBytes= IOUtils.toByteArray( new FileInputStream(file));           
            response.getOutputStream().write(pdfAsBytes);
            response.getOutputStream().flush();
    	}catch (IOException e){
    		e.printStackTrace();
    	}
        
    }

    @RequestMapping(value = "/download/httpEntity", method = RequestMethod.GET, produces = APPLICATION_PDF)
    public @ResponseBody HttpEntity<byte[]> downloadAsHttpEntity() throws IOException {
        File file = getFile();
        byte[] document =null;
        HttpHeaders header =null;
        
        try{        	
        	document = FileCopyUtils.copyToByteArray(file);
	        header = new HttpHeaders();
	        header.setContentType(new MediaType("application", "pdf"));
	        header.set("Content-Disposition", "inline; filename=" + file.getName());
	        header.setContentLength(document.length);
	        
	    }catch (IOException e){
			e.printStackTrace();
		}
        return new HttpEntity<byte[]>(document, header);
    }

    @RequestMapping(value = "/download/resource", method = RequestMethod.GET, produces = APPLICATION_PDF)
    public @ResponseBody Resource downloadAsResource(HttpServletResponse response) throws IOException {
    	Resource result=null;
    	try{
        	File file = getFile();
            setupResponseHeaders(file,response);       
            result= new FileSystemResource(file);
        }catch (IOException e){
			e.printStackTrace();
		}
    	return result;
    	
    }

       
    @RequestMapping(value = "/download/bytes", method = RequestMethod.GET, produces = APPLICATION_PDF)
    public @ResponseBody byte[] downloadAsBytes(HttpServletResponse response) throws IOException {
    	byte[] results=null;
    	try{
	    	File file = getFile();
	        setupResponseHeaders(file,response); 
	        results= IOUtils.toByteArray( new FileInputStream(file));
	        
    	}catch (IOException e){
			e.printStackTrace();
		}
        return results;
    }
    
        
    private void setupResponseHeaders(File file,HttpServletResponse response){
    	response.setContentType(APPLICATION_PDF);    	
        response.setHeader("Content-Disposition", "inline; filename=" + file.getName());
        response.setHeader("Content-Length", String.valueOf(file.length()));
    }
    
  
    private File getFile() throws FileNotFoundException {
        File file = new File(FILE_PATH);
        if (!file.exists()){
            throw new FileNotFoundException("file with path: " + FILE_PATH + " was not found.");
        }
        return file;
    }

}
