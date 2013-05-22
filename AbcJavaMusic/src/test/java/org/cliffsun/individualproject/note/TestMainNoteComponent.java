package org.cliffsun.individualproject.note;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.Arrays;

import org.cliffsun.individualproject.note.MainNoteComponent;
import org.junit.Test;

public class TestMainNoteComponent {

	@Test
	public void testGetAbcRepresentationForMainNoteCSharpWithOctaveShiftPlus1ReturnsCorrectly(){
		MainNoteComponent note = new MainNoteComponent(BasicNote.cSharp(), 1);
		assertThat(note.getAbcRepresentation(null), equalTo("^c"));
	}
	
	@Test
	public void testGetAbcRepresentationForMainNoteBFlatWithOctaveShiftMinus1ReturnsCorrectly(){
		MainNoteComponent note = new MainNoteComponent(BasicNote.bFlat(), -1);
		assertThat(note.getAbcRepresentation(null), equalTo("_B,"));
	}
	
	@Test
	public void testGetAbcRepresentationForMainNoteCSharpWithOctaveShiftMinus3ReturnsCorrectly(){
		MainNoteComponent note = new MainNoteComponent(BasicNote.cSharp(), -3);
		assertThat(note.getAbcRepresentation(null), equalTo("^C,,,"));
	}
	
	@Test
	public void testThatAbcRepresentationForMainNoteCNaturalAfterACSharpReturnsWithNaturalSign(){
		MainNoteComponent cNatural = new MainNoteComponent(BasicNote.cNatural());
		MainNoteComponent cSharp = new MainNoteComponent(BasicNote.cSharp());
		assertThat(cNatural.getAbcRepresentation(Arrays.asList(cSharp)), equalTo("=C"));
	}
	
	@Test
	public void testThatAbcRepresentationForMainNoteCSharpAfterACSharpReturnsCorrectly(){
		MainNoteComponent cSharp1 = new MainNoteComponent(BasicNote.cSharp());
		MainNoteComponent cSharp2 = new MainNoteComponent(BasicNote.cSharp());
		assertThat(cSharp1.getAbcRepresentation(Arrays.asList(cSharp2)), equalTo("^C"));
	}
	
	@Test
	public void testThatAbcRepresentationForMainNoteCNaturalOnDifferentOctaveAfterACSharpReturnsWithoutNaturalSign(){
		MainNoteComponent cNatural = new MainNoteComponent(BasicNote.cNatural(), 1);
		MainNoteComponent cSharp = new MainNoteComponent(BasicNote.cSharp());
		assertThat(cNatural.getAbcRepresentation(Arrays.asList(cSharp)), equalTo("c"));
	}
	
	@Test
	public void testGetIntervalsReturnsCorrectlyForTwoNotesOnSameOctave(){
		MainNoteComponent dNatural = new MainNoteComponent(BasicNote.dNatural(), 0);
		MainNoteComponent aSharp = new MainNoteComponent(BasicNote.aSharp(), 0);
		assertThat(dNatural.getAbsInterval(aSharp), equalTo(8));
		assertThat(aSharp.getAbsInterval(dNatural), equalTo(8));
	}
	
	@Test
	public void testGetIntervalsReturnsAnIntervalOfZeroForTheSameNote(){
		MainNoteComponent dNatural = new MainNoteComponent(BasicNote.dNatural(), 0);
		assertThat(dNatural.getAbsInterval(dNatural), equalTo(0));
	}
	
	@Test
	public void testGetIntervalsReturnsCorrectlyForTwoNotesMoreThanOneOctaveApart(){
		MainNoteComponent dNatural = new MainNoteComponent(BasicNote.dNatural(), 0);
		MainNoteComponent aNatural = new MainNoteComponent(BasicNote.aNatural(), 1);
		assertThat(dNatural.getAbsInterval(aNatural), equalTo(19));
		assertThat(aNatural.getAbsInterval(dNatural), equalTo(19));
	}
	
	@Test
	public void testGetIntervalsReturnsCorrectlyForSameNotesOnDifferentOctaves(){
		MainNoteComponent aNatural = new MainNoteComponent(BasicNote.aNatural(), 0);
		MainNoteComponent aNatural1 = new MainNoteComponent(BasicNote.aNatural(), 1);
		assertThat(aNatural.getAbsInterval(aNatural1), equalTo(12));
		assertThat(aNatural1.getAbsInterval(aNatural), equalTo(12));
	}
	
	
	@Test
	public void testGetIntervalsReturnsCorrectlyForTwoNotesOneHigherThanTheOtherAndOnDifferentOctaves(){
		MainNoteComponent aNatural = new MainNoteComponent(BasicNote.aNatural(), 0);
		MainNoteComponent dNatural = new MainNoteComponent(BasicNote.dNatural(), 1);
		assertThat(aNatural.getAbsInterval(dNatural), equalTo(5));
		assertThat(dNatural.getAbsInterval(aNatural), equalTo(5));
	}
	
	@Test
	public void testGetIntervalsReturnsCorrectlyForTwoNotesOneHigherThanTheOtherAndMoreThanOneOctaveApart(){
		MainNoteComponent aNatural = new MainNoteComponent(BasicNote.aNatural(), -1);
		MainNoteComponent dNatural = new MainNoteComponent(BasicNote.dNatural(), 1);
		assertThat(aNatural.getAbsInterval(dNatural), equalTo(17));
		assertThat(dNatural.getAbsInterval(aNatural), equalTo(17));
	}
	
}
