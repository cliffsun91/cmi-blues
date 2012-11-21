package org.cliffsun.individualproject.cmiblues;

import org.cliffsun.individualproject.notes.BasicNote;
import org.cliffsun.individualproject.notes.Note;
import org.cliffsun.individualproject.notes.Shift;

public class BluesGenerator {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Will generate 4 bars of blues initially
		// Will use a grammar and a parse tree 
		// Forming a line of music like forming a sentence of words
		System.out.println("helloworld");
		Note cNote = new Note(BasicNote.C, Shift.Natural);
		System.out.println(cNote.getRepresentation());
	}

}
