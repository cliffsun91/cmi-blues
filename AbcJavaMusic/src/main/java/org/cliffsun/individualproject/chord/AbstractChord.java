package org.cliffsun.individualproject.chord;

import org.cliffsun.individualproject.note.ChordComponent;
import org.cliffsun.individualproject.note.MainNoteComponent;

public abstract class AbstractChord implements Chord{

	public ChordComponent createChord(MainNoteComponent ... notes){
		ChordComponent chord = new ChordComponent();
		for (MainNoteComponent note : notes){
			chord.addNoteToChordComponent(note);
		}
		return chord;
	}
}
