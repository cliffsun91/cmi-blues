package org.cliffsun.individualproject.keys;

import static org.cliffsun.individualproject.note.MainNoteComponent.mainNote;

import java.util.Arrays;
import java.util.List;

import org.cliffsun.individualproject.note.BasicNote;
import org.cliffsun.individualproject.note.ChordComponent;

public class GMajorSeventhScale extends AbstractChordCreator implements Scale{
	
	@Override
	public List<BasicNote> getScaleAsList() {
		BasicNote g = BasicNote.gNatural();
		BasicNote a = BasicNote.aNatural();
		BasicNote b = BasicNote.bNatural();
		BasicNote c = BasicNote.cNatural();
		BasicNote d = BasicNote.dNatural();
		BasicNote e = BasicNote.eNatural();
		BasicNote f = BasicNote.fNatural();
		List<BasicNote> bluesCScale = Arrays.asList(g,a,b,c,d,e,f);
		return bluesCScale;
	}

	@Override
	public ChordComponent getChordBassAccompaniment() {
		return createChord(mainNote(BasicNote.gNatural(), -1),
				   		   mainNote(BasicNote.bNatural(), -1),
				   		   mainNote(BasicNote.fNatural(), 0));
	}
	
	public static GMajorSeventhScale gMaj7(){
		return new GMajorSeventhScale();
	}

	@Override
	public BasicNote getTonic() {
		return BasicNote.gNatural();
	}

}
