package org.cliffsun.individualproject.grammar.toneclass;

import org.cliffsun.individualproject.note.BasicNote;
import org.cliffsun.individualproject.note.MainNoteComponent;

public class RestTone implements Tone{

	public MainNoteComponent getRestNote() {
		return new MainNoteComponent(BasicNote.rest());
	}
	
}
