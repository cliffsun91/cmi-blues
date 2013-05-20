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
		
		List<BasicNote> chromaticNotes = BasicNote.getChromaticNoteList();
		List<MainNoteComponent> suitableNotes = new ArrayList<MainNoteComponent>();
		for (BasicNote potentialNote : chromaticNotes){
			int newNoteValue = basicNoteNumberValue + upOrDown;
			if (newNoteValue == 0){
				newNoteValue = 12;
				octaveShift--;
			}
			else if (newNoteValue == 13){
				newNoteValue = 1;
				octaveShift++;
			}
			if (potentialNote.getIntegerValueForNote() == newNoteValue){
				suitableNotes.add(new MainNoteComponent(potentialNote, octaveShift));
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
