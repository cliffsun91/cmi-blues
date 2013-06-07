package org.cliffsun.individualproject.keys;

import java.util.Arrays;
import java.util.List;

import org.cliffsun.individualproject.note.BasicNote;

public class EDominantSeventhScale implements Scale{
	
	@Override
	public List<BasicNote> getScaleAsList() {
		BasicNote e = BasicNote.eNatural();
		BasicNote f = BasicNote.fNatural();
		BasicNote gSharp = BasicNote.gSharp();
		BasicNote a = BasicNote.aNatural();
		BasicNote b = BasicNote.bNatural();
		BasicNote c = BasicNote.cNatural();
		BasicNote d = BasicNote.dNatural();
		List<BasicNote> bluesCScale = Arrays.asList(e,f,gSharp,a,b,c,d);
		return bluesCScale;
	}

	public static EDominantSeventhScale eDom7(){
		return new EDominantSeventhScale();
	}

	@Override
	public BasicNote getTonic() {
		return BasicNote.fNatural();
	}

}
