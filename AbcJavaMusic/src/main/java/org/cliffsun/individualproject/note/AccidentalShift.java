package org.cliffsun.individualproject.note;

public enum AccidentalShift {
	Flat("_", -1), Natural("", 0), Sharp("^", 1);

    private final String abcRepresentation;
    private int shiftNumber;

    AccidentalShift(String abcRepresentation, int shiftNumber) {
        this.abcRepresentation = abcRepresentation;
        this.shiftNumber = shiftNumber;
    }

    public String toString() {
        return abcRepresentation;
    }
    
    public int getShiftValue(){
    	return shiftNumber;
    }
}

