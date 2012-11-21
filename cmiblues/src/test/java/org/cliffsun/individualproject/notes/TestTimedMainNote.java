package org.cliffsun.individualproject.notes;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Assert;
import org.junit.Test;
import org.hamcrest.CoreMatchers;

public class TestTimedMainNote {
	
	@Test
	public void testGetAbcReprForMainNoteCFlatWithOctaveShiftPlus1WithQuarterDurationReturnsCorrectly(){
		MainNote note = new MainNote(BasicNote.C, AccidentalShift.Flat, 1);
		TimedMainNote timedNote = new TimedMainNote(note, 0.25);
		assertThat(timedNote.getAbcRepresentation(), equalTo("_c/4"));
	}
	
	@Test
	public void testGetAbcReprForMainNoteBFlatWithOctaveShiftMinus1WithHalfDurationReturnsCorrectly(){
		MainNote note = new MainNote(BasicNote.B, AccidentalShift.Flat, -1);
		TimedMainNote timedNote = new TimedMainNote(note, 0.5);
		assertThat(timedNote.getAbcRepresentation(), equalTo("_B,/2"));
	}
	
	@Test
	public void testGetAbcReprForMainNoteBFlatWithOctaveShiftMinus1WithAbnormalDurationReturnsFlooredDuration(){
		MainNote note = new MainNote(BasicNote.B, AccidentalShift.Flat, -1);
		TimedMainNote timedNote = new TimedMainNote(note, 0.37);
		assertThat(timedNote.getAbcRepresentation(), equalTo("_B,/2"));
	}
}
