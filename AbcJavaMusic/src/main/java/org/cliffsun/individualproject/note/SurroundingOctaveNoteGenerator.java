package org.cliffsun.individualproject.note;

import java.util.ArrayList;
import java.util.List;

import org.cliffsun.individualproject.utils.Utils;

public class SurroundingOctaveNoteGenerator {

	public List<MainNoteComponent> generateSurroundingMainNotesForTrebleClef(List<BasicNote> suitableBasicNotes, int originalOctave) {
		List<MainNoteComponent> results = new ArrayList<MainNoteComponent>();
		for (BasicNote note: suitableBasicNotes){
//			if (originalOctave > 0){
//				results.add(new MainNoteComponent(note, originalOctave-1));
//			}
//			results.add(new MainNoteComponent(note, originalOctave));
//			if (originalOctave < 3){
//				results.add(new MainNoteComponent(note, originalOctave+1));
//			}
			for(int i = 0; i < 3; i ++){
				MainNoteComponent newNote = new MainNoteComponent(note, originalOctave + (i-1));
				if(!newNote.isLowerThan(Utils.minTrebleNote) && !newNote.isHigherThan(Utils.maxTrebleNote)){
					results.add(newNote);
				}
			}
		}

		return results;
	}
}
