package org.cliffsun.individualproject.cmiblues;

import static org.cliffsun.individualproject.note.TimedComponent.standardTimedComponent;
import static org.cliffsun.individualproject.note.TimedComponent.timedComponent;

import java.util.ArrayList;
import java.util.List;

import org.cliffsun.individualproject.bar.Bar;
import org.cliffsun.individualproject.exception.BarLengthException;
import org.cliffsun.individualproject.keys.CKey;
import org.cliffsun.individualproject.note.MainNoteComponent;
import org.cliffsun.individualproject.note.TimedComponent;
import org.cliffsun.individualproject.phrase.Phrase;
import org.cliffsun.individualproject.phrase.StandardTimedComponentPhrase;
import org.cliffsun.individualproject.score.TrebleClefScoreLine;

public class TrebleScoreGenerator {

	public String generateScore() throws BarLengthException {
		CKey keyOfC = new CKey();
		List<MainNoteComponent> bluesNotes = keyOfC.getBluesMinorScale();
		
		TrebleClefScoreLine scoreLine = new TrebleClefScoreLine();
		
		int index = 0;
		int interval = 0;
		int direction = 1; //1 for up -1 for down
		int octave = 0; //initially on the octave above middle C
		double duration = 1.0;
		TimedComponent previousComponent;
		
		for (int i = 0; i < 12; i++){
			Bar bar = new Bar();
			for (int j = 0; j < 2; j++){
				duration = 1;
				//generate pairs of notes
				StandardTimedComponentPhrase phrase = new StandardTimedComponentPhrase();
				for(int k = 0; k < 2; k++){
					//int index = (int) (Math.random() * (double) bluesNotes.size()-1);
					double rand = Math.random();
					if(rand >= 0 && rand < 0.5){
						interval = 1;
					}
					else if (rand >= 0.5 && rand < 0.8){
						interval = 2;
					}
					else if (rand >= 0.8 && rand < 1.0){
						interval = 3;
					}
					
					double rand2 = Math.random();
					if(rand2 < 0.3){
						direction *= -1;
					}
					
					int directedInterval = interval*direction;
					
					index += directedInterval;
					
					if (index > (bluesNotes.size() - 1)){
						index = index % bluesNotes.size();
					}
					else if (index < 0) {
						index = bluesNotes.size() + index;
					}
					
					MainNoteComponent note = bluesNotes.get(index);
					
					//note.setOctaveShift(octave);
					
					//randomly introduce random durations for notes like half beats, 
					//then the next note should follow with the rest of the duration 
					//to fulfill 2 beats.
					TimedComponent component;
					
					
					if(duration != 1){
						//use nextDuration as duration
						component = timedComponent(note, duration);
						duration = 1;
					}
					else {
						//could give a small duration for the second note of a pair, 
						//thats not what we want
						if(k == 0){
							double durationRand = Math.random();
							//small chance that we have a smaller duration
							if(durationRand < 0.1){
								duration = 0.5;
							}
							else if(durationRand >= 0.1 && durationRand < 0.15){
								duration = 0.25;
							}
							System.out.println("small duration: " + duration);
						}
						component = timedComponent(note, duration);
						duration = 2 - duration;
						System.out.println("next duration: " + duration);
					}
					
					phrase.addtoComponentList(component);
				}
				bar.addToBar(phrase);
			}
			scoreLine.addBarToScoreLine(bar);
		}
		
		return scoreLine.getAbcRepresentation();
	}

	private List<Phrase> generateTwoNotePhrases(List<MainNoteComponent> bluesNotes) {
		List<Phrase> phrases = new ArrayList<Phrase>();
		for (int i = 0; i < bluesNotes.size()-1; i++){
			StandardTimedComponentPhrase phrase = new StandardTimedComponentPhrase();
			phrase.addtoComponentList(standardTimedComponent(bluesNotes.get(i)));
			phrase.addtoComponentList(standardTimedComponent(bluesNotes.get(i+1)));
			phrases.add(phrase);
		}
		return phrases;
	}
	
}
