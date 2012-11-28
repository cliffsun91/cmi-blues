package org.cliffsun.individualproject.keys;

import org.cliffsun.individualproject.note.AccidentalShift;
import org.cliffsun.individualproject.note.BasicNote;
import org.cliffsun.individualproject.note.MainNoteComponent;

public class CKey implements Key{

	private MainNoteComponent[] normalScale;
	
	public CKey(){
		MainNoteComponent c = new MainNoteComponent(BasicNote.C);
		MainNoteComponent d = new MainNoteComponent(BasicNote.D);
		MainNoteComponent e = new MainNoteComponent(BasicNote.E);
		MainNoteComponent f = new MainNoteComponent(BasicNote.F);
		MainNoteComponent g = new MainNoteComponent(BasicNote.G);
		MainNoteComponent a = new MainNoteComponent(BasicNote.A);
		MainNoteComponent b = new MainNoteComponent(BasicNote.B);
		MainNoteComponent[] normalCScale = {c,d,e,f,g,a,b};
		this.normalScale = normalCScale;
	}
	
	@Override
	public MainNoteComponent[] getNormalScale() {
		return normalScale;
	}

	@Override
	public MainNoteComponent[] getBluesScale() {
		MainNoteComponent c = new MainNoteComponent(BasicNote.C);
		MainNoteComponent eSharp = new MainNoteComponent(BasicNote.E, AccidentalShift.Sharp);
		MainNoteComponent f = new MainNoteComponent(BasicNote.F);
		MainNoteComponent fSharp = new MainNoteComponent(BasicNote.F, AccidentalShift.Sharp);
		MainNoteComponent g = new MainNoteComponent(BasicNote.G);
		MainNoteComponent bFlat = new MainNoteComponent(BasicNote.B, AccidentalShift.Flat);
		MainNoteComponent[] bluesCScale = {c,eSharp,f,fSharp,g,bFlat};
		return bluesCScale;
	}

}
