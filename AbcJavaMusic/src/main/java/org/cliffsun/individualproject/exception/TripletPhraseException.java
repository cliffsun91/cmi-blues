package org.cliffsun.individualproject.exception;

public class TripletPhraseException extends Exception{

	private static final long serialVersionUID = 1L;
	
	public TripletPhraseException(){
		super("Too many notes in the triplet phrase");
	}

}
