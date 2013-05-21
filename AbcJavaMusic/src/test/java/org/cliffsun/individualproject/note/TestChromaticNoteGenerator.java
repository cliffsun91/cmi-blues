package org.cliffsun.individualproject.note;

import org.junit.Test;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;


public class TestChromaticNoteGenerator {

	@Test
	public void testThatGetOneChromaticNoteUpForCNaturalReturnsANoteWithIncrementedValue(){
		MainNoteComponent note = new MainNoteComponent(BasicNote.cNatural());
		
		ChromaticNoteGenerator generator = new ChromaticNoteGenerator();
		MainNoteComponent oneUp = generator.getOneChromaticNoteUp(note);
		
		assertThat(oneUp.getIntegerValueForNote(), equalTo(note.getIntegerValueForNote()+1));
	}
	
	@Test
	public void testThatGetOneChromaticNoteUpForDNaturalReturnsDSharpInsteadOfEFlat(){
		MainNoteComponent note = new MainNoteComponent(BasicNote.dNatural());
		
		ChromaticNoteGenerator generator = new ChromaticNoteGenerator();
		MainNoteComponent oneUp = generator.getOneChromaticNoteUp(note);
		
		assertThat(oneUp.getBasicNote(), equalTo(BasicNote.dSharp()));
	}
	
	@Test
	public void testThatGetOneChromaticNoteDownReturnsBNaturalWithLowerOctaveForCNatural(){
		MainNoteComponent note = new MainNoteComponent(BasicNote.cNatural());
		
		ChromaticNoteGenerator generator = new ChromaticNoteGenerator();
		MainNoteComponent oneDown = generator.getOneChromaticNoteDown(note);
		
		assertThat(oneDown.getBasicNote(), equalTo(BasicNote.bNatural()));
		assertThat(oneDown.getOctaveShift(), equalTo(note.getOctaveShift()-1));
	}
	
	@Test
	public void testThatGetOneChromaticNoteUpReturnsCNaturalWithLowerOctaveForBNatural(){
		MainNoteComponent note = new MainNoteComponent(BasicNote.bNatural());
		
		ChromaticNoteGenerator generator = new ChromaticNoteGenerator();
		MainNoteComponent oneUp = generator.getOneChromaticNoteUp(note);
		
		assertThat(oneUp.getBasicNote(), equalTo(BasicNote.cNatural()));
		assertThat(oneUp.getOctaveShift(), equalTo(note.getOctaveShift()+1));
	}
}
