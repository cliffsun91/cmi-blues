package org.cliffsun.individualproject.melody;

import java.util.Arrays;
import java.util.List;

import org.cliffsun.individualproject.accompaniment.BassAccompaniment;
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

public class MelodyGenerator{

	private SentenceGenerator sentenceGenerator;
	private int carriedOctaveShift = 0;
	private BassAccompaniment bassAccomp;
	
	public MelodyGenerator(BassAccompaniment bassAccomp, SentenceGenerator sentenceGenerator) {
		this.bassAccomp = bassAccomp;
		this.sentenceGenerator = sentenceGenerator;
	}
	
	public TrebleClefScoreLine getScoreLine() throws Exception{
		//String classPath = System.getProperty("java.class.path");
		//System.out.println("Class Path is: " + classPath);
		TrebleClefScoreLine melody = new TrebleClefScoreLine();
		TerminalParser parser = new TerminalParser();
		List<List<Pair<Chord,Duration>>> accompList = bassAccomp.getForm();
		for (int i = 0 ; i < bassAccomp.getNumberOfBars() ; i++) {
			Bar bar = new Bar();
			List<Pair<Chord, Duration>> barChords = accompList.get(i);
			for(Pair<Chord,Duration> pair : barChords){
				Chord chord = pair.getLeft();
				Duration duration = pair.getRight();
				Scale accompScale = chord.getAccompanyingScale();
				
				String [] terminalSentence;
				if (duration.getDurationAsDouble() == 4.0){
					terminalSentence = sentenceGenerator.generate("Q4");
				}
				else if (duration.getDurationAsDouble() == 2.0){
					terminalSentence = sentenceGenerator.generate("Q2");
				}
				else{
					throw new IllegalArgumentException("duration not valid for accompanying chords: " + duration.getAbcRepresentation());
				}
				
				System.out.println("terminal sequence is: " + Arrays.asList(terminalSentence).toString());
				Phrase phrase = parser.convertSentenceToPhrase(Arrays.asList(terminalSentence), accompScale, carriedOctaveShift);
				carriedOctaveShift = extractLastOctaveShiftInPhrase(phrase);
				bar.addToBar(phrase);
			}
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
