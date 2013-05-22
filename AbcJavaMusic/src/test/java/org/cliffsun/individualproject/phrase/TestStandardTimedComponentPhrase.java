package org.cliffsun.individualproject.phrase;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Arrays;

import org.cliffsun.individualproject.duration.Duration;
import org.cliffsun.individualproject.exception.TripletPhraseException;
import org.cliffsun.individualproject.note.BasicNote;
import org.cliffsun.individualproject.note.MainNoteComponent;
import org.cliffsun.individualproject.note.TimedComponent;
import org.junit.Test;

public class TestStandardTimedComponentPhrase {

	@Test
	public void testThatStandardPhraseWithNoPreviousAccentedNotesInBarReturnsReprCorrectly() throws TripletPhraseException{
		TimedComponent cSharp = new TimedComponent(MainNoteComponent.mainNote(BasicNote.cSharp()), Duration.quarter);
		TimedComponent dSharp = new TimedComponent(MainNoteComponent.mainNote(BasicNote.dSharp()), Duration.quarter);
		TimedComponent cNatural = new TimedComponent(MainNoteComponent.mainNote(BasicNote.cNatural()), Duration.quarter);
		
		StandardTimedComponentPhrase phrase = new StandardTimedComponentPhrase();
		phrase.addToPhrase(cSharp, dSharp, cNatural);
		
		assertThat(phrase.getAbcRepresentation(new ArrayList<MainNoteComponent>()), equalTo("^C^D=C "));
	}
	
	@Test
	public void testThatStandardPhraseWithPreviousAccentedNotesInBarReturnsReprCorrectly() throws TripletPhraseException{
		TimedComponent cSharp = new TimedComponent(MainNoteComponent.mainNote(BasicNote.cSharp()), Duration.quarter);
		TimedComponent cNatural = new TimedComponent(MainNoteComponent.mainNote(BasicNote.cNatural()), Duration.quarter);
		TimedComponent eNatural = new TimedComponent(MainNoteComponent.mainNote(BasicNote.eNatural()), Duration.quarter);
		TimedComponent fNatural = new TimedComponent(MainNoteComponent.mainNote(BasicNote.fNatural()), Duration.quarter);

		MainNoteComponent prevEFlat = MainNoteComponent.mainNote(BasicNote.eFlat());
		MainNoteComponent prevFSharp = MainNoteComponent.mainNote(BasicNote.fSharp());
		
		StandardTimedComponentPhrase phrase = new StandardTimedComponentPhrase();
		phrase.addToPhrase(cSharp, cNatural, eNatural, fNatural);
		
		assertThat(phrase.getAbcRepresentation(Arrays.asList(prevEFlat, prevFSharp)), equalTo("^C=C=E=F "));
	}
	
}