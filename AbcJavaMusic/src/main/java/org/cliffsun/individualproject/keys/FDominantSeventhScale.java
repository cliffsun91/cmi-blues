package org.cliffsun.individualproject.keys;

import java.util.Arrays;
import java.util.List;

import org.cliffsun.individualproject.note.BasicNote;

public class FDominantSeventhScale implements Scale{
	
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

	public static FDominantSeventhScale fDom7(){
		return new FDominantSeventhScale();
	}

	@Override
	public BasicNote getTonic() {
		return BasicNote.fNatural();
	}

}
