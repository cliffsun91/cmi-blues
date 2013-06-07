package org.cliffsun.individualproject.note;

import java.util.List;

import org.cliffsun.individualproject.utils.Utils;


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
	
	@Override
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
		if (!this.isLowerThan(note)){
			throw new IllegalArgumentException("The comparing note must be a higher (or equivalent) note to the receiver");
		}
		int diff = note.octaveShift - this.octaveShift;
		//System.out.print("(octave diff is: " + diff + ")");
		if (this.basicNote.isLowerThan(note.basicNote) || this.basicNote.isMusicallyEquivalent(note.basicNote)){
			return this.basicNote.getAbsInterval(note.basicNote) + diff*Utils.octaveInterval;
		}
		else{
			int basicNoteDiff = Utils.octaveInterval - this.basicNote.getAbsInterval(note.basicNote);
			return basicNoteDiff + (diff-1)*Utils.octaveInterval;
		}
	}
	
	public boolean isLowerThan(MainNoteComponent note){
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
	
	public boolean isHigherThan(MainNoteComponent note){
		if(this.octaveShift > note.octaveShift){
			return true;
		}
		else if(this.octaveShift < note.octaveShift){
			return false;
		}
		else{
			return this.basicNote.isHigherThan(note.basicNote);
		}
	}
	
	public int getIntegerValueForNote(){
		return basicNote.getIntegerValueForNote();
	}
	
	@Override
	public String getAbcRepresentation(List<MainNoteComponent> accumAccentedNotes) {
		String accidentalShiftString;
		if (basicNote.getAccidentalShift() == AccidentalShift.Natural && hasAppearedInAccentedNotes(accumAccentedNotes)){
			accidentalShiftString = AccidentalShift.printNatural.toString();
		}
		else{
			accidentalShiftString = basicNote.getAccidentalShift().toString();
		}
		return accidentalShiftString + getAbcBasicNoteRepresentationWithOctaveShift();
	}
	
	private boolean hasAppearedInAccentedNotes(List<MainNoteComponent> accumAccentedNotes){
		for(MainNoteComponent accentedNote : accumAccentedNotes){
			if(accentedNote.getBasicNote().getNoteEnum() == this.basicNote.getNoteEnum() &&
						accentedNote.getOctaveShift() == this.octaveShift){
				return true;
			}
		}
		return false;
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
	
    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((basicNote == null) ? 0 : basicNote.hashCode());
		result = prime * result + octaveShift;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MainNoteComponent other = (MainNoteComponent) obj;
		if (basicNote == null) {
			if (other.basicNote != null)
				return false;
		} else if (!basicNote.equals(other.basicNote))
			return false;
		if (octaveShift != other.octaveShift)
			return false;
		return true;
	}
	
	public static MainNoteComponent mainNote(BasicNote note){
		return new MainNoteComponent(note);
	}
	
	public static MainNoteComponent mainNote(BasicNote note, int octaveShift){
		return new MainNoteComponent(note, octaveShift);
	}
}
