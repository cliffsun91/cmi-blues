package org.cliffsun.individualproject.accompaniment;

import java.io.IOException;
import java.util.List;

import main.java.org.cliffsun.individualproject.antlrgrammar.ProgressionInputGrammarLexer;
import main.java.org.cliffsun.individualproject.antlrgrammar.ProgressionInputGrammarParser;

import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.cliffsun.individualproject.keys.CMajorSeventhScale;
import org.cliffsun.individualproject.keys.DMinorSeventhScale;
import org.cliffsun.individualproject.keys.GMajorSeventhScale;


public class BassProgressionParser {

	public BassProgressionParser() {
		// TODO Auto-generated constructor stub
	}
	
	public BassAccompaniment parseBassProgressionFile(String filePath) throws IOException{
		ANTLRFileStream inputStream = new ANTLRFileStream(filePath);
		ProgressionInputGrammarLexer lexer = new ProgressionInputGrammarLexer(inputStream);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		ProgressionInputGrammarParser parser = new ProgressionInputGrammarParser(tokens);
		List<String> line = parser.prog().progression;
		
		System.out.println("line = " + line.toString());
		
		InputtedBassAccompaniment bassAccomp = new InputtedBassAccompaniment(line);
		
		return bassAccomp;
	}
	
	public BassAccompaniment getNormalAccomp(){
		//BassAccompaniment accomp = new SimpleTwelveBarBluesAccompaniment(CMajorSeventhScale.cMaj7(), 
        //																 FMajorSeventhScale.fMaj7(), 
        //																 GMajorSeventhScale.gMaj7());
        BassAccompaniment accomp = new TwoFiveOneAccompaniment(DMinorSeventhScale.dMin7(), 
				 															GMajorSeventhScale.gMaj7(), 
				 															CMajorSeventhScale.cMaj7());
        return accomp;
	}
}
