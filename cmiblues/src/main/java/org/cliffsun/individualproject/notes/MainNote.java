package org.cliffsun.individualproject.notes;

public class MainNote {

	private BasicNote basicNote;
	private AccidentalShift accidentalShift;
	private int octaveShift;
	
	public MainNote(BasicNote basicNote){
		this.basicNote = basicNote;
		this.accidentalShift = AccidentalShift.Natural;
		this.octaveShift = 0;
	}
	
	public MainNote(BasicNote basicNote, int octaveShift){
		this.basicNote = basicNote;
		this.accidentalShift = AccidentalShift.Natural;
		this.octaveShift = octaveShift;
	}
	
	public MainNote(BasicNote basicNote, AccidentalShift shift){
		this.basicNote = basicNote;
		this.accidentalShift = shift;
		this.octaveShift = 0;
	}
	
	public MainNote(BasicNote basicNote, AccidentalShift shift, int octaveShift){
		this.basicNote = basicNote;
		this.accidentalShift = shift;
		this.octaveShift = octaveShift;
	}
	
	public BasicNote getBasicNote(){
		return basicNote;
	}
	
	public AccidentalShift getAccidentalShift(){
		return accidentalShift;
	}
	
	public int getOctaveShift(){
		return octaveShift;
	}
	
	public String getRepresentation(){
		return basicNote.toString() + "-" + accidentalShift.toString();
	}
}
