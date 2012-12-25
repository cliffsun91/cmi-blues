package org.cliffsun.individualproject.notes;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.cliffsun.individualproject.note.AccidentalShift;
import org.cliffsun.individualproject.note.BasicNote;
import org.cliffsun.individualproject.note.MainNoteComponent;
import org.junit.Test;

public class TestMainNoteComponent {

	@Test
	public void testGetRepresentationForMainNoteCFlatWithOctaveShiftPlus1ReturnsCorrectly(){
		MainNoteComponent note = new MainNoteComponent(BasicNote.C, AccidentalShift.Flat, 1);
		assertThat(note.getRepresentation(), equalTo("C+1Flat"));
	}
	
	@Test
	public void testGetRepresentationForMainNoteBFlatWithOctaveShiftMinus1ReturnsCorrectly(){
		MainNoteComponent note = new MainNoteComponent(BasicNote.B, AccidentalShift.Flat, -1);
		assertThat(note.getRepresentation(), equalTo("B-1Flat"));
	}
	
	@Test
	public void testGetAbcRepresentationForMainNoteCFlatWithOctaveShiftPlus1ReturnsCorrectly(){
		MainNoteComponent note = new MainNoteComponent(BasicNote.C, AccidentalShift.Flat, 1);
		assertThat(note.getAbcRepresentation(), equalTo("_c"));
	}
	
	@Test
	public void testGetAbcRepresentationForMainNoteBFlatWithOctaveShiftMinus1ReturnsCorrectly(){
		MainNoteComponent note = new MainNoteComponent(BasicNote.B, AccidentalShift.Flat, -1);
		assertThat(note.getAbcRepresentation(), equalTo("_B,"));
	}
	
	@Test
	public void testGetAbcRepresentationForMainNoteCSharpWithOctaveShiftMinus3ReturnsCorrectly(){
		MainNoteComponent note = new MainNoteComponent(BasicNote.C, AccidentalShift.Sharp, -3);
		assertThat(note.getAbcRepresentation(), equalTo("^C,,,"));
	}
	
}
