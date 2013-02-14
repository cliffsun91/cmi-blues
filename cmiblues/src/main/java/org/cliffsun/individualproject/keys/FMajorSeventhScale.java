package org.cliffsun.individualproject.keys;

import java.util.Arrays;
import java.util.List;

import org.cliffsun.individualproject.note.BasicNote;

public class FMajorSeventhScale implements Scale{
	
	@Override
	public List<BasicNote> getScaleAsList() {
		BasicNote f = BasicNote.fNatural();
		BasicNote g = BasicNote.gNatural();
		BasicNote a = BasicNote.aNatural();
		BasicNote b = BasicNote.bNatural();
		BasicNote c = BasicNote.cNatural();
		BasicNote d = BasicNote.dNatural();
		BasicNote eFlat = BasicNote.eFlat();
		List<BasicNote> bluesCScale = Arrays.asList(f,g,a,b,c,d,eFlat);
		return bluesCScale;
	}

}
