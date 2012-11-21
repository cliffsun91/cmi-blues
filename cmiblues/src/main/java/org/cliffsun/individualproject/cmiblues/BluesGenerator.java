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
	
	public String getHeaders(){
		return  "X: 1\n" +
			    "T: Blues Improv with 12 Bar Accompaniment\n" + 
				"C: Composer\n" +
				"L: 1/4\n" + 
				"Q:110\n" +
				"M: C\n" + 
				"K: C\n" +
				"V: 1\n" +
				"V: 2 bass\n";
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
