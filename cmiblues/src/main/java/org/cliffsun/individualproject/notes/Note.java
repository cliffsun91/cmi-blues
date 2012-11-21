package org.cliffsun.individualproject.notes;

public class Note {

	private BasicNote basicNote;
	private Shift shift;
	
	public Note(BasicNote basicNote){
		this.basicNote = basicNote;
		this.shift = Shift.Natural;
	}
	
	public Note(BasicNote basicNote, Shift shift){
		this.basicNote = basicNote;
		this.shift = shift;
	}
	
	public BasicNote getBasicNote(){
		return basicNote;
	}
	
	public Shift getShift(){
		return shift;
	}
	
	public String getRepresentation(){
		return basicNote.toString() + "-" + shift.toString();
	}
}
