package corso.spring.mvc.demo.rest.controllers.binaries;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/binaries")
public class UploadController {

    private static final String FILE_PATH = "C:/tmp/saved";
   
    @RequestMapping(value = "/upload/single", method = RequestMethod.POST)
    public @ResponseBody String uploadFile(@RequestParam("uploadedFile") MultipartFile uploadedFile) throws IOException  {    	
    	return saveFile(uploadedFile.getName(),uploadedFile.getBytes());        
    }

    @RequestMapping(value = "/upload/multiple", method = RequestMethod.POST)
	public @ResponseBody String uploadMultipleFilesHandler(@RequestParam("file") MultipartFile[] files) throws IOException {
    	int saved=0;
    	for (MultipartFile uploadedFile : files){
    		saveFile(uploadedFile.getName(),uploadedFile.getBytes());
    		saved++;
    	}    	
    	return "saved "+saved+" files";
    }
    
     
     
    private String saveFile(String fileName,byte[] dataToSave)  {
    	
    	
    	BufferedOutputStream bos = null;
    	File filePath=null;
		
		try{
			File dir = new File(FILE_PATH + File.separator + "tmpFiles");
			if (!dir.exists()){
				dir.mkdirs();				
			}
			filePath=new File(dir+ File.separator +fileName);
			bos =new BufferedOutputStream(new FileOutputStream(filePath));
			bos.write(dataToSave);
			bos.flush();
    	}catch (IOException e){
    		e.printStackTrace();
    		return e.getMessage();
    		
    	}finally{
    		IOUtils.closeQuietly(bos);
    	}
		return filePath.toString();
    }

}
