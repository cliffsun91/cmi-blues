package org.cliffsun.individualproject.grammar.toneclass;

import org.cliffsun.individualproject.keys.Scale;
import org.cliffsun.individualproject.note.BasicNote;
import org.cliffsun.individualproject.note.MainNoteComponent;

public class RestTone implements Tone{

	private Scale scale;

	public RestTone(Scale scale) {
		this.scale = scale;
		
	}
	
	public MainNoteComponent getRestNote() {
		return new MainNoteComponent(BasicNote.rest());
	}
	
	@Override
	public Scale getScale() {
		return scale;
	}
	
}
