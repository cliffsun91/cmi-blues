package org.cliffsun.individualproject.keys;

import static org.cliffsun.individualproject.note.MainNoteComponent.mainNote;

import java.util.Arrays;
import java.util.List;

import org.cliffsun.individualproject.note.BasicNote;
import org.cliffsun.individualproject.note.ChordComponent;

public class FMajorSeventhScale extends AbstractChordCreator implements Scale{
	
	@Override
	public List<BasicNote> getScaleAsList() {
		BasicNote f = BasicNote.fNatural();
		BasicNote g = BasicNote.gNatural();
		BasicNote a = BasicNote.aNatural();
		BasicNote bFlat = BasicNote.bFlat();
		BasicNote c = BasicNote.cNatural();
		BasicNote d = BasicNote.dNatural();
		BasicNote eFlat = BasicNote.eFlat();
		List<BasicNote> bluesCScale = Arrays.asList(f,g,a,bFlat,c,d,eFlat);
		return bluesCScale;
	}

	@Override
	public ChordComponent getChordBassAccompaniment() {
		return createChord(mainNote(BasicNote.fNatural(), -1),
						   mainNote(BasicNote.aNatural(), -1),
						   mainNote(BasicNote.eFlat(), 0));
	}
	
	public static FMajorSeventhScale fMaj7(){
		return new FMajorSeventhScale();
	}

	@Override
	public BasicNote getTonic() {
		return BasicNote.fNatural();
	}

}
