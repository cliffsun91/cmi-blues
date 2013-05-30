package org.cliffsun.individualproject.keys;

import java.util.Arrays;
import java.util.List;

import org.cliffsun.individualproject.note.BasicNote;

public class GDominantSeventhScale implements Scale{
	
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

	public static GDominantSeventhScale gDom7(){
		return new GDominantSeventhScale();
	}

	@Override
	public BasicNote getTonic() {
		return BasicNote.gNatural();
	}

}
