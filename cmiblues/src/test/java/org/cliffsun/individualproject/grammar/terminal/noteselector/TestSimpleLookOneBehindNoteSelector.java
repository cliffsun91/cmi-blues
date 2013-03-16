package org.cliffsun.individualproject.grammar.terminal.noteselector;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.cliffsun.individualproject.grammar.toneclass.ChordTone;
import org.cliffsun.individualproject.grammar.toneclass.Tone;
import org.cliffsun.individualproject.keys.CMajorSeventhScale;
import org.cliffsun.individualproject.note.BasicNote;
import org.cliffsun.individualproject.note.MainNoteComponent;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class TestSimpleLookOneBehindNoteSelector {
	
	@Rule
	public ExpectedException exception = ExpectedException.none();
	SimpleLookOneBehindNoteSelector selector;
	
	@Before
	public void setUp() throws FileNotFoundException, IOException{
		selector = new SimpleLookOneBehindNoteSelector();
	}
	
	@Test
	public void testThatNoteSelectorForValidToneInstanceReturnsAMainNoteComponent(){
		MainNoteComponent previous = new MainNoteComponent(BasicNote.cNatural());
		ChordTone tone = new ChordTone(new CMajorSeventhScale());
		assertThat(selector.getSuitableNoteForTone(tone, previous), instanceOf(MainNoteComponent.class));
	}
	
	@Test
	public void testThatNoteSelectorForInvalidToneInstanceThrowsException(){
		MainNoteComponent previous = new MainNoteComponent(BasicNote.cNatural());
		TestTone tone = new TestTone();
		exception.expect(IllegalArgumentException.class);
		
		selector.getSuitableNoteForTone(tone, previous);
	}
	
	private class TestTone implements Tone {}
	
}
