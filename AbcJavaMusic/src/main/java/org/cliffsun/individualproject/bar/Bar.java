package org.cliffsun.individualproject.bar;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math3.fraction.Fraction;
import org.cliffsun.individualproject.exception.BarLengthException;
import org.cliffsun.individualproject.note.MainNoteComponent;
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
			throw new BarLengthException(message, timeSignature, getBarDuration() + phrase.getDuration().doubleValue());
		}
	}
	
	public double getBarDurationLimit(){
		return timeSignature;
	}
	
	private boolean isBarDurationTooLong(Phrase phrase) {
		Fraction barDuration = getBarDurationAsFraction();
		return (barDuration.add(phrase.getDuration())).doubleValue() > timeSignature;
	}

	private Fraction getBarDurationAsFraction(){
		Fraction fractionDuration = Fraction.ZERO;
		for (Phrase phrase : barPhrases){
			fractionDuration = fractionDuration.add(phrase.getDuration());
		}
		return fractionDuration;
	}
	
	public double getBarDuration() {
		return getBarDurationAsFraction().doubleValue();
	}

	public ArrayList<Phrase> getPhrases(){
		return barPhrases;
	}
	
	public String getAbcRepresentation() throws BarLengthException{
		double barDuration = getBarDuration();
		List<MainNoteComponent> accumAccentedNotes = new ArrayList<MainNoteComponent>();
		if(barDuration == timeSignature){
			String representation = "";
			for(Phrase phrase : barPhrases){
				representation += phrase.getAbcRepresentation(accumAccentedNotes);
				accumAccentedNotes = phrase.getAccumAccentedNotes();
			}
			return representation;
		}else{
			for(Phrase p : barPhrases){
				System.out.println(p.getAbcRepresentation(accumAccentedNotes));
			}
			throw new BarLengthException(timeSignature, barDuration);
		}
	}
}
