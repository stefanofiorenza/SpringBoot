package corso.spring.mvc.demo.rest.controllers.export.views;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import com.knits.sboot.common.beans.Employee;
import com.knits.sboot.common.beans.ExportWrapper;



public class EmployeeExcelView extends AbstractXlsxView{

	private static final String NULL_VALUE="";
	
	@Override
	public void buildExcelDocument(Map<String, Object> mad,
			Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		Sheet sheet = workbook.createSheet("Exported Data");		
		addHeaders(sheet);  			
		addEmployeesFromModel(sheet,mad);
		
	}
	
	
	
	private void addEmployeesFromModel(Sheet sheet,Map<String, Object> model){
		int row=FileFormatConsts.EXCL_HEADER_ROW_NUMBER+1;	
		ExportWrapper exportBean=(ExportWrapper)model.get(FileFormatConsts.EXPORT_EMPLOYEE);
		for (Employee selected : exportBean.getEmployees()){
			addContactIntoExcelRow(sheet,selected, row);
			row++;
		}
	}

	public static void addHeaders(Sheet sheet){
		
		 Row headersRow = sheet.createRow(FileFormatConsts.EXCL_HEADER_ROW_NUMBER);
		 int cellPosition = FileFormatConsts.EXCL_ROW_START_CELL_POSITION;
		 
	     for (String header : FileFormatConsts.HEADER_NAMES){        	 
	    	 Cell headerCell=headersRow.createCell(cellPosition);
	    	 headerCell.setCellValue(header);
	    	 cellPosition++;
	     }
	}
	
	public static void addContactIntoExcelRow(Sheet sheet, Employee emp, int rowNumber){
		Row dataRow = sheet.createRow(rowNumber);
		int cellPos=FileFormatConsts.EXCL_ROW_START_CELL_POSITION;		
		cellPos=addCellData(dataRow,emp.getName(),cellPos);
		cellPos=addCellData(dataRow,emp.getEmail(),cellPos);	
	}
	   	 

		
	private static int addCellData(Row dataRow,Object dataToAdd, int cellPosition){
		 Cell dataCell=dataRow.createCell(cellPosition);
		 if(dataToAdd==null){
			 updateCellByDataType(dataCell,NULL_VALUE);	
		 }else{
			 updateCellByDataType(dataCell,dataToAdd);	
		 }			 
		 return ++cellPosition;		 
	}
	
	private static void updateCellByDataType(Cell dataCell,Object dataToAdd){
		try{
			if (Boolean.class.isAssignableFrom(dataToAdd.getClass())){
				dataCell.setCellType(Cell.CELL_TYPE_BOOLEAN);
				dataCell.setCellValue((Boolean)dataToAdd);
			}else if (Long.class.isAssignableFrom(dataToAdd.getClass())){
				dataCell.setCellType(Cell.CELL_TYPE_NUMERIC);
				dataCell.setCellValue((Long)dataToAdd);
			}		
			else{
				dataCell.setCellType(Cell.CELL_TYPE_STRING);	
				dataCell.setCellValue((String)dataToAdd);
			}
		}catch (Exception e){
			System.out.println("Data to add: "+dataToAdd);
			e.printStackTrace();
		}
	
	
}
}
