package org.cliffsun.individualproject.score;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.cliffsun.individualproject.bar.Bar;
import org.cliffsun.individualproject.duration.Duration;
import org.cliffsun.individualproject.exception.BarLengthException;
import org.cliffsun.individualproject.note.AccidentalShift;
import org.cliffsun.individualproject.note.BasicNote;
import org.cliffsun.individualproject.note.SimpleNoteEnum;
import org.cliffsun.individualproject.note.MainNoteComponent;
import org.cliffsun.individualproject.note.TimedComponent;
import org.cliffsun.individualproject.phrase.StandardTimedComponentPhrase;
import org.junit.Test;

public class TestScoreLine {

	@Test
	public void testGetAbcRepresentationForATrebleClefScoreLineWithTwoBarsReturnsCorrectly() throws BarLengthException{
		Bar bar1 = createSimpleBarWithOnePhraseWithRepeatedNotesWithDuration(BasicNote.dFlat(), 0, 1);
		Bar bar2 = createSimpleBarWithOnePhraseWithRepeatedNotesWithDuration(BasicNote.fSharp(), 0, 1);
		
		TrebleClefScoreLine trebleScoreLine = new TrebleClefScoreLine();
		trebleScoreLine.addBarToScoreLine(bar1);
		trebleScoreLine.addBarToScoreLine(bar2);
		
		assertThat(trebleScoreLine.getAbcRepresentation(), equalTo("[V:1]_D_D_D_D |^F^F^F^F ||\n"));
		
	}
	
	@Test
	public void testGetAbcRepresentationForABassClefScoreLineWithThreeBarsReturnsCorrectly() throws BarLengthException{
		Bar bar1 = createSimpleBarWithOnePhraseWithRepeatedNotesWithDuration(BasicNote.dFlat(), -1, 1);
		Bar bar2 = createSimpleBarWithOnePhraseWithRepeatedNotesWithDuration(BasicNote.fSharp(), -1, 1);
		Bar bar3 = createSimpleBarWithOnePhraseWithRepeatedNotesWithDuration(BasicNote.gNatural(), -1, 1);
		
		BassClefScoreLine bassScoreLine = new BassClefScoreLine();
		bassScoreLine.addBarToScoreLine(bar1);
		bassScoreLine.addBarToScoreLine(bar2);
		bassScoreLine.addBarToScoreLine(bar3);
		
		assertThat(bassScoreLine.getAbcRepresentation(), equalTo("[V:2]_D,_D,_D,_D, |^F,^F,^F,^F, |G,G,G,G, ||\n"));
		
	}
	
	public Bar createSimpleBarWithOnePhraseWithRepeatedNotesWithDuration(BasicNote basicNote, int octaveShift, int duration) throws BarLengthException{
		Bar bar = new Bar();
		StandardTimedComponentPhrase phrase = new StandardTimedComponentPhrase();
		for (int i = 0; i < (1/duration)*4; i++){
			MainNoteComponent note = new MainNoteComponent(basicNote, octaveShift);
			TimedComponent timedComponent = new TimedComponent(note, Duration.quarter);
			phrase.addtoComponentList(timedComponent);
		}
		bar.addToBar(phrase);
		return bar;
	}
}
