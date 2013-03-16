package org.cliffsun.individualproject.note;

import java.util.ArrayList;

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
	
	public ArrayList<MainNoteComponent> getNoteList(){
		return noteList;
	}
	
	public String getAbcRepresentation() {
		String representation = "[";
		for(MainNoteComponent note : noteList){
			representation += note.getAbcRepresentation();
		}
		return representation + "]";
	}
}
