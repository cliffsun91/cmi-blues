package org.cliffsun.individualproject.note;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

public class TestBasicNote {

	@Test
	public void testThatIntervalsAreCalculatedCorrectly(){
		assertThat(BasicNote.cNatural().getAbsInterval(BasicNote.fSharp()) , equalTo(6)); 
	}
	
	@Test
	public void testTwoObjectEqualityTrue(){
		BasicNote c1 = BasicNote.cNatural();
		BasicNote c2 = new BasicNote(SimpleNoteEnum.C);
		assertThat(c1, equalTo(c2)); 
	}
	
	@Test
	public void testTwoObjectEqualityFalse(){
		BasicNote c1 = BasicNote.cNatural();
		BasicNote cSharp = new BasicNote(SimpleNoteEnum.C, AccidentalShift.Sharp);
		assertFalse(c1 == cSharp); 
	}
	
	@Test
	public void testTwoBasicNotesMusicallyEquivalentButNotObjectEquivalent(){
		BasicNote cSharp = BasicNote.cSharp();
		BasicNote dFlat = BasicNote.dFlat();
		assertFalse(cSharp == dFlat); 
	}
}
