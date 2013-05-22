package org.cliffsun.individualproject.note;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;

import org.cliffsun.individualproject.duration.Duration;
import org.cliffsun.individualproject.note.ChordComponent;
import org.cliffsun.individualproject.note.MainNoteComponent;
import org.cliffsun.individualproject.note.TimedComponent;
import org.junit.Test;

public class TestTimedComponent {
	
	@Test
	public void testGetAbcReprForMainNoteComponentCSharpWithOctaveShiftPlus1WithQuarterDurationReturnsCorrectly(){
		MainNoteComponent note = new MainNoteComponent(BasicNote.cSharp(), 1);
		TimedComponent timedComponent = new TimedComponent(note, Duration.sixteenth);
		assertThat(timedComponent.getAbcRepresentation(new ArrayList<MainNoteComponent>()), equalTo("^c/4"));
	}
	
	@Test
	public void testGetAbcReprForMainNoteComponentBFlatWithOctaveShiftMinus1WithHalfDurationReturnsCorrectly(){
		MainNoteComponent note = new MainNoteComponent(BasicNote.bFlat(), -1);
		TimedComponent timedComponent = new TimedComponent(note, Duration.eighth);
		assertThat(timedComponent.getAbcRepresentation(new ArrayList<MainNoteComponent>()), equalTo("_B,/2"));
	}
	
	@Test
	public void testGetAbcReprForChordComponentCAndGWithOctaveShiftMinus1WithFullDurationReturnsCorrectly(){
		MainNoteComponent cNote = new MainNoteComponent(BasicNote.cNatural(), -1);
		MainNoteComponent gNote = new MainNoteComponent(BasicNote.gNatural(), -1);
		ChordComponent chord = new ChordComponent();
		chord.addNoteToChordComponent(cNote);
		chord.addNoteToChordComponent(gNote);
		TimedComponent timedComponent = new TimedComponent(chord); //defaults to duration of 1
		assertThat(timedComponent.getAbcRepresentation(new ArrayList<MainNoteComponent>()), equalTo("[C,G,]"));
	}
}
