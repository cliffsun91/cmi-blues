package org.cliffsun.individualproject.duration;

import org.apache.commons.math3.fraction.Fraction;

public enum Duration {
	whole("4", new Fraction(4,1)),      		   //semibreve
	dottedHalf("3", new Fraction(3,1)),			   //dotted minim
	half("2", Fraction.TWO),       		           //minim
	dottedQuarter("3/2", new Fraction(3, 2)),	   //dotted crotchet
	quarter("", Fraction.ONE),     				   //crotchet
	dottedEighth("3/4", Fraction.THREE_QUARTERS),  //dotted quaver
	eighth("/2", Fraction.ONE_HALF),     		   //quaver
	sixteenth("/4", Fraction.ONE_QUARTER), 		   //semiquaver
	quarterTriplet("/3", Fraction.ONE_THIRD),  	   //triplet quarter note
	halfTriplet("2/3", Fraction.TWO_THIRDS);       //triplet half note
	
	private String abcRepr;
	private Fraction actualDuration;
	
	Duration(String abcRepr, Fraction actualDuration) {
		this.abcRepr = abcRepr;
		this.actualDuration = actualDuration;
	}
	
	public String getAbcRepresentation(){
		return abcRepr;
	}
	
	public Fraction getActualDuration(){
		return actualDuration;
	}
	
	public double getDurationAsDouble(){
		return actualDuration.doubleValue();
	}
	
    public static Duration getDurationEnum(Fraction value) {
        for(Duration v : values()) {
            if(value.equals(v.getActualDuration())) {
            	return v;
            }
        }
        throw new IllegalArgumentException("No such Duration: " + value);
    }
	
	
}
