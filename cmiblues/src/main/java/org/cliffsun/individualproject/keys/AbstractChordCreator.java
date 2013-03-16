package org.cliffsun.individualproject.keys;

import org.cliffsun.individualproject.note.BasicNote;
import org.cliffsun.individualproject.note.ChordComponent;
import org.cliffsun.individualproject.note.MainNoteComponent;

public abstract class AbstractChordCreator {

	public ChordComponent createChord(MainNoteComponent ... notes){
		ChordComponent chord = new ChordComponent();
		for (MainNoteComponent note : notes){
			chord.addNoteToChordComponent(note);
		}
		return chord;
	}
}
