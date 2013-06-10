package org.cliffsun.individualproject.cmiblues;


import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.cliffsun.individualproject.exception.BarLengthException;
import org.cliffsun.individualproject.grammar.AbstractTonesGrammar;
import org.cliffsun.individualproject.grammar.AbstractTonesGrammarUsedRules;
import org.cliffsun.individualproject.grammar.AntlrGrammarSentenceGenerator;
import org.cliffsun.individualproject.nongrammar.ProbablisiticGenerationWithDifferentDurationsScoreGenerator;
import org.cliffsun.individualproject.nongrammar.TrebleScoreGenerator;

public class CMIBlues {

	public String generateFullScore() throws BarLengthException{
		ABCFullScoreRepresentation abcRepr = new ABCFullScoreRepresentation(null);
		return abcRepr.getHeaders() + generateTrebleLine() + getTwelveBarBluesBassClefScoreLine();
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
	
	
	public static void main(String[] args) throws Exception {
		//BluesGenerator blues = new BluesGenerator();
		//System.out.println(blues.generateFullScore());
		
		String userDir = System.getProperty("user.dir");
        String grammarFilePath = userDir + "/bluesGrammar.txt";
        String progressionFilePath = userDir + "/progression2.txt";
        
		if(args.length == 0){
			try {
				generateMusicSentenceFromGrammar(1, grammarFilePath, progressionFilePath);
			} catch (Exception e) {
				System.out.println("exception caught: " + e.toString());
				e.printStackTrace();
			}
		}
		else if (args.length == 2){
			String option = args[0];
			String numberToSample = args[1];
			int noToSample = Integer.parseInt(numberToSample);
			if(option.equals("generate")){
				generateMusicSentenceFromGrammar(noToSample, grammarFilePath, progressionFilePath);
			}
			else if(option.equals("update")){
				generateSamplesToUpdateGrammar(noToSample, grammarFilePath, progressionFilePath);
			}
			else{
				throw new Exception("argument '" + option + "' is not a valid argument, it must be 'update'");
			}
		}
		else{
			throw new Exception("Program either expects 0 or 2 arguments, 2 arguments should be " +
								"\"update\" and the number to sample");
		}
		
	}
	
	public static void generateMusicSentenceFromGrammar(int noToGenerate, String grammarFilePath, String progressionFilePath) throws Exception{
        //System.out.println("classpath: " + System.getProperty("java.class.path"));
        System.out.println("Sys path: " + System.getProperty("user.dir"));
        //System.out.println(CMIBlues.class.getProtectionDomain().getCodeSource().getLocation());
        AntlrGrammarParser abstractToneParser = new AntlrGrammarParser();
        AbstractTonesGrammar grammar = abstractToneParser.parseAbstractToneGrammar(grammarFilePath);
        AntlrGrammarSentenceGenerator sentenceGenerator = new AntlrGrammarSentenceGenerator(grammar);
        MusicGenerator generator = new MusicGenerator(sentenceGenerator, progressionFilePath);
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(Calendar.getInstance().getTime());
        String outputDirectory = "/Users/cliffsun91/Desktop/IndividualProject/output/" + timeStamp +"/";
        for(int i = 0; i < noToGenerate; i++){
        	FullMusicScore fullMusicScore = generator.generateFullMusicScore();
        	fullMusicScore.writeToFile(outputDirectory, "output"+(i+1)+".abc");
        	System.out.println("Final score is: \n\n" + fullMusicScore.getABCFullScoreRepr().getFullScoreAsString());
        }
        //below is for the jython stuff
        //SentenceGeneratorFactory generatorFactory = new SentenceGeneratorFactory(jythonPath);
        //SentenceGenerator sentenceGenerator = generatorFactory.create(grammarFilePath);
	}
	
	public static void generateSamplesToUpdateGrammar(int noToSample, String grammarFilePath, String progressionFilePath) throws Exception{
        GrammarUpdate grammarUpdate = new GrammarUpdate(grammarFilePath, progressionFilePath);
        grammarUpdate.runUpdate(noToSample);
	}
	
	

}
