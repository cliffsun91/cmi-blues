package org.cliffsun.individualproject.score;

import java.util.ArrayList;

import org.cliffsun.individualproject.exception.BarLengthException;
import org.cliffsun.individualproject.notes.TimedMainNote;

public class Bar {

	private int timeSignature;
	private ArrayList<TimedMainNote> barNotes;
	
	public Bar(){
		this.timeSignature = 4; //default 4/4
		barNotes = new ArrayList<TimedMainNote>();
	}

	public Bar(ArrayList<TimedMainNote> barNotes){
		this.barNotes = barNotes;
	}
	
	public void addToBar(TimedMainNote note){
		if (!barDurationTooLong(note))
			barNotes.add(note);
	}
	
	private boolean barDurationTooLong(TimedMainNote note) {
		double barDuration = getBarDuration();
		return (barDuration + note.getDuration()) <= timeSignature;
	}

	private double getBarDuration() {
		double duration = 0;
		for (TimedMainNote n : barNotes){
			duration += n.getDuration();
		}
		return duration;
	}

	public ArrayList<TimedMainNote> getBarNotes(){
		return barNotes;
	}
	
	public String getAbcRepresentation() throws BarLengthException{
		double barDuration = getBarDuration();
		if(barDuration == timeSignature){
			String representation = "";
			for(TimedMainNote n : barNotes){
				representation += n.getAbcRepresentation();
			}
			return representation;
		}else{
			throw new BarLengthException(timeSignature, barDuration);
		}
	}
}
