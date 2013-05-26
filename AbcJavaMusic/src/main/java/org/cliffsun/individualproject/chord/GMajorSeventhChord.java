package org.cliffsun.individualproject.chord;

import static org.cliffsun.individualproject.note.MainNoteComponent.mainNote;

import org.cliffsun.individualproject.keys.GMajorSeventhScale;
import org.cliffsun.individualproject.keys.Scale;
import org.cliffsun.individualproject.note.BasicNote;
import org.cliffsun.individualproject.note.ChordComponent;

public class GMajorSeventhChord extends AbstractChord{

	@Override
	public ChordComponent getChord() {
		return createChord(mainNote(BasicNote.gNatural(), -1),
						   mainNote(BasicNote.bNatural(), -1),
						   mainNote(BasicNote.fNatural(), 0));
	}

	@Override
	public Scale getAccompanyingScale() {
		return new GMajorSeventhScale();
	}
}
