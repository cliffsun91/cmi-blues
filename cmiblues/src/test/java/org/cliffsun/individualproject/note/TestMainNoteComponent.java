package org.cliffsun.individualproject.note;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.cliffsun.individualproject.note.AccidentalShift;
import org.cliffsun.individualproject.note.SimpleNoteEnum;
import org.cliffsun.individualproject.note.MainNoteComponent;
import org.junit.Test;

public class TestMainNoteComponent {

	@Test
	public void testGetAbcRepresentationForMainNoteCSharpWithOctaveShiftPlus1ReturnsCorrectly(){
		MainNoteComponent note = new MainNoteComponent(BasicNote.cSharp(), 1);
		assertThat(note.getAbcRepresentation(), equalTo("^c"));
	}
	
	@Test
	public void testGetAbcRepresentationForMainNoteBFlatWithOctaveShiftMinus1ReturnsCorrectly(){
		MainNoteComponent note = new MainNoteComponent(BasicNote.bFlat(), -1);
		assertThat(note.getAbcRepresentation(), equalTo("_B,"));
	}
	
	@Test
	public void testGetAbcRepresentationForMainNoteCSharpWithOctaveShiftMinus3ReturnsCorrectly(){
		MainNoteComponent note = new MainNoteComponent(BasicNote.cSharp(), -3);
		assertThat(note.getAbcRepresentation(), equalTo("^C,,,"));
	}
	
}
