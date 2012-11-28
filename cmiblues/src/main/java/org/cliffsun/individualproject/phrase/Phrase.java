package org.cliffsun.individualproject.phrase;

import java.util.ArrayList;

import org.cliffsun.individualproject.note.TimedComponent;

public interface Phrase {
	
	public double getLengthOfPhraseInCrotchets();
	
	public ArrayList<TimedComponent> getComponentList();
	
	public String getAbcRepresentation();
}
