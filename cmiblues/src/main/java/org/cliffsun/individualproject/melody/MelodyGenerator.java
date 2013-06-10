package org.cliffsun.individualproject.melody;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.cliffsun.individualproject.accompaniment.BassAccompaniment;
import org.cliffsun.individualproject.bar.Bar;
import org.cliffsun.individualproject.chord.Chord;
import org.cliffsun.individualproject.duration.Duration;
import org.cliffsun.individualproject.grammar.AbstractTonesGrammarUsedRules;
import org.cliffsun.individualproject.grammar.AntlrGrammarSentenceGenerator;
import org.cliffsun.individualproject.grammar.SentenceGenerator;
import org.cliffsun.individualproject.grammar.terminal.TerminalParser;
import org.cliffsun.individualproject.keys.Scale;
import org.cliffsun.individualproject.note.TimedComponent;
import org.cliffsun.individualproject.phrase.Phrase;
import org.cliffsun.individualproject.score.TrebleClefScoreLine;
import org.cliffsun.individualproject.utils.Pair;

public class MelodyGenerator{

	private AntlrGrammarSentenceGenerator sentenceGenerator;
	private int carriedOctaveShift = 0;
	private BassAccompaniment bassAccomp;
	private List<List<List<String>>> fullScoreToneList;
	private AbstractTonesGrammarUsedRules grammarUsedRules;
	
	public MelodyGenerator(BassAccompaniment bassAccomp, AntlrGrammarSentenceGenerator sentenceGenerator, AbstractTonesGrammarUsedRules grammarUsedRules) {
		this.bassAccomp = bassAccomp;
		this.sentenceGenerator = sentenceGenerator;
		this.grammarUsedRules = grammarUsedRules;
		fullScoreToneList = new ArrayList<List<List<String>>>();
	}
	
	public TrebleClefScoreLine getScoreLine() throws Exception{
		//String classPath = System.getProperty("java.class.path");
		//System.out.println("Class Path is: " + classPath);
		fullScoreToneList.clear();
		TrebleClefScoreLine melody = new TrebleClefScoreLine();
		TerminalParser parser = new TerminalParser();
		List<List<Pair<Chord,Duration>>> accompList = bassAccomp.getForm();
		for (int i = 0 ; i < bassAccomp.getNumberOfBars() ; i++) {
			Bar bar = new Bar();
			List<Pair<Chord, Duration>> barChords = accompList.get(i);
			List<List<String>> barToneList = new ArrayList<List<String>>();
			for(Pair<Chord,Duration> pair : barChords){
				Chord chord = pair.getLeft();
				Duration duration = pair.getRight();
				Scale accompScale = chord.getAccompanyingScale();
				
				List<String> terminalSentence;
				if (duration.getDurationAsDouble() == 4.0){
					terminalSentence = sentenceGenerator.generate("Q4", grammarUsedRules);
				}
				else if (duration.getDurationAsDouble() == 2.0){
					terminalSentence = sentenceGenerator.generate("Q2", grammarUsedRules);
				}
				else{
					throw new IllegalArgumentException("duration not valid for accompanying chords: " + duration.getAbcRepresentation());
				}
				barToneList.add(terminalSentence);
				System.out.println("terminal sequence is: " + terminalSentence.toString());
				Phrase phrase = parser.convertSentenceToPhrase(terminalSentence, accompScale, carriedOctaveShift);
				carriedOctaveShift = extractLastOctaveShiftInPhrase(phrase);
				bar.addToBar(phrase);
			}
			melody.addBarToScoreLine(bar);
			fullScoreToneList.add(barToneList);
		}
		return melody;
	}
	
	public List<List<List<String>>> getFullScoreToneList(){
		return fullScoreToneList;
	}
	
	public int extractLastOctaveShiftInPhrase(Phrase phrase){
		List<TimedComponent> components = phrase.getComponentList();
		TimedComponent lastTimedComponent = components.get(components.size()-1);
		return lastTimedComponent.getComponent().getOctaveShift();
	}
}
