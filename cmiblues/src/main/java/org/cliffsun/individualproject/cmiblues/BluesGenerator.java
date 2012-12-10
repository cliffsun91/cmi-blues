package org.cliffsun.individualproject.cmiblues;

import org.cliffsun.individualproject.note.AccidentalShift;
import org.cliffsun.individualproject.note.BasicNote;
import org.cliffsun.individualproject.note.MainNoteComponent;
import org.cliffsun.individualproject.score.BassClefScoreLine;
import org.cliffsun.individualproject.score.CombinedScoreLine;

public class BluesGenerator {

	/**
	 * @param args
	 */
	CombinedScoreLine scoreLine;
	
	
	public BluesGenerator(){
		
	}
	
	public void addTwelveBarBluesBassClefScoreLine(){
		//String = ""
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
		MainNoteComponent cNote = new MainNoteComponent(BasicNote.C, AccidentalShift.Natural);
		System.out.println(cNote.getRepresentation());
	}

}
