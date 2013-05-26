package org.cliffsun.individualproject.keys;

import java.util.Arrays;
import java.util.List;

import org.cliffsun.individualproject.note.BasicNote;

public class DMinorSeventhScale implements Scale{
	
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

	public static DMinorSeventhScale dMin7(){
		return new DMinorSeventhScale();
	}

	@Override
	public BasicNote getTonic() {
		return BasicNote.cNatural();
	}

}
