package org.cliffsun.individualproject.keys;

import java.util.Arrays;
import java.util.List;

import org.cliffsun.individualproject.note.BasicNote;

public class EDominantSeventhFlatNineScale implements Scale{
	
	@Override
	public List<BasicNote> getScaleAsList() {
		BasicNote e = BasicNote.eNatural();
		BasicNote f = BasicNote.fNatural();
		BasicNote gSharp = BasicNote.gSharp();
		BasicNote a = BasicNote.aNatural();
		BasicNote b = BasicNote.bNatural();
		BasicNote cSharp = BasicNote.cSharp();
		BasicNote d = BasicNote.dNatural();
		List<BasicNote> bluesCScale = Arrays.asList(e,f,gSharp,a,b,cSharp,d);
		return bluesCScale;
	}

	public static EDominantSeventhFlatNineScale eDom7(){
		return new EDominantSeventhFlatNineScale();
	}

	@Override
	public BasicNote getTonic() {
		return BasicNote.fNatural();
	}

}
