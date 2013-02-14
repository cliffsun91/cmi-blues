package org.cliffsun.individualproject.duration;

public enum Duration {
	whole("4", 4.0),      		//semibreve
	dottedHalf("3", 3.0),			//dotted minim
	half("2", 2.0),       		//minim
	dottedQuarter("3/2", 1.5),	//dotted crotchet
	quarter("", 1.0),     		//crotchet
	dottedEighth("3/4", 0.75),	//dotted quaver
	eighth("/2", 0.5),     		//quaver
	sixteenth("/4", 0.25), 		//semiquaver
	quarterTriplet("/3", 1.0/3.0),  //triplet quarter note
	halfTriplet("2/3", 2.0/3.0);    //triplet half note
	
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
	
    public static Duration getDurationEnum(double value) {
        for(Duration v : values()) {
            if(value == v.getActualDuration()) {
            	return v;
            }
        }
        throw new IllegalArgumentException("No such Duration: " + value);
    }
	
	
}
