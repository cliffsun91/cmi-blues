package org.cliffsun.individualproject.chord;

import static org.cliffsun.individualproject.note.MainNoteComponent.mainNote;

import org.cliffsun.individualproject.keys.CMajorSeventhScale;
import org.cliffsun.individualproject.keys.Scale;
import org.cliffsun.individualproject.note.BasicNote;
import org.cliffsun.individualproject.note.ChordComponent;

public class CMajorSeventhChord extends AbstractChord{

	@Override
	public ChordComponent getChord() {
		return createChord(mainNote(BasicNote.cNatural(), -1),
				   		   mainNote(BasicNote.eNatural(), -1),
				   		   mainNote(BasicNote.bFlat(), -1));
	}

	@Override
	public Scale getAccompanyingScale() {
		return new CMajorSeventhScale();
	}
}
