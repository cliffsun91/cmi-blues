package org.cliffsun.individualproject.chord;

import static org.cliffsun.individualproject.note.MainNoteComponent.mainNote;

import org.cliffsun.individualproject.keys.BMinorSeventhFlatFiveScale;
import org.cliffsun.individualproject.keys.Scale;
import org.cliffsun.individualproject.note.BasicNote;
import org.cliffsun.individualproject.note.ChordComponent;

public class BMinorSeventhAddFlatFiveChord extends AbstractChord{

	@Override
	public ChordComponent getChord() {
		return createChord(mainNote(BasicNote.bNatural(), -1),
				   		   mainNote(BasicNote.dNatural(), -1),
				   		   mainNote(BasicNote.fNatural(), -1),
				   		   mainNote(BasicNote.aNatural(), -1));
	}

	@Override
	public Scale getAccompanyingScale() {
		return new BMinorSeventhFlatFiveScale();
	}
}
