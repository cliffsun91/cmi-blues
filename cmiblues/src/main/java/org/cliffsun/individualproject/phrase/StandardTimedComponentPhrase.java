package org.cliffsun.individualproject.phrase;

import java.util.ArrayList;

import org.cliffsun.individualproject.note.TimedComponent;

public class StandardTimedComponentPhrase implements Phrase{

	ArrayList<TimedComponent> componentList;
	
	public StandardTimedComponentPhrase() {
		this.componentList = new ArrayList<TimedComponent>();
	}
	
	public StandardTimedComponentPhrase(ArrayList<TimedComponent> componentList) {
		this.componentList = componentList;
	}
	
	@Override
	public double getLengthOfPhraseInCrotchets() {
		int duration = 0;
		for (TimedComponent t : componentList){
			duration += t.getDuration();
		}
		return duration;
	}

	@Override
	public ArrayList<TimedComponent> getComponentList() {
		return componentList;
	}

	@Override
	public String getAbcRepresentation() {
		String representation = "";
		for (TimedComponent t : componentList){
			representation += t.getAbcRepresentation();
		}
		return representation + " "; //add a gap to differentiate phrases
	}

}
