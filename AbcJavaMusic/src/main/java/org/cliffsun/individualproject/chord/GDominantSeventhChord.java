package org.cliffsun.individualproject.chord;

import static org.cliffsun.individualproject.note.MainNoteComponent.mainNote;

import org.cliffsun.individualproject.keys.GDominantSeventhScale;
import org.cliffsun.individualproject.keys.Scale;
import org.cliffsun.individualproject.note.BasicNote;
import org.cliffsun.individualproject.note.ChordComponent;

public class GDominantSeventhChord extends AbstractChord{

	@Override
	public ChordComponent getChord() {
		return createChord(mainNote(BasicNote.gNatural(), -2),
						   mainNote(BasicNote.bNatural(), -2),
						   mainNote(BasicNote.fNatural(), -1));
	}

	@Override
	public Scale getAccompanyingScale() {
		return new GDominantSeventhScale();
	}
}
