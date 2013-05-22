package org.cliffsun.individualproject.bar;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.cliffsun.individualproject.bar.Bar;
import org.cliffsun.individualproject.duration.Duration;
import org.cliffsun.individualproject.exception.BarLengthException;
import org.cliffsun.individualproject.note.BasicNote;
import org.cliffsun.individualproject.note.MainNoteComponent;
import org.cliffsun.individualproject.note.TimedComponent;
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
			phrase.addToPhrase(component);
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
		phrase.addToPhrase(timedComponent);
		bar.addToBar(phrase);
		
		exception.expect(BarLengthException.class);
		bar.getAbcRepresentation();
	}
	
	@Test
	public void testAddingPhraseToBarWhichWillExceedTheDurationLimitWillReturnException() throws BarLengthException{
		Bar bar = new Bar();
		StandardTimedComponentPhrase fullPhrase = new StandardTimedComponentPhrase();
		for (int i = 0; i < 15; i++){
			MainNoteComponent note = new MainNoteComponent(BasicNote.gSharp());
			TimedComponent timedComponent = new TimedComponent(note, Duration.sixteenth);
			fullPhrase.addToPhrase(timedComponent);
		}
		bar.addToBar(fullPhrase);
		
		MainNoteComponent exceedLimitNote = new MainNoteComponent(BasicNote.dSharp());
		TimedComponent exceedLimitTimedComponent = new TimedComponent(exceedLimitNote, Duration.quarter);
		StandardTimedComponentPhrase exceedLimitPhrase = new StandardTimedComponentPhrase();
		exceedLimitPhrase.addToPhrase(exceedLimitTimedComponent);
		
		exception.expect(BarLengthException.class);
		bar.addToBar(exceedLimitPhrase);
	}
	
	@Test
	public void testBarWithPhraseWithPrecedingAccentedNotesReturnsABCWithNaturalNotesWithSign() throws BarLengthException{
		Bar bar = new Bar();
		StandardTimedComponentPhrase fullPhrase = new StandardTimedComponentPhrase();
		TimedComponent cSharp = new TimedComponent(MainNoteComponent.mainNote(BasicNote.cSharp()), Duration.quarter);
		TimedComponent gSharp = new TimedComponent(MainNoteComponent.mainNote(BasicNote.gSharp()), Duration.quarter);
		TimedComponent gNatural = new TimedComponent(MainNoteComponent.mainNote(BasicNote.gNatural()), Duration.quarter);
		TimedComponent cNatural = new TimedComponent(MainNoteComponent.mainNote(BasicNote.cNatural()), Duration.quarter);
		fullPhrase.addToPhrase(cSharp);
		fullPhrase.addToPhrase(gSharp);
		fullPhrase.addToPhrase(gNatural);
		fullPhrase.addToPhrase(cNatural);
		
		bar.addToBar(fullPhrase);
		assertThat(bar.getAbcRepresentation(), equalTo("^C^G=G=C "));
		
	}
	
	@Test
	public void testBarWithTwoPhrasesWithAccentedAndNaturalNotesInDifferentPhrasesReturnsWithAppropriateSigns() throws BarLengthException{
		Bar bar = new Bar();
		StandardTimedComponentPhrase phrase1 = new StandardTimedComponentPhrase();
		StandardTimedComponentPhrase phrase2 = new StandardTimedComponentPhrase();
		StandardTimedComponentPhrase phrase3 = new StandardTimedComponentPhrase();
		TimedComponent cSharp = new TimedComponent(MainNoteComponent.mainNote(BasicNote.cSharp()), Duration.quarter);
		TimedComponent gSharp = new TimedComponent(MainNoteComponent.mainNote(BasicNote.gSharp()), Duration.quarter);
		TimedComponent gNatural = new TimedComponent(MainNoteComponent.mainNote(BasicNote.gNatural()), Duration.quarter);
		TimedComponent cNatural = new TimedComponent(MainNoteComponent.mainNote(BasicNote.cNatural()), Duration.quarter);
		phrase1.addToPhrase(cSharp);
		phrase1.addToPhrase(gSharp);
		phrase2.addToPhrase(gNatural);
		phrase3.addToPhrase(cNatural);
		
		bar.addToBar(phrase1);
		bar.addToBar(phrase2);
		bar.addToBar(phrase3);
		assertThat(bar.getAbcRepresentation(), equalTo("^C^G =G =C "));
		
	}
}
