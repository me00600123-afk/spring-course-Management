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
	
	public ExceptionAttribute(Map<String,String> details) {
		this.status="error";
		this.success=Boolean.FALSE;
		this.message="voilation role";
		this.details=details;
	}

	

	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public boolean isSuccess() {
		return success;
	}


	public void setSuccess(boolean success) {
		this.success = success;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public List<String> getDetail() {
		return detail;
	}


	public void setDetail(List<String> detail) {
		this.detail = detail;
	}


	public Map<String, String> getDetails() {
		return details;
	}


	public void setDetails(Map<String, String> details) {
		this.details = details;
	}
	
	

}
