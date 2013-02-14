package org.cliffsun.individualproject.grammar.toneclass;

import java.util.Collections;
import java.util.List;

import org.cliffsun.individualproject.keys.Scale;
import org.cliffsun.individualproject.note.BasicNote;
import org.cliffsun.individualproject.note.MainNoteComponent;

public class ScaleTone implements Tone{

	private Scale scale;
	
	public ScaleTone(Scale scale) {
		this.scale = scale;
	}

	@Override
	public MainNoteComponent getSuitableNote(MainNoteComponent previousNote) {
		int octaveShift = previousNote.getOctaveShift();
		List<BasicNote> suitableNotes = scale.getScaleAsList();
		Collections.shuffle(suitableNotes);
		return new MainNoteComponent(suitableNotes.get(0), octaveShift);
	}

}
