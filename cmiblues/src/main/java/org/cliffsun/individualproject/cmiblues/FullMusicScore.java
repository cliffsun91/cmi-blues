package org.cliffsun.individualproject.cmiblues;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.cliffsun.individualproject.bar.Bar;
import org.cliffsun.individualproject.exception.BarLengthException;
import org.cliffsun.individualproject.grammar.ABCFullScoreRepresentation;
import org.cliffsun.individualproject.grammar.AbstractTonesGrammar;
import org.cliffsun.individualproject.grammar.AbstractTonesGrammarUsedRules;
import org.cliffsun.individualproject.score.CombinedScoreLine;

public class FullMusicScore {
	
	private AbstractTonesGrammar abstractTonesGrammar;
	private AbstractTonesGrammarUsedRules grammarUsesRules;
	private CombinedScoreLine scoreLine;
	private ABCFullScoreRepresentation abcRepr;
	
	public FullMusicScore(AbstractTonesGrammar abstractTonesGrammar, AbstractTonesGrammarUsedRules grammarUsesRules, CombinedScoreLine scoreLine, ABCFullScoreRepresentation abcRepr) {
		this.abstractTonesGrammar = abstractTonesGrammar;
		this.grammarUsesRules = grammarUsesRules;
		this.scoreLine = scoreLine;
		this.abcRepr = abcRepr;
	}
	
	public ABCFullScoreRepresentation getABCFullScoreRepr(){
		return abcRepr;
	}
	
	public AbstractTonesGrammarUsedRules getGrammarUsedRules(){
		return grammarUsesRules;
	}
	
	public List<Bar> getBarsFromTrebleClef(){
		return scoreLine.getTrebleScoreLine().getBarList();
	}
	
	public void writeToFile(String filePath, String fileName) throws BarLengthException{
		try {
			System.out.println(filePath + fileName);
			
			File dir = new File(filePath);
			// if the directory does not exist, create it
			if (!dir.exists()) {
				System.out.println("creating directory: " + filePath);
				boolean result = dir.mkdir();
				if (result) {
					System.out.println("dir created");
				}

			}
			  
			File file = new File(filePath + fileName);
			if (!file.exists()) {
				System.out.println("file does not exist so create it");
				file.createNewFile();
			}
 
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(abcRepr.getFullScoreAsString());
			bw.close();
 
		} catch (IOException e) {
			System.err.println("Could not write abc tune to file '" + filePath + fileName + "', exception:");
			e.printStackTrace();
		}
	}
}
