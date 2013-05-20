package org.cliffsun.individualproject.keys;

import static org.cliffsun.individualproject.note.MainNoteComponent.mainNote;

import java.util.Arrays;
import java.util.List;

import org.cliffsun.individualproject.note.BasicNote;
import org.cliffsun.individualproject.note.ChordComponent;

public class CMinorBluesScale extends AbstractChordCreator implements Scale{
	
	@Override
	public List<BasicNote> getScaleAsList() {
		BasicNote c = BasicNote.cNatural();
		BasicNote eFlat = BasicNote.eFlat();
		BasicNote f = BasicNote.fNatural();
		BasicNote fSharp = BasicNote.fSharp();
		BasicNote g = BasicNote.gNatural();
		BasicNote bFlat = BasicNote.bFlat();
		BasicNote bNatural = BasicNote.bNatural();
		List<BasicNote> bluesCScale = Arrays.asList(c,eFlat,f,fSharp,g,bFlat, bNatural);
		return bluesCScale;
	}

	@Override
	public ChordComponent getChordBassAccompaniment() {
		// C Minor chord
		return createChord(mainNote(BasicNote.cNatural(), -1),
						   mainNote(BasicNote.eFlat(), -1),
						   mainNote(BasicNote.gNatural(), -1));
	}

	@Override
	public BasicNote getTonic() {
		return BasicNote.cNatural();
	}

}
