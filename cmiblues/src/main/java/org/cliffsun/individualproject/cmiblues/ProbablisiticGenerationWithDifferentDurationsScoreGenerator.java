package org.cliffsun.individualproject.cmiblues;

import static org.cliffsun.individualproject.note.TimedComponent.timedComponent;
import static org.cliffsun.individualproject.note.TiedTimedComponent.tiedTimedComponent;

import java.util.List;

import org.cliffsun.individualproject.bar.Bar;
import org.cliffsun.individualproject.duration.Duration;
import org.cliffsun.individualproject.exception.BarLengthException;
import org.cliffsun.individualproject.keys.CKey;
import org.cliffsun.individualproject.note.MainNoteComponent;
import org.cliffsun.individualproject.note.TimedComponent;
import org.cliffsun.individualproject.phrase.StandardTimedComponentPhrase;
import org.cliffsun.individualproject.score.TrebleClefScoreLine;

public class ProbablisiticGenerationWithDifferentDurationsScoreGenerator extends AbstractTrebleScoreGenerator {

	@Override
	public String generateScore() throws BarLengthException {
		CKey keyOfC = new CKey();
		List<MainNoteComponent> bluesNotes = keyOfC.getBluesMinorScale();
		
		TrebleClefScoreLine scoreLine = new TrebleClefScoreLine();
		
		int index = (int) (Math.random() * (double) bluesNotes.size()-1);
		int interval = 0;
		int direction = 1; //1 for up -1 for down
		double duration = 0.5;
		double totalDurationForBar = 0;
		double barOverflowDuration = 0;
		int barOverflowIndex = 0;
		
		for (int i = 0; i < 12; i++){
			Bar bar = new Bar();
			totalDurationForBar = 0; //reset duration for new bar
			
			double barDurationLimit = bar.getBarDurationLimit();
			
			// If our previous bar's note has gone over the bar limit, then we need to
			// finish that note off on the next bar, we use a tied note to represent the same
			// duration of the note as it was before.
			if (barOverflowDuration > 0.0){
				StandardTimedComponentPhrase phrase = new StandardTimedComponentPhrase();
				totalDurationForBar += barOverflowDuration;

				MainNoteComponent note = bluesNotes.get(barOverflowIndex);
				Duration enumDuration = convertDoubleDurationToDuration(barOverflowDuration);
				TimedComponent component = timedComponent(note, enumDuration);
				
				phrase.addtoComponentList(component);
				bar.addToBar(phrase);
				
				barOverflowIndex = 0;
				barOverflowDuration = 0;
			}
			
			while(totalDurationForBar < barDurationLimit){
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
				
				duration = randomiseDuration();
				
				MainNoteComponent note = bluesNotes.get(index);
				
				TimedComponent component;
				
				if((totalDurationForBar + duration) > barDurationLimit){
					double tempDuration = duration;
					duration = barDurationLimit - totalDurationForBar;
					barOverflowDuration = tempDuration - duration;
					barOverflowIndex = index;
//					if (barOverflowDuration >= 0.5){
//						//System.out.println("for the bar number " + (i+1) +",");
//						//System.out.println("duration is: " + duration + " and overflow is: " + barOverflowDuration);
//						//System.out.println("totalduraiton before is: " + totalDurationForBar);
//						barOverflowIndex = index;
//					}else{
//						barOverflowDuration = 0;
//						barOverflowIndex = 0;
//					}
					totalDurationForBar = bar.getBarDurationLimit(); //exit the while loop
					
					Duration abcDuration = convertDoubleDurationToDuration(duration);
					
					component = tiedTimedComponent(note, abcDuration);
				}
				else{
					totalDurationForBar += duration;
					Duration abcDuration = convertDoubleDurationToDuration(duration);
					component = timedComponent(note, abcDuration);	
				}
				
				//check if you add duration to totalDuration and that it goes over the bar limit
				//and if so then we need to split up the duration of the note so that we can 
				//finish the current bar off and have the remaining duration as a new note
				//in the next bar, we can use a tie across the bar to maintain the intended 
				//note duration.
				
				//done everything except for the tie.
				
				phrase.addtoComponentList(component);
			
				bar.addToBar(phrase);
			}
			scoreLine.addBarToScoreLine(bar);
		}
		
		return scoreLine.getAbcRepresentation();
	}



	
}
