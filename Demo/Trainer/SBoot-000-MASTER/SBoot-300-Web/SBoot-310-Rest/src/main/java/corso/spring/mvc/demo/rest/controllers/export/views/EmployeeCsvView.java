package corso.spring.mvc.demo.rest.controllers.export.views;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.AbstractView;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import com.knits.sboot.common.beans.Employee;
import com.knits.sboot.common.beans.ExportWrapper;

public class EmployeeCsvView extends AbstractView {

	private String fileName;
	 
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
	
    @Override
	protected void prepareResponse(HttpServletRequest request,
            HttpServletResponse response) {
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"",
                fileName);
        response.setContentType("text/csv");
        response.setHeader(headerKey, headerValue);
    }
    
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		 ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(),
				 CsvPreference.STANDARD_PREFERENCE); 
	      buildCsvDocument(csvWriter, model);
	      csvWriter.close();		
	}

	private void buildCsvDocument(ICsvBeanWriter csvWriter,
			Map<String, Object> model) throws IOException {

		  csvWriter.writeHeader(FileFormatConsts.HEADER_NAMES);
		  ExportWrapper exportBean=(ExportWrapper)model.get(FileFormatConsts.EXPORT_EMPLOYEE);
		  for (Employee selected : exportBean.getEmployees()){			
			  csvWriter.write(selected, FileFormatConsts.HEADER_NAMES, FileFormatConsts.CSV_CELL_PROCESSORS);  
		  }
		
		
	}
	
	

}
