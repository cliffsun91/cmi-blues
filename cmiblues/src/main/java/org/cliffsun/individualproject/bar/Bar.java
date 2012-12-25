package org.cliffsun.individualproject.bar;

import java.util.ArrayList;

import org.cliffsun.individualproject.exception.BarLengthException;
import org.cliffsun.individualproject.phrase.Phrase;

public class Bar {

	private double timeSignature;
	private ArrayList<Phrase> barPhrases;
	
	public Bar(){
		this.timeSignature = 4.0; //default 4/4
		barPhrases = new ArrayList<Phrase>();
	}

	public Bar(ArrayList<Phrase> barPhrases){
		this.barPhrases = barPhrases;
	}
	
	public void addToBar(Phrase phrase) throws BarLengthException{
		if (!isBarDurationTooLong(phrase)){
			barPhrases.add(phrase);
		}
		else{
			String message = "Can't add note to bar due to bar limit being exceeded";
			throw new BarLengthException(message, timeSignature, getBarDuration() + phrase.getDuration());
		}
	}
	
	public double getBarDurationLimit(){
		return timeSignature;
	}
	
	private boolean isBarDurationTooLong(Phrase phrase) {
		double barDuration = getBarDuration();
		return (barDuration + phrase.getDuration()) > timeSignature;
	}

	private double getBarDuration() {
		double duration = 0;
		for (Phrase phrase : barPhrases){
			duration += phrase.getDuration();
		}
		return duration;
	}

	public ArrayList<Phrase> getBarNotes(){
		return barPhrases;
	}
	
	public String getAbcRepresentation() throws BarLengthException{
		double barDuration = getBarDuration();
		if(barDuration == timeSignature){
			String representation = "";
			for(Phrase phrase : barPhrases){
				representation += phrase.getAbcRepresentation();
			}
			return representation;
		}else{
			throw new BarLengthException(timeSignature, barDuration);
		}
	}
}
