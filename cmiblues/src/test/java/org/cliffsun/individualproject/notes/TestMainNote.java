package org.cliffsun.individualproject.notes;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class TestMainNote {

	@Test
	public void testGetRepresentationForMainNoteCFlatWithOctaveShiftPlus1ReturnsCorrectly(){
		MainNote note = new MainNote(BasicNote.C, AccidentalShift.Flat, 1);
		assertThat(note.getRepresentation(), equalTo("C+1Flat"));
	}
	
	@Test
	public void testGetRepresentationForMainNoteBFlatWithOctaveShiftMinus1ReturnsCorrectly(){
		MainNote note = new MainNote(BasicNote.B, AccidentalShift.Flat, -1);
		assertThat(note.getRepresentation(), equalTo("B-1Flat"));
	}
	
	@Test
	public void testGetAbcRepresentationForMainNoteCFlatWithOctaveShiftPlus1ReturnsCorrectly(){
		MainNote note = new MainNote(BasicNote.C, AccidentalShift.Flat, 1);
		assertThat(note.getAbcRepresentation(), equalTo("_c"));
	}
	
	@Test
	public void testGetAbcRepresentationForMainNoteBFlatWithOctaveShiftMinus1ReturnsCorrectly(){
		MainNote note = new MainNote(BasicNote.B, AccidentalShift.Flat, -1);
		assertThat(note.getAbcRepresentation(), equalTo("_B,"));
	}
	
	@Test
	public void testGetAbcRepresentationForMainNoteCSharpWithOctaveShiftMinus3ReturnsCorrectly(){
		MainNote note = new MainNote(BasicNote.C, AccidentalShift.Sharp, -3);
		assertThat(note.getAbcRepresentation(), equalTo("^C,,,"));
	}
}
