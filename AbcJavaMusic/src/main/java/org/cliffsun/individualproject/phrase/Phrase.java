package org.cliffsun.individualproject.phrase;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math3.fraction.Fraction;
import org.cliffsun.individualproject.note.MainNoteComponent;
import org.cliffsun.individualproject.note.TimedComponent;

public interface Phrase {
	
	public void addToPhrase(TimedComponent component) throws Exception;
	
	public Fraction getDuration();
	
	public ArrayList<TimedComponent> getComponentList();
	
	public String getAbcRepresentation(List<MainNoteComponent> accumAccentedNotes);
	
	public List<MainNoteComponent> getAccumAccentedNotes();
}
