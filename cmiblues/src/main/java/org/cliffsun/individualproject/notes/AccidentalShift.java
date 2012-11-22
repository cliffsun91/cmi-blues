package org.cliffsun.individualproject.notes;

public enum AccidentalShift {
	Flat("_"), Natural(""), Sharp("^");

    private final String abcRepresentation;

    AccidentalShift(String abcRepresentation) {
        this.abcRepresentation = abcRepresentation;
    }

    public String toString() {
        return abcRepresentation;
    }
}

