package org.cliffsun.individualproject.cmiblues;

import java.util.Arrays;
import java.util.List;

import org.cliffsun.individualproject.accompaniment.BassAccompaniment;
import org.cliffsun.individualproject.accompaniment.SimpleTwelveBarBluesAccompaniment;
import org.cliffsun.individualproject.exception.BarLengthException;
import org.cliffsun.individualproject.grammar.SentenceGenerator;
import org.cliffsun.individualproject.grammar.SentenceGeneratorFactory;
import org.cliffsun.individualproject.grammar.terminal.TerminalParser;
import org.cliffsun.individualproject.keys.CMajorSeventhScale;
import org.cliffsun.individualproject.keys.FMajorSeventhScale;
import org.cliffsun.individualproject.keys.GMajorSeventhScale;
import org.cliffsun.individualproject.melody.TwelveBarBluesMelody;
import org.cliffsun.individualproject.phrase.Phrase;
import org.cliffsun.individualproject.score.BassClefScoreLine;
import org.cliffsun.individualproject.score.CombinedScoreLine;
import org.cliffsun.individualproject.score.TrebleClefScoreLine;

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
		TrebleScoreGenerator generator = new ProbablisiticGenerationWithDifferentDurationsScoreGenerator();
		return generator.generateScore();
	}
	
	public String getTwelveBarBluesWithTripletedSecondChordBassClefScoreLine(){
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
	
	public String getTwelveBarBluesBassClefScoreLine(){
		return "[V:2][C,G,]z[C,G,]z | " +
			   "[C,G,]z[C,G,]z | " +
			   "[C,G,]z[C,G,]z | " +
			   "[C,G,]z[C,G,]z | " +
			   "[F,C]z[F,C]z | " +
			   "[F,C]z[F,C]z | " +
			   "[C,G,]z[C,G,]z | " +
			   "[C,G,]z[C,G,]z | " +
			   "[G,,D,]z[G,,D,]z | " +
			   "[F,,C,]z[F,,C,]z | " +
			   "[C,G,]z[C,G,]z | " +
			   "[C,G,]z[C,G,]z ||";
	}
	
	
	
	public static String getHeaders(){
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
		
		//BluesGenerator blues = new BluesGenerator();
		//System.out.println(blues.generateFullScore());
		
		try {
			generateMusicSentenceFromGrammar();
		} catch (Exception e) {
			System.out.println("exception caught: " + e.toString());
			e.printStackTrace();
		}
	}
	
	public static void generateMusicSentenceFromGrammar() throws Exception{
        SentenceGeneratorFactory generatorFactory = new SentenceGeneratorFactory();
        System.out.println(BluesGenerator.class.getProtectionDomain().getCodeSource().getLocation());
        String grammarFilePath = "/Users/cliffsun91/Desktop/IndividualProject/cmi-blues/cmiblues/bluesGrammar.txt";
        SentenceGenerator sentenceGenerator = generatorFactory.create(grammarFilePath);
        
        TwelveBarBluesMelody melody = new TwelveBarBluesMelody(sentenceGenerator);
        BassAccompaniment accomp = new SimpleTwelveBarBluesAccompaniment(CMajorSeventhScale.cMaj7(), 
        																 FMajorSeventhScale.fMaj7(), 
        																 GMajorSeventhScale.gMaj7());
        TrebleClefScoreLine trebleScore = melody.getScoreLine();
        BassClefScoreLine bassScore = accomp.getScoreLine();
        
        CombinedScoreLine fullScore = new CombinedScoreLine(trebleScore, bassScore);
        
        System.out.println("Final score is: \n" + getHeaders() + fullScore.getAbcRepresentation());
        
        //String[] sentence = sentenceGenerator.generate("Q4");
        
        //for (String s: sentence){
        //	System.out.print(s + ", ");
        //}
        
//        TerminalParser terminalParser = new TerminalParser();
//        List<String> stringList = Arrays.asList(sentence);
//        Phrase phrase = terminalParser.convertSentenceToPhrase(stringList);
//        System.out.println("phrase is:");
//        System.out.print(phrase.getAbcRepresentation() + " ");
	}

}
