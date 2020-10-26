package com.neu.edu.exception;

public class ApplicationException extends Exception {
	
	public ApplicationException(String message)
	{
		super("ApplicationException-"+message);
	}
	
	public ApplicationException(String message, Throwable cause)
	{
		super("ApplicationException-"+message,cause);
	}
	
}
