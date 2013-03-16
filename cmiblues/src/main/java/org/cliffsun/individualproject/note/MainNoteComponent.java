package org.cliffsun.individualproject.note;

public class MainNoteComponent implements Component{

    private BasicNote basicNote;
	private int octaveShift;
	
	public MainNoteComponent(BasicNote basicNote){
		this(basicNote, 0);
	}
	
	public MainNoteComponent(BasicNote basicNote, int octaveShift){
		this.basicNote = basicNote;
		this.octaveShift = octaveShift;
	}
	
	public BasicNote getBasicNote(){
		return basicNote;
	}
	
	public int getOctaveShift(){
		return octaveShift;
	}
	
	public int getAbsInterval(MainNoteComponent note){
		if (this.octaveShift < note.octaveShift){
			return this.getInterval(note);
		}
		else if(this.octaveShift > note.octaveShift){
			return note.getAbsInterval(this);
		}
		else {
			return this.basicNote.getAbsInterval(note.basicNote);
		}
	}
	
	private int getInterval(MainNoteComponent note){
		if (!this.isLowerOrEqualThan(note)){
			throw new IllegalArgumentException("The comparing note must be a higher note than the receiver");
		}
		int diff = note.octaveShift - this.octaveShift;
		if (this.basicNote.isLowerThan(note.basicNote) || this.basicNote.isMusicallyEquivalent(note.basicNote)){
			return this.basicNote.getAbsInterval(note.basicNote) + diff*12;
		}
		else{
			int basicNoteDiff = 12 - this.basicNote.getAbsInterval(note.basicNote);
			return basicNoteDiff + (diff-1)*12;
		}
	}
	
	public boolean isLowerOrEqualThan(MainNoteComponent note){
		if(this.octaveShift < note.octaveShift){
			return true;
		}
		else if(this.octaveShift > note.octaveShift){
			return false;
		}
		else{
			return this.basicNote.isLowerThan(note.basicNote);
		}
	}
	
	public int getIntegerValueForNote(){
		return basicNote.getIntegerValueForNote();
	}
	
	public String getAbcRepresentation() {
		return basicNote.getAccidentalShift().toString() + getAbcBasicNoteRepresentationWithOctaveShift();
	}
	
	private String getAbcBasicNoteRepresentationWithOctaveShift() {
		if(octaveShift > 0){
			if(octaveShift == 1){
				return basicNote.getNoteEnum().getRepresentation().toLowerCase();
			}
			else if(octaveShift > 1){
				String note = basicNote.getNoteEnum().getRepresentation().toLowerCase();
				for (int i = 0; i < octaveShift-1; i++){
					note += "'";
				}
				return note;
			}
		}
		else if(octaveShift < 0){
			String note = basicNote.getNoteEnum().getRepresentation().toUpperCase();
			for (int i = 0; i < (0-octaveShift); i++){
				note += ",";
			}
			return note;
		}
		return basicNote.getNoteEnum().getRepresentation();
	}
	
	public static MainNoteComponent mainNote(BasicNote note){
		return new MainNoteComponent(note);
	}
	
	public static MainNoteComponent mainNote(BasicNote note, int octaveShift){
		return new MainNoteComponent(note, octaveShift);
	}
}
