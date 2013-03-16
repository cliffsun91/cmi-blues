package org.cliffsun.individualproject.phrase;

import java.util.ArrayList;

import org.apache.commons.math3.fraction.Fraction;
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
	public void addToPhrase(TimedComponent component){
		componentList.add(component);
	}
	
	@Override
	public Fraction getDuration() {
		Fraction fractionDuration = Fraction.ZERO;
		for (TimedComponent component : componentList){
			fractionDuration = fractionDuration.add(component.getDuration());
		}
		return fractionDuration;
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
