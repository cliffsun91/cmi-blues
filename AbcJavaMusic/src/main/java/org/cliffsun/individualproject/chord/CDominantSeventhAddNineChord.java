package org.cliffsun.individualproject.chord;

import static org.cliffsun.individualproject.note.MainNoteComponent.mainNote;

import org.cliffsun.individualproject.keys.CDominantSeventhScale;
import org.cliffsun.individualproject.keys.Scale;
import org.cliffsun.individualproject.note.BasicNote;
import org.cliffsun.individualproject.note.ChordComponent;

public class CDominantSeventhAddNineChord extends AbstractChord{

	@Override
	public ChordComponent getChord() {
		return createChord(//mainNote(BasicNote.cNatural(), -1),
				   		   mainNote(BasicNote.eNatural(), -1),
				   		   mainNote(BasicNote.bFlat(), -1),
				   		   mainNote(BasicNote.dNatural(), 0));
	}

	@Override
	public Scale getAccompanyingScale() {
		return new CDominantSeventhScale();
	}
}
