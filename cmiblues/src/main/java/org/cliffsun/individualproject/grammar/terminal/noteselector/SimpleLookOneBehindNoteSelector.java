package org.cliffsun.individualproject.grammar.terminal.noteselector;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.cliffsun.individualproject.grammar.toneclass.AbstractMultipleNotesTone;
import org.cliffsun.individualproject.grammar.toneclass.ApproachTone;
import org.cliffsun.individualproject.grammar.toneclass.RestTone;
import org.cliffsun.individualproject.grammar.toneclass.Tone;
import org.cliffsun.individualproject.note.BasicNote;
import org.cliffsun.individualproject.note.MainNoteComponent;
import org.cliffsun.individualproject.note.SurroundingOctaveNoteGenerator;

public class SimpleLookOneBehindNoteSelector extends AbstractIntervalNoteSelector{

	SurroundingOctaveNoteGenerator gen;
	
	public SimpleLookOneBehindNoteSelector() throws FileNotFoundException, IOException {
		super();
		gen = new SurroundingOctaveNoteGenerator();
	}

	public MainNoteComponent getSuitableNoteForTone(Tone tone, MainNoteComponent previous){
		int octaveShift = previous.getOctaveShift();
		
		if (tone instanceof AbstractMultipleNotesTone){
			List<BasicNote> suitableBasicNotes = ((AbstractMultipleNotesTone) tone).getSuitableNoteList();
			List<MainNoteComponent> allSuitableMainNotes = gen.generateSurroundingMainNotesForTrebleClef(suitableBasicNotes, octaveShift);
			List<MainNoteComponent> suitableIntervalNotes = new ArrayList<MainNoteComponent>();
			for (MainNoteComponent note: allSuitableMainNotes){
				int finalIntervalLimit = getFinalIntervalLimit();
				if (previous.getAbsInterval(note) <= finalIntervalLimit){
					suitableIntervalNotes.add(note);
				}
			}
			int randIndex = RandomListIndexPicker.pickRandomIndex(suitableIntervalNotes);
			return suitableIntervalNotes.get(randIndex);
		}
		else if (tone instanceof ApproachTone){
			ApproachTone appTone = (ApproachTone) tone;
			List<MainNoteComponent> suitableNotes = appTone.getApproachNotes(previous);
			int randIndex = RandomListIndexPicker.pickRandomIndex(suitableNotes);
			return suitableNotes.get(randIndex);
		}
		else if (tone instanceof RestTone){
			return ((RestTone)tone).getRestNote();
		}
		else {
			throw new IllegalArgumentException("Tone is not of a valid instance, tone found is of type: " + tone.getClass().getSimpleName());
		}
			
	}

}
