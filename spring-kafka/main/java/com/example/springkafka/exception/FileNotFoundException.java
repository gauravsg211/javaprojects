package com.example.springkafka.exception;

public class FileNotFoundException extends Exception {
	 
public String message;
	   public String getMessage() {
	return message;
}
public void setMessage(String message) {
	this.message = message;
}

	public FileNotFoundException(String message) {
	      this.message=message;
	   }

	  
	}
