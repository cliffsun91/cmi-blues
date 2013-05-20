package org.cliffsun.individualproject.exception;

public class BarLengthException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public BarLengthException(double expectedDuration, double actualDuration){
		this("BarLengthException: Expected bar to be of duration " +
			  expectedDuration +
			  " but got an actual duration of " +
			  actualDuration);
		
	}
	
	public BarLengthException(String message, double expectedDuration, double actualDuration){
		this("BarLengthException: " + message + 
			 ". Expected duration was " +
			 expectedDuration +
			 " but got an actual duration of " +
			 actualDuration);
		
	}
	
	public BarLengthException(String message){
		super(message);
	}
}
