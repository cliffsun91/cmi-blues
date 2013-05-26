package org.cliffsun.individualproject.keys;

import java.util.Arrays;
import java.util.List;

import org.cliffsun.individualproject.note.BasicNote;

public class CMinorBluesScale implements Scale{
	
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
	public BasicNote getTonic() {
		return BasicNote.cNatural();
	}

}
