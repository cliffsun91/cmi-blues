package org.cliffsun.individualproject.cmiblues;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.cliffsun.individualproject.grammar.AbstractTonesGrammarUsedRules;
import org.cliffsun.individualproject.grammar.AntlrGrammarSentenceGenerator;

import abc.parser.TuneBook;

public class GrammarUpdate {

	private String progressionFilePath;
	private String grammarFilePath;

	public GrammarUpdate(String grammarFilePath, String progressionFilePath) {
		this.grammarFilePath = grammarFilePath;
		this.progressionFilePath = progressionFilePath;
	}
	
	public void runUpdate(int noToSample) throws Exception{
        AntlrGrammarParser abstractToneParser = new AntlrGrammarParser();
        AntlrGrammarSentenceGenerator sentenceGenerator = abstractToneParser.parseAbstractToneGrammar(grammarFilePath);

        GrammarRuleUsage grammarRuleUsage = new GrammarRuleUsage(sentenceGenerator.getAbstractToneGrammar());
        
        MusicGenerator generator = new MusicGenerator(sentenceGenerator, progressionFilePath);
		for (int i = 0; i < noToSample; i++){
			FullMusicScore fullMusicScore = generator.generateFullMusicScore();
			ABCFullScoreRepresentation abcRepr = fullMusicScore.getABCFullScoreRepr();
			 
			AbstractTonesGrammarUsedRules grammarUsedRules = fullMusicScore.getGrammarUsedRules();
			System.out.println("Used production rules: " + grammarUsedRules.getListOfRulesUsedForScore());
			//InputStream is = new ByteArrayInputStream(abcRepr.getFullScoreAsString().getBytes());
			//BufferedReader br = new BufferedReader(new InputStreamReader(is));
		 
			TuneBook tune = new TuneBook();
			tune.putTune(abcRepr.getFullScoreAsString());
			//play and display the music
			//ask user which bars sound pleasant
			
			System.out.println("Final score is: \n\n" + fullMusicScore.getABCFullScoreRepr().getFullScoreAsString());
		}
	}
	
}
