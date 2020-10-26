package com.neu.edu.exception;

public class FacultyException extends Exception {
	
	public FacultyException(String message)
	{
		super("FacultyException-"+message);
	}
	
	public FacultyException(String message, Throwable cause)
	{
		super("FacultyException-"+message,cause);
	}
	
}
