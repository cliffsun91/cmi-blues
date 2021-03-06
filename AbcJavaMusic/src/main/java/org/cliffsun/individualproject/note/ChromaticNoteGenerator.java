package org.cliffsun.individualproject.note;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChromaticNoteGenerator {
	
	public List<MainNoteComponent> getChromaticUpAndDown(MainNoteComponent note){
		return Arrays.asList(getOneChromaticNoteUp(note), getOneChromaticNoteDown(note));
	}

	public MainNoteComponent getOneChromaticNoteUp(MainNoteComponent note){
		return getOneChromaticNoteDifference(note, 1);
	}
	
	public MainNoteComponent getOneChromaticNoteDown(MainNoteComponent note){
		return getOneChromaticNoteDifference(note, -1);
	}
	
	private MainNoteComponent getOneChromaticNoteDifference(MainNoteComponent mainNote, int upOrDown){
		BasicNote basicNote = mainNote.getBasicNote();
		int octaveShift = mainNote.getOctaveShift();
		int basicNoteNumberValue = mainNote.getIntegerValueForNote();
		
		//System.out.println("finding approach note for: " + mainNote.getAbcRepresentation(new ArrayList<MainNoteComponent>()));
		
		List<BasicNote> chromaticNotes = BasicNote.getChromaticNoteList();
		List<MainNoteComponent> suitableNotes = new ArrayList<MainNoteComponent>();
		int newNoteValue = basicNoteNumberValue + upOrDown; //calculate what should be the new note value
		if (newNoteValue == 0){ //i.e. if we go from 1 (C) to 0 (nothing), we wrap around to B and dec the octave
			newNoteValue = 12;
			octaveShift--;
		}
		else if (newNoteValue == 13){ // if we go from 12 (B) to 13 (nothing), wrap around to C and inc the octave
			newNoteValue = 1;
			octaveShift++;
		}
		for (BasicNote potentialNote : chromaticNotes){
			if (potentialNote.getIntegerValueForNote() == newNoteValue){
				MainNoteComponent approachNote = new MainNoteComponent(potentialNote, octaveShift);
				//System.out.println("found approach note: " + approachNote.getAbcRepresentation(new ArrayList<MainNoteComponent>()));
				suitableNotes.add(approachNote);
			}
		}
		
		if (suitableNotes.size() == 1){
			return suitableNotes.get(0);
		}
		else if (suitableNotes.size() > 1){
			for (MainNoteComponent suitableNote : suitableNotes){
				BasicNote suitableBasicNote = suitableNote.getBasicNote();
				if (suitableBasicNote.getNoteEnum() == basicNote.getNoteEnum()){
					return suitableNote;
				}
			}
			return suitableNotes.get(0);
		}
		else{
			throw new IllegalArgumentException("The list of suitable notes for an approach tone is empty!");
		}
	}
}
