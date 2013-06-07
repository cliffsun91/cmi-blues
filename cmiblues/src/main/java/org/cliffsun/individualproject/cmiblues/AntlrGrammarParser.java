package org.cliffsun.individualproject.cmiblues;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import main.java.org.cliffsun.individualproject.antlrgrammar.tonegrammar.AbstractToneGrammarLexer;
import main.java.org.cliffsun.individualproject.antlrgrammar.tonegrammar.AbstractToneGrammarParser;

import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.cliffsun.individualproject.grammar.AbstractTonesGrammar;
import org.cliffsun.individualproject.grammar.AntlrGrammarSentenceGenerator;
import org.cliffsun.individualproject.grammar.ProductionRule;

public class AntlrGrammarParser{
	
	public AntlrGrammarSentenceGenerator parseAbstractToneGrammar(String filePath) throws IOException{
		ANTLRFileStream inputStream = new ANTLRFileStream(filePath);
		AbstractToneGrammarLexer lexer = new AbstractToneGrammarLexer(inputStream);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		AbstractToneGrammarParser parser = new AbstractToneGrammarParser(tokens);
		Map<String, List<ProductionRule>> abstractToneGrammar = parser.grmmr().grammarList;
		AbstractTonesGrammar grammar = new AbstractTonesGrammar(abstractToneGrammar);
		AntlrGrammarSentenceGenerator sentenceGenerator = 
									new AntlrGrammarSentenceGenerator(grammar);
		return sentenceGenerator;
	}
	
	public static void main(String[] args) throws IOException{
		String userDir = System.getProperty("user.dir");
        String grammarFilePath = userDir + "/bluesGrammar.txt";
        AntlrGrammarParser antlrGrammarParser = new AntlrGrammarParser();
        antlrGrammarParser.parseAbstractToneGrammar(grammarFilePath);
	}
}
