package org.cliffsun.individualproject.note;

public class MainNoteComponent implements Component{

	private BasicNote basicNote;
	private AccidentalShift accidentalShift;
	private int octaveShift;
	
	public MainNoteComponent(BasicNote basicNote){
		this.basicNote = basicNote;
		this.accidentalShift = AccidentalShift.Natural;
		this.octaveShift = 0;
	}
	
	public MainNoteComponent(BasicNote basicNote, int octaveShift){
		this.basicNote = basicNote;
		this.accidentalShift = AccidentalShift.Natural;
		this.octaveShift = octaveShift;
	}
	
	public MainNoteComponent(BasicNote basicNote, AccidentalShift shift){
		this.basicNote = basicNote;
		this.accidentalShift = shift;
		this.octaveShift = 0;
	}
	
	public MainNoteComponent(BasicNote basicNote, AccidentalShift shift, int octaveShift){
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
	
	public void setOctaveShift(int octaveShift){
		this.octaveShift = octaveShift;
	}
	
	public String getRepresentation(){
		return basicNote.toString() + getOctaveShiftRepresentation() + accidentalShift.name();
	}

	private String getOctaveShiftRepresentation() {
		if(octaveShift > 0){
			return "+" + octaveShift;
		}
		return String.valueOf(octaveShift);
	}

	@Override
	public String getAbcRepresentation() {
		return accidentalShift.toString() + getAbcBasicNoteRepresentationWithOctaveShift();
	}
	
	private String getAbcBasicNoteRepresentationWithOctaveShift() {
		if(octaveShift > 0){
			if(octaveShift == 1){
				return basicNote.toString().toLowerCase();
			}
			else if(octaveShift > 1){
				String note = basicNote.toString().toLowerCase();
				for (int i = 0; i < octaveShift-1; i++){
					note += "'";
				}
				return note;
			}
		}
		else if(octaveShift < 0){
			String note = basicNote.toString().toUpperCase();
			for (int i = 0; i < (0-octaveShift); i++){
				note += ",";
			}
			return note;
		}
		return basicNote.toString();
	}
}
