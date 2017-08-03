package net.codinginaction.mp.sbsoapws.helper;


import javax.xml.ws.WebFault;

import org.springframework.util.StringUtils;

import lombok.ToString;
import net.codinginaction.mp.sbsoapws.data.ExceptionData;
import net.codinginaction.mp.sbsoapws.util.RequestUtil;

@WebFault(name = "BuException", targetNamespace = "net.codinginaction.mp.sbsoapws")
@ToString
public class BuException extends Exception {

	private static final long serialVersionUID = -2495792691844526556L;
	private ExceptionData data;

	public BuException() {
		super();
	}

	public BuException(String message) {
		super(message);
	}

	public BuException(String message, Throwable cause) {
		super(message, cause);
	}

	public BuException(String message, ExceptionData data) {
		super(message);
		this.data = data;
	}

	public BuException(ExceptionData data) {
		super(RequestUtil.DEFAULT_SOAP_FAULT_STRING);
		this.data = data;
	}


	public BuException(String message, ExceptionData data, Throwable cause) {
		super(message, cause);
		this.data = data;
	}

	public ExceptionData getFaultInfo() {
		return data;
	}

	@Override
	public String getMessage() {
		if (data == null) {
			return super.getMessage();
		} else {
			String error = data.getCode().name();
			if (!StringUtils.hasText(data.getMessage())) {
				error +=  ": " + data.getMessage();
			}
			return error;
		}
	}

}
