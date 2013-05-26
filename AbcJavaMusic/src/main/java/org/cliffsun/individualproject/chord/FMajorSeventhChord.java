package org.cliffsun.individualproject.chord;

import static org.cliffsun.individualproject.note.MainNoteComponent.mainNote;

import org.cliffsun.individualproject.keys.FMajorSeventhScale;
import org.cliffsun.individualproject.keys.Scale;
import org.cliffsun.individualproject.note.BasicNote;
import org.cliffsun.individualproject.note.ChordComponent;

public class FMajorSeventhChord extends AbstractChord {

	@Override
	public ChordComponent getChord() {
		return createChord(mainNote(BasicNote.fNatural(), -1),
						   mainNote(BasicNote.aNatural(), -1),
						   mainNote(BasicNote.eFlat(), 0));
	}

	@Override
	public Scale getAccompanyingScale() {
		return new FMajorSeventhScale();
	}
}
