package org.cliffsun.individualproject.chord;

import static org.cliffsun.individualproject.note.MainNoteComponent.mainNote;

import org.cliffsun.individualproject.keys.AMinorSeventhScale;
import org.cliffsun.individualproject.keys.Scale;
import org.cliffsun.individualproject.note.BasicNote;
import org.cliffsun.individualproject.note.ChordComponent;

public class AMinorSeventhChord extends AbstractChord{

	@Override
	public ChordComponent getChord() {
		return createChord(mainNote(BasicNote.aNatural(), -1),
				   		   mainNote(BasicNote.cNatural(), -1),
				   		   mainNote(BasicNote.gNatural(), -1));
	}

	@Override
	public Scale getAccompanyingScale() {
		return new AMinorSeventhScale();
	}
}
