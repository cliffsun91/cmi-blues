package org.cliffsun.individualproject.notes;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.cliffsun.individualproject.duration.Duration;
import org.cliffsun.individualproject.note.AccidentalShift;
import org.cliffsun.individualproject.note.BasicNote;
import org.cliffsun.individualproject.note.ChordComponent;
import org.cliffsun.individualproject.note.Component;
import org.cliffsun.individualproject.note.MainNoteComponent;
import org.cliffsun.individualproject.note.TimedComponent;
import org.junit.Test;

public class TestTimedComponent {
	
	@Test
	public void testGetAbcReprForMainNoteComponentCFlatWithOctaveShiftPlus1WithQuarterDurationReturnsCorrectly(){
		MainNoteComponent note = new MainNoteComponent(BasicNote.C, AccidentalShift.Flat, 1);
		TimedComponent timedComponent = new TimedComponent(note, Duration.sixteenth);
		assertThat(timedComponent.getAbcRepresentation(), equalTo("_c/4"));
	}
	
	@Test
	public void testGetAbcReprForMainNoteComponentBFlatWithOctaveShiftMinus1WithHalfDurationReturnsCorrectly(){
		MainNoteComponent note = new MainNoteComponent(BasicNote.B, AccidentalShift.Flat, -1);
		TimedComponent timedComponent = new TimedComponent(note, Duration.eigth);
		assertThat(timedComponent.getAbcRepresentation(), equalTo("_B,/2"));
	}
	
	@Test
	public void testGetAbcReprForChordComponentCAndGWithOctaveShiftMinus1WithFullDurationReturnsCorrectly(){
		MainNoteComponent cNote = new MainNoteComponent(BasicNote.C, -1);
		MainNoteComponent gNote = new MainNoteComponent(BasicNote.G, -1);
		ChordComponent chord = new ChordComponent();
		chord.addNoteToChordComponent(cNote);
		chord.addNoteToChordComponent(gNote);
		TimedComponent timedComponent = new TimedComponent(chord); //defaults to duration of 1
		assertThat(timedComponent.getAbcRepresentation(), equalTo("[C,G,]"));
	}
}
