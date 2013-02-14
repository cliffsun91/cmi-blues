package org.cliffsun.individualproject.bar;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.cliffsun.individualproject.bar.Bar;
import org.cliffsun.individualproject.duration.Duration;
import org.cliffsun.individualproject.exception.BarLengthException;
import org.cliffsun.individualproject.note.AccidentalShift;
import org.cliffsun.individualproject.note.BasicNote;
import org.cliffsun.individualproject.note.SimpleNoteEnum;
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
			MainNoteComponent note = new MainNoteComponent(BasicNote.cSharp());
			TimedComponent component = new TimedComponent(note, Duration.sixteenth);
			phrase.addtoComponentList(component);
		}
		bar.addToBar(phrase);
		assertThat(bar.getAbcRepresentation(), equalTo("^C/4^C/4^C/4^C/4^C/4^C/4^C/4^C/4^C/4^C/4^C/4^C/4^C/4^C/4^C/4^C/4 "));
	}
	
	@Test
	public void testGetAbcRepresentationForIncompleteBarReturnsException() throws BarLengthException{
		Bar bar = new Bar();
		MainNoteComponent note = new MainNoteComponent(BasicNote.cSharp());
		TimedComponent timedComponent = new TimedComponent(note, Duration.eighth);
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
			MainNoteComponent note = new MainNoteComponent(BasicNote.cSharp());
			TimedComponent timedComponent = new TimedComponent(note, Duration.sixteenth);
			fullPhrase.addtoComponentList(timedComponent);
		}
		bar.addToBar(fullPhrase);
		
		MainNoteComponent exceedLimitNote = new MainNoteComponent(BasicNote.dSharp());
		TimedComponent exceedLimitTimedComponent = new TimedComponent(exceedLimitNote, Duration.quarter);
		StandardTimedComponentPhrase exceedLimitPhrase = new StandardTimedComponentPhrase();
		exceedLimitPhrase.addtoComponentList(exceedLimitTimedComponent);
		
		exception.expect(BarLengthException.class);
		bar.addToBar(exceedLimitPhrase);
		
	}
}
