package org.cliffsun.individualproject.notes;

public enum AccidentalShift {
	Flat("_"), Natural(""), Sharp("^");

    private final String value;

    AccidentalShift(String value) {
        this.value = value;
    }

    public String toString() {
        return value;
    }
}

