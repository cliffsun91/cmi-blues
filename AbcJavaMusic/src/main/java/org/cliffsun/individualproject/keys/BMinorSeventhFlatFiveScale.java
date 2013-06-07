package org.cliffsun.individualproject.keys;

import java.util.Arrays;
import java.util.List;

import org.cliffsun.individualproject.note.BasicNote;

public class BMinorSeventhFlatFiveScale implements Scale{
	
	@Override
	public List<BasicNote> getScaleAsList() {
		BasicNote b = BasicNote.bNatural();
		BasicNote c = BasicNote.cNatural();
		BasicNote d = BasicNote.dNatural();
		BasicNote e = BasicNote.eNatural();
		BasicNote f = BasicNote.fNatural();
		BasicNote g = BasicNote.gNatural();
		BasicNote a = BasicNote.aNatural();
		List<BasicNote> bluesCScale = Arrays.asList(b,c,d,e,f,g,a);
		return bluesCScale;
	}

	public static BMinorSeventhFlatFiveScale bMin7(){
		return new BMinorSeventhFlatFiveScale();
	}

	@Override
	public BasicNote getTonic() {
		return BasicNote.dNatural();
	}

}
