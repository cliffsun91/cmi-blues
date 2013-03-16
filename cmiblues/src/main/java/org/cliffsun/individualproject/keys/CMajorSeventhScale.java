package org.cliffsun.individualproject.keys;

import static org.cliffsun.individualproject.note.MainNoteComponent.mainNote;

import java.util.Arrays;
import java.util.List;

import org.cliffsun.individualproject.note.BasicNote;
import org.cliffsun.individualproject.note.ChordComponent;

public class CMajorSeventhScale extends AbstractChordCreator implements Scale{
	
	@Override
	public List<BasicNote> getScaleAsList() {
		BasicNote c = BasicNote.cNatural();
		BasicNote d = BasicNote.dNatural();
		BasicNote e = BasicNote.eNatural();
		BasicNote f = BasicNote.fNatural();
		BasicNote g = BasicNote.gNatural();
		BasicNote a = BasicNote.aNatural();
		BasicNote bFlat = BasicNote.bFlat();
		List<BasicNote> bluesCScale = Arrays.asList(c,d,e,f,g,a,bFlat);
		return bluesCScale;
	}

	@Override
	public ChordComponent getChordBassAccompaniment() {
		return createChord(mainNote(BasicNote.cNatural(), -1),
				   		   mainNote(BasicNote.eNatural(), -1),
				   		   mainNote(BasicNote.bFlat(), -1));
	}
	
	public static CMajorSeventhScale cMaj7(){
		return new CMajorSeventhScale();
	}

}
