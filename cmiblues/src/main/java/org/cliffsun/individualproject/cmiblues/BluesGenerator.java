package org.cliffsun.individualproject.cmiblues;

import org.cliffsun.individualproject.notes.BasicNote;
import org.cliffsun.individualproject.notes.MainNote;
import org.cliffsun.individualproject.notes.AccidentalShift;

public class BluesGenerator {

	/**
	 * @param args
	 */
	
	public BluesGenerator(){
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Will generate 4 bars of blues initially
		// Will use a grammar and a parse tree 
		// Forming a line of music like forming a sentence of words
		MainNote cNote = new MainNote(BasicNote.C, AccidentalShift.Natural);
		System.out.println(cNote.getRepresentation());
	}

}
