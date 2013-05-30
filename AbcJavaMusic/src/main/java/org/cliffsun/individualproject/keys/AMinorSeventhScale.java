package org.cliffsun.individualproject.keys;

import java.util.Arrays;
import java.util.List;

import org.cliffsun.individualproject.note.BasicNote;

public class AMinorSeventhScale implements Scale{
	
	@Override
	public List<BasicNote> getScaleAsList() {
		BasicNote a = BasicNote.aNatural();
		BasicNote b = BasicNote.bNatural();
		BasicNote c = BasicNote.cNatural();
		BasicNote d = BasicNote.dNatural();
		BasicNote e = BasicNote.eNatural();
		BasicNote f = BasicNote.fNatural();
		BasicNote g = BasicNote.gNatural();
		List<BasicNote> bluesCScale = Arrays.asList(a,b,c,d,e,f,g);
		return bluesCScale;
	}

	public static AMinorSeventhScale aMin7(){
		return new AMinorSeventhScale();
	}

	@Override
	public BasicNote getTonic() {
		return BasicNote.dNatural();
	}

}
