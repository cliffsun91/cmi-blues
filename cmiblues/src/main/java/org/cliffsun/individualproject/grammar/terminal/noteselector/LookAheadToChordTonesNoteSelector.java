package org.cliffsun.individualproject.grammar.terminal.noteselector;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.cliffsun.individualproject.grammar.toneclass.ChordTone;
import org.cliffsun.individualproject.grammar.toneclass.Tone;
import org.cliffsun.individualproject.note.MainNoteComponent;

public class LookAheadToChordTonesNoteSelector extends AbstractNoteSelector{

	public LookAheadToChordTonesNoteSelector() throws FileNotFoundException, IOException {
		super();
	}
	
	public MainNoteComponent selectNoteBasedOnCorrespondingChordTone(Tone tone, ChordTone chordTone) {
		return null;
	}

}
