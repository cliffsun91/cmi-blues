package org.cliffsun.individualproject.keys;

import java.util.List;

import org.cliffsun.individualproject.note.BasicNote;
import org.cliffsun.individualproject.note.ChordComponent;

public interface Scale {
	
	public List<BasicNote> getScaleAsList();
	
	public ChordComponent getChordBassAccompaniment();
}
