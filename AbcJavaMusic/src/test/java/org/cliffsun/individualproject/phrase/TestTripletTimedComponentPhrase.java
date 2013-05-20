package org.cliffsun.individualproject.phrase;

import static org.cliffsun.individualproject.note.TimedComponent.standardTimedComponent;
import static org.hamcrest.CoreMatchers.equalTo;

import org.cliffsun.individualproject.duration.Duration;
import org.cliffsun.individualproject.exception.TripletPhraseException;
import org.cliffsun.individualproject.note.AccidentalShift;
import org.cliffsun.individualproject.note.BasicNote;
import org.cliffsun.individualproject.note.SimpleNoteEnum;
import org.cliffsun.individualproject.note.MainNoteComponent;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class TestTripletTimedComponentPhrase {

	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	@Test
	public void testThatTripletPhraseComponentListWithFourComponentsReturnsException() throws TripletPhraseException{
		MainNoteComponent c1 = new MainNoteComponent(BasicNote.cNatural());
		MainNoteComponent c2 = new MainNoteComponent(BasicNote.dNatural());
		MainNoteComponent c3 = new MainNoteComponent(BasicNote.eNatural());
		
		MainNoteComponent extraBadNote = new MainNoteComponent(BasicNote.gNatural());
		
		TripletTimedComponentPhrase tripletPhrase = new TripletTimedComponentPhrase(standardTimedComponent(c1), 
																					standardTimedComponent(c2), 
																					standardTimedComponent(c3),
																					Duration.half);
		
		exception.expect(TripletPhraseException.class);
		
		tripletPhrase.addToPhrase(standardTimedComponent(extraBadNote));
	}
	
	@Test
	public void testThatAbcRepresentationForTripletPhraseComponentListReturnsCorrectly(){
		MainNoteComponent c1 = new MainNoteComponent(BasicNote.cSharp(), 1);
		MainNoteComponent c2 = new MainNoteComponent(BasicNote.dNatural());
		MainNoteComponent c3 = new MainNoteComponent(BasicNote.eFlat());
		
		TripletTimedComponentPhrase tripletPhrase = new TripletTimedComponentPhrase(standardTimedComponent(c1), 
																					standardTimedComponent(c2), 
																					standardTimedComponent(c3),
																					Duration.half);
		
		Assert.assertThat(tripletPhrase.getAbcRepresentation(), equalTo("(3^cD_E"));
	}
}
