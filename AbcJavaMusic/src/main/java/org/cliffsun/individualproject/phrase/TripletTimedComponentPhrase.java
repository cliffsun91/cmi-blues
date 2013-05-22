package org.cliffsun.individualproject.phrase;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math3.fraction.Fraction;
import org.cliffsun.individualproject.duration.Duration;
import org.cliffsun.individualproject.exception.TripletPhraseException;
import org.cliffsun.individualproject.note.MainNoteComponent;
import org.cliffsun.individualproject.note.TimedComponent;

public class TripletTimedComponentPhrase extends AbstractPhrase{
	
	ArrayList<TimedComponent> componentList;
	Duration duration;
	
	public TripletTimedComponentPhrase(TimedComponent c1, TimedComponent c2, TimedComponent c3, Duration duration) {
		this();
		componentList.add(c1);
	    componentList.add(c2);
	    componentList.add(c3);
	    this.duration = duration;
	}
	
	public TripletTimedComponentPhrase() {
		componentList = new ArrayList<TimedComponent>();
	}
	
	public void addToPhrase(TimedComponent component) throws TripletPhraseException{
		if ((componentList.size() + 1) > 3){
			throw new TripletPhraseException();
		}
		componentList.add(component);
	}
	
	@Override
	public Fraction getDuration() {
		return duration.getActualDuration();
	}

	@Override
	public ArrayList<TimedComponent> getComponentList() {
		return componentList;
	}

	@Override
	public String getAbcRepresentation(List<MainNoteComponent> accumAccentedNotes) {
		addAccumAccentedNotes(accumAccentedNotes);
		String representation = "(3";
		for(int i = 0 ; i < 3; i++){
			TimedComponent t = componentList.get(i);
			addTimedComponentToAccentedNotes(t);
			representation += t.getAbcRepresentation(getAccumAccentedNotes());
		}
		return representation;
	}

}
