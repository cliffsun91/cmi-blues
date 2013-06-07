package org.cliffsun.individualproject.chord;

import static org.cliffsun.individualproject.note.MainNoteComponent.mainNote;

import org.cliffsun.individualproject.keys.EDominantSeventhScale;
import org.cliffsun.individualproject.keys.Scale;
import org.cliffsun.individualproject.note.BasicNote;
import org.cliffsun.individualproject.note.ChordComponent;

public class EDominantSeventhChord extends AbstractChord{

	@Override
	public ChordComponent getChord() {
		return createChord(mainNote(BasicNote.eNatural(), -1),
				   		   mainNote(BasicNote.gSharp(), -1),
				   		   mainNote(BasicNote.dNatural(), 0));
	}

	@Override
	public Scale getAccompanyingScale() {
		return new EDominantSeventhScale();
	}
}
