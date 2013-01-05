package org.cliffsun.individualproject.duration;

public enum Duration {
	whole("4", 4.0),      		//semibreve
	dottedHalf("3", 3.0),			//dotted minim
	half("2", 2.0),       		//minim
	dottedQuarter("3/2", 1.5),	//dotted crotchet
	quarter("", 1.0),     		//crotchet
	dottedEigth("3/4", 0.75),	//dotted quaver
	eigth("/2", 0.5),     		//quaver
	sixteenth("/4", 0.25); 		//semiquaver
	
	private String abcRepr;
	private double actualDuration;

	Duration(String abcRepr, double actualDuration) {
		this.abcRepr = abcRepr;
		this.actualDuration = actualDuration;
	}
	
	public String getAbcRepresentation(){
		return abcRepr;
	}
	
	public double getActualDuration(){
		return actualDuration;
	}
	
	
}
