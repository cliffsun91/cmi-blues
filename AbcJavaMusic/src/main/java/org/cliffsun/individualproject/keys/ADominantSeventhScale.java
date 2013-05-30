package org.cliffsun.individualproject.keys;

import java.util.Arrays;
import java.util.List;

import org.cliffsun.individualproject.note.BasicNote;

public class ADominantSeventhScale implements Scale{
	
	@Override
	public List<BasicNote> getScaleAsList() {
		BasicNote a = BasicNote.aNatural();
		BasicNote b = BasicNote.bNatural();
		BasicNote cSharp = BasicNote.cSharp();
		BasicNote d = BasicNote.dNatural();
		BasicNote e = BasicNote.eNatural();
		BasicNote fSharp = BasicNote.fSharp();
		BasicNote g = BasicNote.gNatural();
		List<BasicNote> bluesCScale = Arrays.asList(a,b,cSharp,d,e,fSharp,g);
		return bluesCScale;
	}

	public static ADominantSeventhScale cDom7(){
		return new ADominantSeventhScale();
	}

	@Override
	public BasicNote getTonic() {
		return BasicNote.cNatural();
	}

}
