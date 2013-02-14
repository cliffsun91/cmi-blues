package org.cliffsun.individualproject.note;

public enum SimpleNoteEnum {
	C("C", 1),
	D("D", 3),
	E("E", 5),
	F("F", 6),
	G("G", 8),
	A("A", 10),
	B("B", 12),
	REST("z", -1);
	
	private String repr;
	private int noteNumber;
	
	SimpleNoteEnum(String repr, int noteNumber){
		this.repr = repr;
		this.noteNumber = noteNumber;
	}
	
	public String getRepresentation(){
		return repr;
	}
	
	public int getNoteNumberRepresentation(){
		return noteNumber;
	}
	
}
