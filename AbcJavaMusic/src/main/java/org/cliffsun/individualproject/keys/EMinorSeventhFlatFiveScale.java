package org.cliffsun.individualproject.keys;

import java.util.Arrays;
import java.util.List;

import org.cliffsun.individualproject.note.BasicNote;

public class EMinorSeventhFlatFiveScale implements Scale{
	
	@Override
	public List<BasicNote> getScaleAsList() {
		BasicNote e = BasicNote.eNatural();
		BasicNote fSharp = BasicNote.fSharp();
		BasicNote g = BasicNote.gNatural();
		BasicNote a = BasicNote.aNatural();
		BasicNote bFlat = BasicNote.bFlat();
		BasicNote c = BasicNote.cNatural();
		BasicNote d = BasicNote.dNatural();
		List<BasicNote> bluesCScale = Arrays.asList(e,fSharp,g,a,bFlat,c,d);
		return bluesCScale;
	}

	public static EMinorSeventhFlatFiveScale eDom7(){
		return new EMinorSeventhFlatFiveScale();
	}

	@Override
	public BasicNote getTonic() {
		return BasicNote.fNatural();
	}

}
