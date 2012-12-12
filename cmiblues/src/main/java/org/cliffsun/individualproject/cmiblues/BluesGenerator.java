package org.cliffsun.individualproject.cmiblues;

import org.cliffsun.individualproject.exception.BarLengthException;
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
	
	public String generateFullScore() throws BarLengthException{
		return getHeaders() + generateTrebleLine() + getTwelveBarBluesBassClefScoreLine();
	}
	
	public String generateTrebleLine() throws BarLengthException{
		TrebleScoreGenerator generator = new TrebleScoreGenerator();
		return generator.generateScore();
	}
	
	public String getTwelveBarBluesBassClefScoreLine(){
		return "[V:2]" +
			   "[C,G,](3z/2z/2[C,G,]/2 zz | " +
			   "[C,G,](3z/2z/2[C,G,]/2 zz | " +
			   "[C,G,](3z/2z/2[C,G,]/2 zz | " +
			   "[C,G,](3z/2z/2[C,G,]/2 zz | " +
			   "[F,C](3z/2z/2[F,C]/2 zz | " +
			   "[F,C](3z/2z/2[F,C]/2 zz | " +
			   "[C,G,](3z/2z/2[C,G,]/2 zz | " +
			   "[C,G,](3z/2z/2[C,G,]/2 zz | " +
			   "[G,,D,](3z/2z/2[G,,D,]/2 zz | " +
			   "[F,,C,](3z/2z/2[F,,C,]/2 zz | " +
			   "[C,G,](3z/2z/2[C,G,]/2 zz | " +
			   "[C,G,](3z/2z/2[C,G,]/2 zz ||";
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
	
	public static void main(String[] args) throws BarLengthException {
		// TODO Auto-generated method stub
		// Will generate 4 bars of blues initially
		// Will use a grammar and a parse tree 
		// Forming a line of music like forming a sentence of words
		BluesGenerator blues = new BluesGenerator();
		System.out.println(blues.generateFullScore());
	}

}
