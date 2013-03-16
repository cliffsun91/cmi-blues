package org.cliffsun.individualproject.grammar.terminal.noteselector;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.cliffsun.individualproject.grammar.toneclass.AbstractMultipleNotesTone;
import org.cliffsun.individualproject.grammar.toneclass.ApproachTone;
import org.cliffsun.individualproject.grammar.toneclass.RestTone;
import org.cliffsun.individualproject.grammar.toneclass.Tone;
import org.cliffsun.individualproject.note.BasicNote;
import org.cliffsun.individualproject.note.MainNoteComponent;

public class SimpleLookOneBehindNoteSelector extends AbstractNoteSelector{

	public SimpleLookOneBehindNoteSelector() throws FileNotFoundException, IOException {
		super();
	}

	public MainNoteComponent getSuitableNoteForTone(Tone tone, MainNoteComponent previous){
		int intervalLimit = getIntegerPropertyForAttribute("intervalLimit");
		double intervalLimitProb = getDoublePropertyForAttribute("intervalLimitProb");
		int octaveShift = previous.getOctaveShift();
		
		double rand = Math.random();
		
		if (tone instanceof AbstractMultipleNotesTone){
			List<BasicNote> suitableBasicNotes = ((AbstractMultipleNotesTone) tone).getSuitableNoteList();
			List<MainNoteComponent> allSuitableMainNotes = generateOneOctaveUpAndDownMainNotes(suitableBasicNotes, octaveShift);
			if (rand < intervalLimitProb){
				List<MainNoteComponent> suitableIntervalNotes = new ArrayList<MainNoteComponent>();
				for (MainNoteComponent note: allSuitableMainNotes){
					if (previous.getAbsInterval(note) <= intervalLimit){
						suitableIntervalNotes.add(note);
					}
				}
				Collections.shuffle(suitableIntervalNotes);
				return suitableIntervalNotes.get(0);
			}
			else{
				Collections.shuffle(allSuitableMainNotes);
				return allSuitableMainNotes.get(0);
			}
		}
		else if (tone instanceof ApproachTone){
			ApproachTone appTone = (ApproachTone) tone;
			List<MainNoteComponent> suitableNotes = appTone.getApproachNotes(previous);
			Collections.shuffle(suitableNotes);
			return suitableNotes.get(0);
		}
		else if (tone instanceof RestTone){
			return ((RestTone)tone).getRestNote();
		}
		else {
			throw new IllegalArgumentException("Tone is not of a valid instance, tone found is of type: " + tone.getClass().getSimpleName());
		}
			
//		if (AbstractMultipleNotesTone.class.isInstance(tone)){
//			
//		}
		
	}

	private List<MainNoteComponent> generateOneOctaveUpAndDownMainNotes(List<BasicNote> suitableBasicNotes, int originalOctave) {
		List<MainNoteComponent> results = new ArrayList<MainNoteComponent>();
		for (BasicNote note: suitableBasicNotes){
			results.add(new MainNoteComponent(note, originalOctave-1));
			results.add(new MainNoteComponent(note, originalOctave));
			results.add(new MainNoteComponent(note, originalOctave+1));
		}
		return results;
	}
}
