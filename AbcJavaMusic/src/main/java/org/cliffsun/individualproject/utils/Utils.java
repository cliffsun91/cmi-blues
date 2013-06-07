package org.cliffsun.individualproject.utils;

import org.cliffsun.individualproject.note.BasicNote;
import org.cliffsun.individualproject.note.MainNoteComponent;

public class Utils {

	public final static int octaveInterval = 12;
	public final static int minTrebleOctave = 0;
	public final static int maxTrebleOctave = 3;
	public final static MainNoteComponent minTrebleNote = new MainNoteComponent(BasicNote.cNatural(), minTrebleOctave);
	public final static MainNoteComponent maxTrebleNote = new MainNoteComponent(BasicNote.cNatural(), maxTrebleOctave);
}
