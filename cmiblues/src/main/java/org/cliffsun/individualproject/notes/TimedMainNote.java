package org.cliffsun.individualproject.notes;

public class TimedMainNote {

	private MainNote note;
	private double duration;
	
	public TimedMainNote(MainNote note){
		this.note = note;
		this.duration = 1;
	}
	
	public TimedMainNote(MainNote note, double duration){
		this.note = note;
		this.duration = duration;
	}
	
	public MainNote getMainNote(){
		return note;
	}
	
	public double getDuration(){
		return duration;
	}

	public String getAbcRepresentation() {
		String representation = note.getAbcRepresentation();
		if(duration < 1){
			representation += "/" + (int)(1/duration);
		}
		else if(duration > 1){
			representation += "duration";
		}
		return representation;
	}

}
