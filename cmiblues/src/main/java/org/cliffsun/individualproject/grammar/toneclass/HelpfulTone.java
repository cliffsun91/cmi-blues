package org.cliffsun.individualproject.grammar.toneclass;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.cliffsun.individualproject.keys.Scale;
import org.cliffsun.individualproject.note.MainNoteComponent;

public class HelpfulTone implements Tone {

	private Scale scale;
	
	public HelpfulTone(Scale scale) {
		this.scale = scale;
	}
	
	@Override
	public MainNoteComponent getSuitableNote(MainNoteComponent previousNote) {
		MainNoteComponent note1 = (new ChordTone(scale)).getSuitableNote(previousNote);
		MainNoteComponent note2 = (new ColourTone(scale)).getSuitableNote(previousNote);
		MainNoteComponent note3 = (new ApproachTone(scale)).getSuitableNote(previousNote);

		List<MainNoteComponent> suitableNotes = Arrays.asList(note1, note2, note3);

		Collections.shuffle(suitableNotes);
		return suitableNotes.get(0);
	}

}
