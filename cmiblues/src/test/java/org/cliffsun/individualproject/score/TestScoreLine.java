package org.cliffsun.individualproject.score;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.cliffsun.individualproject.exception.BarLengthException;
import org.cliffsun.individualproject.notes.AccidentalShift;
import org.cliffsun.individualproject.notes.BasicNote;
import org.cliffsun.individualproject.notes.MainNote;
import org.cliffsun.individualproject.notes.TimedMainNote;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

public class TestScoreLine {

	@Test
	public void testGetAbcRepresentationForATrebleClefScoreLineWithTwoBarsReturnsCorrectly() throws BarLengthException{
		Bar bar1 = createSimpleBarWithRepeatedNotesWithDuration(BasicNote.C, AccidentalShift.Flat, 0, 1);
		Bar bar2 = createSimpleBarWithRepeatedNotesWithDuration(BasicNote.F, AccidentalShift.Sharp, 0, 1);
		
		TrebleClefScoreLine trebleScoreLine = new TrebleClefScoreLine();
		trebleScoreLine.addBarToScoreLine(bar1);
		trebleScoreLine.addBarToScoreLine(bar2);
		
		assertThat(trebleScoreLine.getAbcRepresentation(), equalTo("[V:1]_C_C_C_C|^F^F^F^F||"));
		
	}
	
	@Test
	public void testGetAbcRepresentationForABassClefScoreLineWithThreeBarsReturnsCorrectly() throws BarLengthException{
		Bar bar1 = createSimpleBarWithRepeatedNotesWithDuration(BasicNote.C, AccidentalShift.Flat, -1, 1);
		Bar bar2 = createSimpleBarWithRepeatedNotesWithDuration(BasicNote.F, AccidentalShift.Sharp, -1, 1);
		Bar bar3 = createSimpleBarWithRepeatedNotesWithDuration(BasicNote.G, AccidentalShift.Natural, -1, 1);
		
		TrebleClefScoreLine trebleScoreLine = new TrebleClefScoreLine();
		trebleScoreLine.addBarToScoreLine(bar1);
		trebleScoreLine.addBarToScoreLine(bar2);
		trebleScoreLine.addBarToScoreLine(bar3);
		
		assertThat(trebleScoreLine.getAbcRepresentation(), equalTo("[V:1]_C,_C,_C,_C,|^F,^F,^F,^F,|G,G,G,G,||"));
		
	}
	
	public Bar createSimpleBarWithRepeatedNotesWithDuration(BasicNote n, AccidentalShift accidentalShift, int octaveShift, int duration) throws BarLengthException{
		Bar bar = new Bar();
		for (int i = 0; i < (1/duration)*4; i++){
			MainNote note = new MainNote(n, accidentalShift, octaveShift);
			TimedMainNote timedNote = new TimedMainNote(note, 1);
			bar.addToBar(timedNote);
		}
		return bar;
	}
}