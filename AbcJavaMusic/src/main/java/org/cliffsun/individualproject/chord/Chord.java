package org.cliffsun.individualproject.chord;

import org.cliffsun.individualproject.keys.Scale;
import org.cliffsun.individualproject.note.ChordComponent;

public interface Chord {

	public ChordComponent getChord();
	public Scale getAccompanyingScale();
}
