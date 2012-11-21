package org.cliffsun.individualproject.keys;

import org.cliffsun.individualproject.notes.BasicNote;
import org.cliffsun.individualproject.notes.MainNote;
import org.cliffsun.individualproject.notes.AccidentalShift;

public class CKey implements Key{

	private MainNote[] normalScale;
	
	public CKey(){
		MainNote c = new MainNote(BasicNote.C);
		MainNote d = new MainNote(BasicNote.D);
		MainNote e = new MainNote(BasicNote.E);
		MainNote f = new MainNote(BasicNote.F);
		MainNote g = new MainNote(BasicNote.G);
		MainNote a = new MainNote(BasicNote.A);
		MainNote b = new MainNote(BasicNote.B);
		MainNote[] normalCScale = {c,d,e,f,g,a,b};
		this.normalScale = normalCScale;
	}
	
	@Override
	public MainNote[] getNormalScale() {
		return normalScale;
	}

	@Override
	public MainNote[] getBluesScale() {
		MainNote c = new MainNote(BasicNote.C);
		MainNote eSharp = new MainNote(BasicNote.E, AccidentalShift.Sharp);
		MainNote f = new MainNote(BasicNote.F);
		MainNote fSharp = new MainNote(BasicNote.F, AccidentalShift.Sharp);
		MainNote g = new MainNote(BasicNote.G);
		MainNote bFlat = new MainNote(BasicNote.B, AccidentalShift.Flat);
		MainNote[] bluesCScale = {c,eSharp,f,fSharp,g,bFlat};
		return bluesCScale;
	}

}
