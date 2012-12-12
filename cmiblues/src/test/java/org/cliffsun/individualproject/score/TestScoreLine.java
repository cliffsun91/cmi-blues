package org.cliffsun.individualproject.score;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.cliffsun.individualproject.bar.Bar;
import org.cliffsun.individualproject.exception.BarLengthException;
import org.cliffsun.individualproject.note.AccidentalShift;
import org.cliffsun.individualproject.note.BasicNote;
import org.cliffsun.individualproject.note.MainNoteComponent;
import org.cliffsun.individualproject.note.TimedComponent;
import org.cliffsun.individualproject.phrase.StandardTimedComponentPhrase;
import org.junit.Test;

public class TestScoreLine {

	@Test
	public void testGetAbcRepresentationForATrebleClefScoreLineWithTwoBarsReturnsCorrectly() throws BarLengthException{
		Bar bar1 = createSimpleBarWithOnePhraseWithRepeatedNotesWithDuration(BasicNote.C, AccidentalShift.Flat, 0, 1);
		Bar bar2 = createSimpleBarWithOnePhraseWithRepeatedNotesWithDuration(BasicNote.F, AccidentalShift.Sharp, 0, 1);
		
		TrebleClefScoreLine trebleScoreLine = new TrebleClefScoreLine();
		trebleScoreLine.addBarToScoreLine(bar1);
		trebleScoreLine.addBarToScoreLine(bar2);
		
		assertThat(trebleScoreLine.getAbcRepresentation(), equalTo("[V:1]_C_C_C_C |^F^F^F^F ||\n"));
		
	}
	
	@Test
	public void testGetAbcRepresentationForABassClefScoreLineWithThreeBarsReturnsCorrectly() throws BarLengthException{
		Bar bar1 = createSimpleBarWithOnePhraseWithRepeatedNotesWithDuration(BasicNote.C, AccidentalShift.Flat, -1, 1);
		Bar bar2 = createSimpleBarWithOnePhraseWithRepeatedNotesWithDuration(BasicNote.F, AccidentalShift.Sharp, -1, 1);
		Bar bar3 = createSimpleBarWithOnePhraseWithRepeatedNotesWithDuration(BasicNote.G, AccidentalShift.Natural, -1, 1);
		
		BassClefScoreLine bassScoreLine = new BassClefScoreLine();
		bassScoreLine.addBarToScoreLine(bar1);
		bassScoreLine.addBarToScoreLine(bar2);
		bassScoreLine.addBarToScoreLine(bar3);
		
		assertThat(bassScoreLine.getAbcRepresentation(), equalTo("[V:2]_C,_C,_C,_C, |^F,^F,^F,^F, |G,G,G,G, ||\n"));
		
	}
	
	public Bar createSimpleBarWithOnePhraseWithRepeatedNotesWithDuration(BasicNote n, AccidentalShift accidentalShift, int octaveShift, int duration) throws BarLengthException{
		Bar bar = new Bar();
		StandardTimedComponentPhrase phrase = new StandardTimedComponentPhrase();
		for (int i = 0; i < (1/duration)*4; i++){
			MainNoteComponent note = new MainNoteComponent(n, accidentalShift, octaveShift);
			TimedComponent timedComponent = new TimedComponent(note, 1);
			phrase.addtoComponentList(timedComponent);
		}
		bar.addToBar(phrase);
		return bar;
	}
}
