package org.cliffsun.individualproject.note;

import java.util.List;

public interface Component {

	public String getAbcRepresentation(List<MainNoteComponent> accumAccentedNotes);
	
	public int getOctaveShift();
}
