package org.cliffsun.individualproject.phrase;

import static org.cliffsun.individualproject.note.TimedComponent.standardTimedComponent;
import static org.hamcrest.CoreMatchers.equalTo;

import org.cliffsun.individualproject.exception.BarLengthException;
import org.cliffsun.individualproject.exception.TripletPhraseException;
import org.cliffsun.individualproject.note.AccidentalShift;
import org.cliffsun.individualproject.note.BasicNote;
import org.cliffsun.individualproject.note.MainNoteComponent;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class TestTripletTimedComponentPhrase {

	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	@Test
	public void testThatTripletPhraseComponentListWithFourComponentsReturnsException() throws TripletPhraseException{
		MainNoteComponent c1 = new MainNoteComponent(BasicNote.C);
		MainNoteComponent c2 = new MainNoteComponent(BasicNote.D);
		MainNoteComponent c3 = new MainNoteComponent(BasicNote.E);
		
		MainNoteComponent extraBadNote = new MainNoteComponent(BasicNote.G);
		
		TripletTimedComponentPhrase tripletPhrase = new TripletTimedComponentPhrase(standardTimedComponent(c1), 
																					standardTimedComponent(c2), 
																					standardTimedComponent(c3));
		
		exception.expect(TripletPhraseException.class);
		
		tripletPhrase.addToTripletComponentList(standardTimedComponent(extraBadNote));
	}
	
	@Test
	public void testThatAbcRepresentationForTripletPhraseComponentListReturnsCorrectly(){
		MainNoteComponent c1 = new MainNoteComponent(BasicNote.C, AccidentalShift.Sharp, 1);
		MainNoteComponent c2 = new MainNoteComponent(BasicNote.D);
		MainNoteComponent c3 = new MainNoteComponent(BasicNote.E, AccidentalShift.Flat);
		
		TripletTimedComponentPhrase tripletPhrase = new TripletTimedComponentPhrase(standardTimedComponent(c1), 
																					standardTimedComponent(c2), 
																					standardTimedComponent(c3));
		
		Assert.assertThat(tripletPhrase.getAbcRepresentation(), equalTo("(3^cD_E"));
	}
}
