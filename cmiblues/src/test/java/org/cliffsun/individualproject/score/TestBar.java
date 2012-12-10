package org.cliffsun.individualproject.score;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.cliffsun.individualproject.bar.Bar;
import org.cliffsun.individualproject.exception.BarLengthException;
import org.cliffsun.individualproject.note.AccidentalShift;
import org.cliffsun.individualproject.note.BasicNote;
import org.cliffsun.individualproject.note.MainNoteComponent;
import org.cliffsun.individualproject.note.TimedComponent;
import org.cliffsun.individualproject.phrase.Phrase;
import org.cliffsun.individualproject.phrase.StandardTimedComponentPhrase;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class TestBar {
	
	@Rule
	public ExpectedException exception = ExpectedException.none();
	  
	@Test
	public void testGetAbcRepresentationForBarWithOneStandardPhraseWithRepeatedNotesReturnsCorrectly() throws BarLengthException{
		Bar bar = new Bar();
		StandardTimedComponentPhrase phrase = new StandardTimedComponentPhrase();
		for (int i = 0; i < 16; i++){
			MainNoteComponent note = new MainNoteComponent(BasicNote.C, AccidentalShift.Sharp);
			TimedComponent component = new TimedComponent(note, 0.25);
			phrase.addtoComponentList(component);
		}
		bar.addToBar(phrase);
		assertThat(bar.getAbcRepresentation(), equalTo("^C/4^C/4^C/4^C/4^C/4^C/4^C/4^C/4^C/4^C/4^C/4^C/4^C/4^C/4^C/4^C/4 "));
	}
	
	@Test
	public void testGetAbcRepresentationForIncompleteBarReturnsException() throws BarLengthException{
		Bar bar = new Bar();
		MainNoteComponent note = new MainNoteComponent(BasicNote.C, AccidentalShift.Sharp);
		TimedComponent timedComponent = new TimedComponent(note, 0.5);
		StandardTimedComponentPhrase phrase = new StandardTimedComponentPhrase();
		phrase.addtoComponentList(timedComponent);
		bar.addToBar(phrase);
		
		exception.expect(BarLengthException.class);
		bar.getAbcRepresentation();
		
	}
	
	@Test
	public void testAddingPhraseToBarWhichWillExceedTheDurationLimitWillReturnException() throws BarLengthException{
		Bar bar = new Bar();
		StandardTimedComponentPhrase fullPhrase = new StandardTimedComponentPhrase();
		for (int i = 0; i < 15; i++){
			MainNoteComponent note = new MainNoteComponent(BasicNote.C, AccidentalShift.Sharp);
			TimedComponent timedComponent = new TimedComponent(note, 0.25);
			fullPhrase.addtoComponentList(timedComponent);
		}
		bar.addToBar(fullPhrase);
		
		MainNoteComponent exceedLimitNote = new MainNoteComponent(BasicNote.D, AccidentalShift.Sharp);
		TimedComponent exceedLimitTimedComponent = new TimedComponent(exceedLimitNote, 1);
		StandardTimedComponentPhrase exceedLimitPhrase = new StandardTimedComponentPhrase();
		exceedLimitPhrase.addtoComponentList(exceedLimitTimedComponent);
		
		exception.expect(BarLengthException.class);
		bar.addToBar(exceedLimitPhrase);
		
	}
}
