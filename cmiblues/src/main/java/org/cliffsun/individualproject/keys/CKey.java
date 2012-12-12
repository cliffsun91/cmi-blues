package org.cliffsun.individualproject.keys;

import java.util.Arrays;
import java.util.List;

import org.cliffsun.individualproject.note.AccidentalShift;
import org.cliffsun.individualproject.note.BasicNote;
import org.cliffsun.individualproject.note.MainNoteComponent;

public class CKey implements Key{

	@Override
	public List<MainNoteComponent> getNormalScale() {
		MainNoteComponent c = new MainNoteComponent(BasicNote.C);
		MainNoteComponent d = new MainNoteComponent(BasicNote.D);
		MainNoteComponent e = new MainNoteComponent(BasicNote.E);
		MainNoteComponent f = new MainNoteComponent(BasicNote.F);
		MainNoteComponent g = new MainNoteComponent(BasicNote.G);
		MainNoteComponent a = new MainNoteComponent(BasicNote.A);
		MainNoteComponent b = new MainNoteComponent(BasicNote.B);
		List<MainNoteComponent> normalCScale = Arrays.asList(c,d,e,f,g,a,b);
		return normalCScale;
	}
	
	@Override
	public List<MainNoteComponent> getBluesMinorScale() {
		MainNoteComponent c = new MainNoteComponent(BasicNote.C);
		MainNoteComponent eSharp = new MainNoteComponent(BasicNote.E, AccidentalShift.Sharp);
		MainNoteComponent f = new MainNoteComponent(BasicNote.F);
		MainNoteComponent fSharp = new MainNoteComponent(BasicNote.F, AccidentalShift.Sharp);
		MainNoteComponent g = new MainNoteComponent(BasicNote.G);
		MainNoteComponent bFlat = new MainNoteComponent(BasicNote.B, AccidentalShift.Flat);
		List<MainNoteComponent> bluesCScale = Arrays.asList(c,eSharp,f,fSharp,g,bFlat);
		return bluesCScale;
	}

}
