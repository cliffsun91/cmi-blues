package org.cliffsun.individualproject.chord;

import static org.cliffsun.individualproject.note.MainNoteComponent.mainNote;

import org.cliffsun.individualproject.keys.DMinorSeventhScale;
import org.cliffsun.individualproject.keys.Scale;
import org.cliffsun.individualproject.note.BasicNote;
import org.cliffsun.individualproject.note.ChordComponent;

public class DMinorSeventhChord extends AbstractChord{

	@Override
	public ChordComponent getChord() {
		return createChord(mainNote(BasicNote.dNatural(), -1),
				   		   mainNote(BasicNote.fNatural(), -1),
				   		   mainNote(BasicNote.cNatural(), 0));
	}

	@Override
	public Scale getAccompanyingScale() {
		return new DMinorSeventhScale();
	}
}
