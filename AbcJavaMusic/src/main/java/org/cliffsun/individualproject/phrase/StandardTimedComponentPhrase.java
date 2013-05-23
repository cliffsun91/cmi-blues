package org.cliffsun.individualproject.phrase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.math3.fraction.Fraction;
import org.cliffsun.individualproject.note.MainNoteComponent;
import org.cliffsun.individualproject.note.TimedComponent;

public class StandardTimedComponentPhrase extends AbstractPhrase{

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
	
	public void addToPhrase(TimedComponent ... components){
		componentList.addAll(Arrays.asList(components));
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
	public String getAbcRepresentation(List<MainNoteComponent> accumAccentedNotes) {
		addAccumAccentedNotes(accumAccentedNotes);
		String representation = "";
		for (TimedComponent t : componentList){
			addTimedComponentToAccentedNotes(t);
			representation += t.getAbcRepresentation(getAccumAccentedNotes());
		}
		return representation + " "; //add a gap to differentiate phrases
	}

}
