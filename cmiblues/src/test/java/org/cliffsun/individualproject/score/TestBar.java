package org.cliffsun.individualproject.score;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.cliffsun.individualproject.exception.BarLengthException;
import org.cliffsun.individualproject.note.AccidentalShift;
import org.cliffsun.individualproject.note.BasicNote;
import org.cliffsun.individualproject.note.MainNoteComponent;
import org.cliffsun.individualproject.note.TimedComponent;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class TestBar {
	
	@Rule
	public ExpectedException exception = ExpectedException.none();
	  
	@Test
	public void testGetAbcRepresentationForBarWithRepeatedNotesReturnsCorrectly() throws BarLengthException{
		Bar bar = new Bar();
		for (int i = 0; i < 16; i++){
			MainNoteComponent note = new MainNoteComponent(BasicNote.C, AccidentalShift.Sharp);
			TimedComponent timedNote = new TimedComponent(note, 0.25);
			bar.addToBar(timedNote);
		}
		assertThat(bar.getAbcRepresentation(), equalTo("^C/4^C/4^C/4^C/4^C/4^C/4^C/4^C/4^C/4^C/4^C/4^C/4^C/4^C/4^C/4^C/4"));
	}
	
	@Test
	public void testGetAbcRepresentationForIncompleteBarReturnsException() throws BarLengthException{
		Bar bar = new Bar();
		MainNoteComponent note = new MainNoteComponent(BasicNote.C, AccidentalShift.Sharp);
		TimedComponent timedNote = new TimedComponent(note, 0.5);
		bar.addToBar(timedNote);
		
		exception.expect(BarLengthException.class);
		bar.getAbcRepresentation();
		
	}
	
	@Test
	public void testAddingToBarWhichWillExceedTheDurationLimitWillReturnException() throws BarLengthException{
		Bar bar = new Bar();
		for (int i = 0; i < 15; i++){
			MainNoteComponent note = new MainNoteComponent(BasicNote.C, AccidentalShift.Sharp);
			TimedComponent timedNote = new TimedComponent(note, 0.25);
			bar.addToBar(timedNote);
		}
		MainNoteComponent exceedLimitNote = new MainNoteComponent(BasicNote.D, AccidentalShift.Sharp);
		TimedComponent exceedLimitTimedNote = new TimedComponent(exceedLimitNote, 1);
		
		exception.expect(BarLengthException.class);
		bar.addToBar(exceedLimitTimedNote);
		
	}
}
