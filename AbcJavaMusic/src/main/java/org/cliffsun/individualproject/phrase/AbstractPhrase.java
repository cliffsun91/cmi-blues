package org.cliffsun.individualproject.phrase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.cliffsun.individualproject.note.AccidentalShift;
import org.cliffsun.individualproject.note.ChordComponent;
import org.cliffsun.individualproject.note.Component;
import org.cliffsun.individualproject.note.MainNoteComponent;
import org.cliffsun.individualproject.note.TimedComponent;

public abstract class AbstractPhrase implements Phrase{

	List<MainNoteComponent> accentedNotes;
	
	public AbstractPhrase() {
		accentedNotes = new ArrayList<MainNoteComponent>();
	}
	
	@Override
	public List<MainNoteComponent> getAccumAccentedNotes(){
		return accentedNotes;
	}
	
	public void addNoteIfAccentedToAccentedNotes(MainNoteComponent note){
		if(note.getBasicNote().getAccidentalShift() != AccidentalShift.Natural){
			if(!accentedNotes.contains(note)){
				accentedNotes.add(note);
			}
		}
	}
	
	public void addTimedComponentToAccentedNotes(TimedComponent t){
		List<MainNoteComponent> unpackedMainNotes = unpackComponent(t.getComponent());
		for(MainNoteComponent mainNote : unpackedMainNotes){
			if(mainNote.getBasicNote().getAccidentalShift() != AccidentalShift.Natural){
				addNoteIfAccentedToAccentedNotes(mainNote);
			}
		}
	}
	
	public List<MainNoteComponent> unpackComponent(Component component){
		if (component instanceof MainNoteComponent){
			return Arrays.asList((MainNoteComponent) component);
		}
		else if (component instanceof ChordComponent){
			ChordComponent chord = (ChordComponent) component;
			return chord.getNoteList();
		}
		else{
			throw new IllegalArgumentException("Illegal instance of Component found");
		}
	}
	
	public void addAccumAccentedNotes(List<MainNoteComponent> accumAccentedNotes){
		accentedNotes.addAll(accumAccentedNotes);
	}
}
