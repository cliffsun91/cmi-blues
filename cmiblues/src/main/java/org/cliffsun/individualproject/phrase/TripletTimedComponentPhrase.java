package org.cliffsun.individualproject.phrase;

import java.util.ArrayList;

import org.cliffsun.individualproject.exception.TripletPhraseException;
import org.cliffsun.individualproject.note.TimedComponent;

public class TripletTimedComponentPhrase implements Phrase{
	
	ArrayList<TimedComponent> componentList;
	
	public TripletTimedComponentPhrase(TimedComponent c1, TimedComponent c2, TimedComponent c3) {
		this();
		componentList.add(c1);
	    componentList.add(c2);
	    componentList.add(c3);
	}
	
	public TripletTimedComponentPhrase() {
		componentList = new ArrayList<TimedComponent>();
	}
	
	public void addToTripletComponentList(TimedComponent component) throws TripletPhraseException{
		if ((componentList.size() + 1) > 3){
			throw new TripletPhraseException();
		}
		componentList.add(component);
	}
	
	@Override
	public double getDuration() {
		return 1.0;
	}

	@Override
	public ArrayList<TimedComponent> getComponentList() {
		return componentList;
	}

	@Override
	public String getAbcRepresentation() {
		String representation = "(3";
		for(int i = 0 ; i < 3; i++){
			representation += componentList.get(i).getAbcRepresentation();
		}
		return representation;
	}

}
