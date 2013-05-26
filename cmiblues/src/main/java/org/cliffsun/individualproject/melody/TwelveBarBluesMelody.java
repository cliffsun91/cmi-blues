package org.cliffsun.individualproject.melody;

import java.util.Arrays;
import java.util.List;

import org.cliffsun.individualproject.bar.Bar;
import org.cliffsun.individualproject.chord.Chord;
import org.cliffsun.individualproject.duration.Duration;
import org.cliffsun.individualproject.grammar.SentenceGenerator;
import org.cliffsun.individualproject.grammar.terminal.TerminalParser;
import org.cliffsun.individualproject.keys.Scale;
import org.cliffsun.individualproject.note.TimedComponent;
import org.cliffsun.individualproject.phrase.Phrase;
import org.cliffsun.individualproject.score.TrebleClefScoreLine;
import org.cliffsun.individualproject.utils.Pair;

public class TwelveBarBluesMelody{

	SentenceGenerator sentenceGenerator;
	final int bars = 12;
	final int beatsPerBar = 4;
	int carriedOctaveShift = 0;
	
	public TwelveBarBluesMelody(SentenceGenerator sentenceGenerator) {
		this.sentenceGenerator = sentenceGenerator;
	}
	
	public TrebleClefScoreLine getScoreLine(List<Pair<Chord,Duration>> accompChords) throws Exception{
		//String classPath = System.getProperty("java.class.path");
		//System.out.println("Class Path is: " + classPath);
		TrebleClefScoreLine melody = new TrebleClefScoreLine();
		TerminalParser parser = new TerminalParser();
		for (int i = 0 ; i < bars ; i++) {
			Bar bar = new Bar();
			String [] terminalSentence = sentenceGenerator.generate("Q4");
			System.out.println("terminal sequence is: " + Arrays.asList(terminalSentence).toString());
			Pair<Chord, Duration> pair = accompChords.get(i);
			Chord chord = pair.getLeft();
			Scale accompScale = chord.getAccompanyingScale();
			Phrase phrase = parser.convertSentenceToPhrase(Arrays.asList(terminalSentence), accompScale, carriedOctaveShift);
			carriedOctaveShift = extractLastOctaveShiftInPhrase(phrase);
			bar.addToBar(phrase);
			melody.addBarToScoreLine(bar);
		}
		return melody;
	}
	
	public int extractLastOctaveShiftInPhrase(Phrase phrase){
		List<TimedComponent> components = phrase.getComponentList();
		TimedComponent lastTimedComponent = components.get(components.size()-1);
		return lastTimedComponent.getComponent().getOctaveShift();
	}
}
