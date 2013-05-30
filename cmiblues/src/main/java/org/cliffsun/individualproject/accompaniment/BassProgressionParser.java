package org.cliffsun.individualproject.accompaniment;

import java.io.IOException;
import java.util.List;

import main.java.org.cliffsun.individualproject.antlrgrammar.ProgressionInputGrammarLexer;
import main.java.org.cliffsun.individualproject.antlrgrammar.ProgressionInputGrammarParser;

import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.cliffsun.individualproject.chord.CDominantSeventhChord;
import org.cliffsun.individualproject.chord.DMinorSeventhChord;
import org.cliffsun.individualproject.chord.GDominantSeventhChord;


public class BassProgressionParser {
	
	public BassAccompaniment parseBassProgressionFile(String filePath) throws IOException{
		ANTLRFileStream inputStream = new ANTLRFileStream(filePath);
		ProgressionInputGrammarLexer lexer = new ProgressionInputGrammarLexer(inputStream);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		ProgressionInputGrammarParser parser = new ProgressionInputGrammarParser(tokens);
		List<List<String>> progressionLine = parser.prog().progression;
		
		System.out.println("progressionLine = " + progressionLine.toString());
		
		InputtedBassAccompaniment bassAccomp = new InputtedBassAccompaniment(progressionLine);
		
		return bassAccomp;
	}
	
	public BassAccompaniment getNormalAccomp(){
		//BassAccompaniment accomp = new SimpleTwelveBarBluesAccompaniment(CMajorSeventhScale.cMaj7(), 
        //																 FMajorSeventhScale.fMaj7(), 
        //																 GMajorSeventhScale.gMaj7());
        BassAccompaniment accomp = new TwoFiveOneAccompaniment(new DMinorSeventhChord(), 
				 															new GDominantSeventhChord(), 
				 															new CDominantSeventhChord());
        return accomp;
	}
}
