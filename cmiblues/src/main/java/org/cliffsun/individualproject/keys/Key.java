package org.cliffsun.individualproject.keys;

import java.util.List;

import org.cliffsun.individualproject.note.MainNoteComponent;

public interface Key {
	
	public List<MainNoteComponent> getNormalScale();
	
	public List<MainNoteComponent> getBluesMinorScale();
}
