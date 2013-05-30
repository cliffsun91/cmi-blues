package org.cliffsun.individualproject.keys;

import java.util.Arrays;
import java.util.List;

import org.cliffsun.individualproject.note.BasicNote;

public class CDominantSeventhScale implements Scale{
	
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

	public static CDominantSeventhScale cDom7(){
		return new CDominantSeventhScale();
	}

	@Override
	public BasicNote getTonic() {
		return BasicNote.cNatural();
	}

}
