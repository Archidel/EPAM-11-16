package com.epam.store.bean;

import java.io.Serializable;

public class Response implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String message;
	private String errorMessage;
	private boolean statusError;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public boolean isStatusError() {
		return statusError;
	}
	public void setStatusError(boolean statusError) {
		this.statusError = statusError;
	}
	
}
