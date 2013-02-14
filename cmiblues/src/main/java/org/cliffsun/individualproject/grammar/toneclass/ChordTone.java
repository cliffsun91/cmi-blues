package org.cliffsun.individualproject.grammar.toneclass;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.cliffsun.individualproject.keys.Scale;
import org.cliffsun.individualproject.note.MainNoteComponent;

public class ChordTone extends AbstractRandomChoiceTone implements Tone{

	private Scale scale;
	public static Integer[] intervals = {1,3,5,7};
	
	public ChordTone(Scale scale) {
		this.scale = scale;
	}

	@Override
	public MainNoteComponent getSuitableNote(MainNoteComponent previousNote) {
		int octaveShift = previousNote.getOctaveShift();
		//for chord tone just choose a random one
		List<MainNoteComponent> suitableNotes = getSuitableNoteList(octaveShift);
		Collections.shuffle(suitableNotes);
		return suitableNotes.get(0);
	}

	@Override
	public List<Integer> getIntervals() {
		return Arrays.asList(intervals);
	}

	@Override
	public Scale getScale() {
		return scale;
	}
	
}
