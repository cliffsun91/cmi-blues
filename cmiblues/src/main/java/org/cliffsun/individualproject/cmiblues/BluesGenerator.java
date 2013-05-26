package org.cliffsun.individualproject.cmiblues;


import org.cliffsun.individualproject.accompaniment.BassAccompaniment;
import org.cliffsun.individualproject.accompaniment.BassProgressionParser;
import org.cliffsun.individualproject.exception.BarLengthException;
import org.cliffsun.individualproject.grammar.SentenceGenerator;
import org.cliffsun.individualproject.grammar.SentenceGeneratorFactory;
import org.cliffsun.individualproject.melody.TwelveBarBluesMelody;
import org.cliffsun.individualproject.score.BassClefScoreLine;
import org.cliffsun.individualproject.score.CombinedScoreLine;
import org.cliffsun.individualproject.score.TrebleClefScoreLine;

public class BluesGenerator {

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
				"Q: 120\n" +
				"M: C\n" + 
				"K: C\n" +
				"V: 1\n" +
				"V: 2 bass\n";
	}
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		// Will generate 4 bars of blues initially
		// Will use a grammar and a parse tree 
		// Forming a line of music like forming a sentence of words
		
		//BluesGenerator blues = new BluesGenerator();
		//System.out.println(blues.generateFullScore());
		
		//get jython path from command line args
		if(args.length < 1){
			throw new Exception("No arguments currently, program expects 1 argument: path to jython!");
		}
		
		String jythonPath = args[0];
		
		try {
			generateMusicSentenceFromGrammar(jythonPath);
		} catch (Exception e) {
			System.out.println("exception caught: " + e.toString());
			e.printStackTrace();
		}
	}
	
	public static void generateMusicSentenceFromGrammar(String jythonPath) throws Exception{
        SentenceGeneratorFactory generatorFactory = new SentenceGeneratorFactory(jythonPath);
        System.out.println("classpath: " + System.getProperty("java.class.path"));
        System.out.println("Sys path: " + System.getProperty("user.dir"));
        System.out.println(BluesGenerator.class.getProtectionDomain().getCodeSource().getLocation());
        String userDir = System.getProperty("user.dir");
        String grammarFilePath = userDir + "/bluesGrammar.txt";
        String progressionFilePath = userDir + "/progression1.txt";
        SentenceGenerator sentenceGenerator = generatorFactory.create(grammarFilePath);
        
        TwelveBarBluesMelody melody = new TwelveBarBluesMelody(sentenceGenerator);
        BassProgressionParser bassProgParser = new BassProgressionParser();
        BassAccompaniment accomp = bassProgParser.parseBassProgressionFile(progressionFilePath);
        
        TrebleClefScoreLine trebleScore = melody.getScoreLine(accomp.getForm());
        BassClefScoreLine bassScore = accomp.getScoreLine();
        
        CombinedScoreLine fullScore = new CombinedScoreLine(trebleScore, bassScore);
        
        System.out.println("Final score is: \n" + getHeaders() + fullScore.getAbcRepresentation());
	}

}
