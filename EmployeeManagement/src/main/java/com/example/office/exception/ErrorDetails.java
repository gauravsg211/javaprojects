package com.example.office.exception;



import java.util.Date;

public class ErrorDetails {
	private String success;
	private Date timestamp;
	private String message;
	private String details;
	private int status;


	public ErrorDetails(String success,Date timestamp, String message, String details,int status) {
		super();
		this.success=success;
		this.timestamp = timestamp;
		this.message = message;
		this.details = details;
		this.status=status;
		
	}
	
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	
	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}
	
	
	

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	
}
