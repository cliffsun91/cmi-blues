package org.cliffsun.individualproject.chord;

import static org.cliffsun.individualproject.note.MainNoteComponent.mainNote;

import org.cliffsun.individualproject.keys.FDominantSeventhScale;
import org.cliffsun.individualproject.keys.Scale;
import org.cliffsun.individualproject.note.BasicNote;
import org.cliffsun.individualproject.note.ChordComponent;

public class FDominantSeventhChord extends AbstractChord {

	@Override
	public ChordComponent getChord() {
		return createChord(mainNote(BasicNote.fNatural(), -2),
						   mainNote(BasicNote.aNatural(), -2),
						   mainNote(BasicNote.eFlat(), 0));
	}

	@Override
	public Scale getAccompanyingScale() {
		return new FDominantSeventhScale();
	}
}
