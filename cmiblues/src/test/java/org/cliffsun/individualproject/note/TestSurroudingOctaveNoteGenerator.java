package org.cliffsun.individualproject.note;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class TestSurroudingOctaveNoteGenerator {

	SurroundingOctaveNoteGenerator generator;
	
	@Before
	public void setup(){
		generator = new SurroundingOctaveNoteGenerator();
	}
	
	@Test
	public void testGetSurroundingOctaveNotesReturnsSameNotesOctaveAboveAndBelow(){
		List<BasicNote> notes = Arrays.asList(BasicNote.cNatural(), BasicNote.gNatural());
		List<MainNoteComponent> result = generator.generateOneOctaveUpAndDownMainNotesForTrebleClef(notes, 1);
		List<MainNoteComponent> expected = Arrays.asList(new MainNoteComponent(BasicNote.cNatural(), 0),
														 new MainNoteComponent(BasicNote.cNatural(), 1),
														 new MainNoteComponent(BasicNote.cNatural(), 2),
														 new MainNoteComponent(BasicNote.gNatural(), 0),
														 new MainNoteComponent(BasicNote.gNatural(), 1),
														 new MainNoteComponent(BasicNote.gNatural(), 2));
		assertThat(result, equalTo(expected));
	}
	
	
	@Test
	public void testGetSurroundingOctaveNotesForHighOctaveDoesNotReturnAboveTheNoteLimit(){
		List<BasicNote> notes = Arrays.asList(BasicNote.cNatural(), BasicNote.gNatural());
		List<MainNoteComponent> result = generator.generateOneOctaveUpAndDownMainNotesForTrebleClef(notes, 2);
		List<MainNoteComponent> expected = Arrays.asList(new MainNoteComponent(BasicNote.cNatural(), 1),
														 new MainNoteComponent(BasicNote.cNatural(), 2),
														 new MainNoteComponent(BasicNote.cNatural(), 3),
														 new MainNoteComponent(BasicNote.gNatural(), 1),
														 new MainNoteComponent(BasicNote.gNatural(), 2));
		assertThat(result, equalTo(expected));
	}
	
	@Test
	public void testGetSurroundingOctaveNotesForLowOctaveDoesNotReturnAboveTheNoteLimit(){
		List<BasicNote> notes = Arrays.asList(BasicNote.cNatural(), BasicNote.gNatural());
		List<MainNoteComponent> result = generator.generateOneOctaveUpAndDownMainNotesForTrebleClef(notes, 0);
		List<MainNoteComponent> expected = Arrays.asList(new MainNoteComponent(BasicNote.cNatural(), 0),
														 new MainNoteComponent(BasicNote.cNatural(), 1),
														 new MainNoteComponent(BasicNote.gNatural(), 0),
														 new MainNoteComponent(BasicNote.gNatural(), 1));
		assertThat(result, equalTo(expected));
	}
}
