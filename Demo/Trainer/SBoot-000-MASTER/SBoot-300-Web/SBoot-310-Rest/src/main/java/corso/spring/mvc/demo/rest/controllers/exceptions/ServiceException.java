package corso.spring.mvc.demo.rest.controllers.exceptions;

import lombok.Data;

@Data
public class ServiceException extends RuntimeException {

	private int code=-202;
	
	public ServiceException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	public ServiceException(String arg0, int code) {
		super(arg0);
		this.code=code;
		// TODO Auto-generated constructor stub
	}

	public ServiceException(Throwable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}
}
