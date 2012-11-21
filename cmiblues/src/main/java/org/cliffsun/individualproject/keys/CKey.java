package org.cliffsun.individualproject.keys;

import org.cliffsun.individualproject.notes.BasicNote;
import org.cliffsun.individualproject.notes.Note;
import org.cliffsun.individualproject.notes.Shift;

public class CKey implements Key{

	private Note[] normalScale;
	
	public CKey(){
		Note c = new Note(BasicNote.C);
		Note d = new Note(BasicNote.D);
		Note e = new Note(BasicNote.E);
		Note f = new Note(BasicNote.F);
		Note g = new Note(BasicNote.G);
		Note a = new Note(BasicNote.A);
		Note b = new Note(BasicNote.B);
		Note[] normalCScale = {c,d,e,f,g,a,b};
		this.normalScale = normalCScale;
	}
	
	@Override
	public Note[] getNormalScale() {
		return normalScale;
	}

	@Override
	public Note[] getBluesScale() {
		Note c = new Note(BasicNote.C);
		Note eSharp = new Note(BasicNote.E, Shift.Sharp);
		Note f = new Note(BasicNote.F);
		Note fSharp = new Note(BasicNote.F, Shift.Sharp);
		Note g = new Note(BasicNote.G);
		Note bFlat = new Note(BasicNote.B, Shift.Flat);
		Note[] bluesCScale = {c,eSharp,f,fSharp,g,bFlat};
		return bluesCScale;
	}

}
