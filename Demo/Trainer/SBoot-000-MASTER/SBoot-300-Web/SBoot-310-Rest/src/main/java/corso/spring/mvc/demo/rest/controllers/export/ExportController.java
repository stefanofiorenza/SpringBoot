package corso.spring.mvc.demo.rest.controllers.export;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.knits.sboot.common.beans.ExportWrapper;
import corso.spring.mvc.demo.rest.controllers.export.views.FileFormatConsts;
import com.knits.sboot.common.service.EmployeeService;

@Controller
@RequestMapping("/export")
public class ExportController {

	@Autowired
	private EmployeeService employeeService; 
	
	@RequestMapping("/csv")
	public ModelAndView exportEmployeesAsCsv(){
		
		ModelAndView mav= new ModelAndView(FileFormatConsts.EXPORT_CSV);
		mav.addObject(FileFormatConsts.EXPORT_EMPLOYEE, employeeService.listAll());
		return mav;
	}
	
	@RequestMapping("/excel")
	public ModelAndView exportEmployeesAsExcel(){		
		ModelAndView mav= new ModelAndView(FileFormatConsts.EXPORT_EXCEL);
		mav.addObject(FileFormatConsts.EXPORT_EMPLOYEE, employeeService.listAll());
		return mav;
	}
	
	@RequestMapping(value="/employees", method=RequestMethod.GET)
//			produces = { "application/json", "application/xml","text/csv",
//			"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" } )
	public ModelAndView exportEmployees(){
		
		ModelAndView mav= new ModelAndView();		
		ExportWrapper export = new ExportWrapper();
		export.getEmployees().addAll(employeeService.listAll());		
		mav.addObject(FileFormatConsts.EXPORT_EMPLOYEE, export);
		return mav;
	}
}
