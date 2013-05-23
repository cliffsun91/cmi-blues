package org.cliffsun.individualproject.note;

import java.util.ArrayList;
import java.util.List;

public class ChordComponent implements Component{
	
	ArrayList<MainNoteComponent> noteList;
	
	public ChordComponent() {
		noteList = new ArrayList<MainNoteComponent>();
	}
	
	public void addNoteToChordComponent(MainNoteComponent mainNote){
		noteList.add(mainNote);
	}
	
	public void addMultipleNotesToChordComponent(MainNoteComponent ... notes){
		for (MainNoteComponent note : notes){
			addNoteToChordComponent(note);
		}
	}
	
	@Override
	public int getOctaveShift(){
		//get an average of the octave shifts for each note and round it
		double accum = 0.0;
		for(MainNoteComponent note : noteList){
			accum += (double) note.getOctaveShift();
		}
		accum = accum / noteList.size();
		return (int) Math.round(accum);
	}
	
	public ArrayList<MainNoteComponent> getNoteList(){
		return noteList;
	}
	
	public String getAbcRepresentation(List<MainNoteComponent> accumAccentedNotes){
		String representation = "[";
		for(MainNoteComponent note : noteList){
			representation += note.getAbcRepresentation(accumAccentedNotes);
		}
		return representation + "]";
	}
}
