package com.global.exception;

import java.util.List;
import java.util.Map;

public class ExceptionAttribute {
	private String status;
	private boolean success;
	private String message; 
	private List<String> detail;
	private Map<String,String> details;
	
	public ExceptionAttribute(String message,List<String> detail) {
		this.status="error";
		this.success=Boolean.FALSE;
		this.message=message;
		this.detail=detail;
	}
	
	
	public ExceptionAttribute(String message,List<String> detail,Map<String,String> details) {
		this.status="error";
		this.success=Boolean.FALSE;
		this.message=message;
		this.detail=detail;
		this.details=details;
	}

}
