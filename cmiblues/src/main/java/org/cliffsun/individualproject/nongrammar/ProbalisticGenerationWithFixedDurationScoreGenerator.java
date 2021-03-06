package org.cliffsun.individualproject.nongrammar;

import static org.cliffsun.individualproject.note.TimedComponent.standardTimedComponent;
import static org.cliffsun.individualproject.note.TimedComponent.timedComponent;

import java.util.ArrayList;
import java.util.List;

import org.cliffsun.individualproject.bar.Bar;
import org.cliffsun.individualproject.duration.Duration;
import org.cliffsun.individualproject.exception.BarLengthException;
import org.cliffsun.individualproject.keys.CMinorBluesScale;
import org.cliffsun.individualproject.note.BasicNote;
import org.cliffsun.individualproject.note.MainNoteComponent;
import org.cliffsun.individualproject.note.TimedComponent;
import org.cliffsun.individualproject.phrase.Phrase;
import org.cliffsun.individualproject.phrase.StandardTimedComponentPhrase;
import org.cliffsun.individualproject.score.TrebleClefScoreLine;

public class ProbalisticGenerationWithFixedDurationScoreGenerator extends AbstractTrebleScoreGenerator {

	@Override
	public String generateScore() throws BarLengthException {
		CMinorBluesScale cMinorBluesScale = new CMinorBluesScale();
		List<BasicNote> bluesNotes = cMinorBluesScale.getScaleAsList();
		
		TrebleClefScoreLine scoreLine = new TrebleClefScoreLine();
		
		int index = (int) (Math.random() * (double) bluesNotes.size()-1);
		int interval = 0;
		int direction = 1; //1 for up -1 for down
		Duration duration = Duration.eighth;
		
		for (int i = 0; i < 12; i++){
			Bar bar = new Bar();
			for (int j = 0; j < (int) 4.0/duration.getDurationAsDouble(); j++){
				StandardTimedComponentPhrase phrase = new StandardTimedComponentPhrase();
					
				interval = randomiseInterval();
				
				double rand2 = Math.random();
				if(rand2 < 0.2){
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
				
				BasicNote scaleNote = bluesNotes.get(index);
				
				MainNoteComponent note = new MainNoteComponent(scaleNote, 0); //0 octave shift
				
				TimedComponent component = timedComponent(note, duration);
				
				phrase.addToPhrase(component);
			
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
			phrase.addToPhrase(standardTimedComponent(bluesNotes.get(i)));
			phrase.addToPhrase(standardTimedComponent(bluesNotes.get(i+1)));
			phrases.add(phrase);
		}
		return phrases;
	}
	
}
