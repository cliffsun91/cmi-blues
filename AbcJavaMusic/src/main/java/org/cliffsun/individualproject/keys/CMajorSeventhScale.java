package org.cliffsun.individualproject.keys;

import java.util.Arrays;
import java.util.List;

import org.cliffsun.individualproject.note.BasicNote;

public class CMajorSeventhScale implements Scale{
	
	@Override
	public List<BasicNote> getScaleAsList() {
		BasicNote c = BasicNote.cNatural();
		BasicNote d = BasicNote.dNatural();
		BasicNote e = BasicNote.eNatural();
		BasicNote f = BasicNote.fNatural();
		BasicNote g = BasicNote.gNatural();
		BasicNote a = BasicNote.aNatural();
		BasicNote b = BasicNote.bNatural();
		List<BasicNote> bluesCScale = Arrays.asList(c,d,e,f,g,a,b);
		return bluesCScale;
	}

	public static CMajorSeventhScale cMaj7(){
		return new CMajorSeventhScale();
	}

	@Override
	public BasicNote getTonic() {
		return BasicNote.cNatural();
	}

}
