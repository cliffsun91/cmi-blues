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
}
