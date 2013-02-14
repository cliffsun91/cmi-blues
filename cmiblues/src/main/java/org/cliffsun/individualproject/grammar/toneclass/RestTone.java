package org.cliffsun.individualproject.grammar.toneclass;

import org.cliffsun.individualproject.note.BasicNote;
import org.cliffsun.individualproject.note.MainNoteComponent;

public class RestTone implements Tone{

	@Override
	public MainNoteComponent getSuitableNote(MainNoteComponent previousNote) {
		return new MainNoteComponent(BasicNote.rest());
	}
	
}
