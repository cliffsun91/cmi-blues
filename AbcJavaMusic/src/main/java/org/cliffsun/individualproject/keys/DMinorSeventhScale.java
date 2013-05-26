package org.cliffsun.individualproject.keys;

import static org.cliffsun.individualproject.note.MainNoteComponent.mainNote;

import java.util.Arrays;
import java.util.List;

import org.cliffsun.individualproject.note.BasicNote;
import org.cliffsun.individualproject.note.ChordComponent;

public class DMinorSeventhScale extends AbstractChordCreator implements Scale{
	
	@Override
	public List<BasicNote> getScaleAsList() {
		BasicNote d = BasicNote.dNatural();
		BasicNote e = BasicNote.eNatural();
		BasicNote f = BasicNote.fNatural();
		BasicNote g = BasicNote.gNatural();
		BasicNote a = BasicNote.aNatural();
		BasicNote b = BasicNote.bNatural();
		BasicNote c = BasicNote.cNatural();
		List<BasicNote> bluesCScale = Arrays.asList(d,e,f,g,a,b,c);
		return bluesCScale;
	}

	@Override
	public ChordComponent getChordBassAccompaniment() {
		return createChord(mainNote(BasicNote.dNatural(), -1),
				   		   mainNote(BasicNote.fNatural(), -1),
				   		   mainNote(BasicNote.cNatural(), 0));
	}
	
	public static DMinorSeventhScale dMin7(){
		return new DMinorSeventhScale();
	}

	@Override
	public BasicNote getTonic() {
		return BasicNote.cNatural();
	}

}
