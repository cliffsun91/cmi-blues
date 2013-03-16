package org.cliffsun.individualproject.melody;

import java.util.Arrays;

import org.cliffsun.individualproject.bar.Bar;
import org.cliffsun.individualproject.grammar.SentenceGenerator;
import org.cliffsun.individualproject.grammar.terminal.TerminalParser;
import org.cliffsun.individualproject.phrase.Phrase;
import org.cliffsun.individualproject.score.TrebleClefScoreLine;

public class TwelveBarBluesMelody{

	SentenceGenerator sentenceGenerator;
	final int bars = 12;
	final int beatsPerBar = 4;
	
	public TwelveBarBluesMelody(SentenceGenerator sentenceGenerator) {
		this.sentenceGenerator = sentenceGenerator;
	}
	
	public TrebleClefScoreLine getScoreLine() throws Exception{
		//String classPath = System.getProperty("java.class.path");
		//System.out.println("Class Path is: " + classPath);
		TrebleClefScoreLine melody = new TrebleClefScoreLine();
		for (int i = 0 ; i < bars ; i++) {
			Bar bar = new Bar();
			String [] terminalSentence = sentenceGenerator.generate("Q4");
			System.out.println("terminal sequence is: " + Arrays.asList(terminalSentence).toString());
			TerminalParser parser = new TerminalParser();
			Phrase phrase = parser.convertSentenceToPhrase(Arrays.asList(terminalSentence));
			bar.addToBar(phrase);
			melody.addBarToScoreLine(bar);
		}
		return melody;
	}
}
