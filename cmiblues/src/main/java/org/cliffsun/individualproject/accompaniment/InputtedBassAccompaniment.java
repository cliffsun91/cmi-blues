package org.cliffsun.individualproject.accompaniment;

import static org.cliffsun.individualproject.note.MainNoteComponent.mainNote;
import static org.cliffsun.individualproject.note.TimedComponent.timedComponent;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math3.fraction.Fraction;
import org.cliffsun.individualproject.bar.Bar;
import org.cliffsun.individualproject.chord.Chord;
import org.cliffsun.individualproject.chord.ChordEnum;
import org.cliffsun.individualproject.duration.Duration;
import org.cliffsun.individualproject.note.BasicNote;
import org.cliffsun.individualproject.note.ChordComponent;
import org.cliffsun.individualproject.note.TimedComponent;
import org.cliffsun.individualproject.phrase.Phrase;
import org.cliffsun.individualproject.phrase.StandardTimedComponentPhrase;
import org.cliffsun.individualproject.score.BassClefScoreLine;
import org.cliffsun.individualproject.utils.Pair;


public class InputtedBassAccompaniment implements BassAccompaniment{

	private List<List<Chord>> progressionList;
	private int noOfBars;
	
	public InputtedBassAccompaniment(List<List<String>> progressionLine) {
		noOfBars = progressionLine.size();
		progressionList = new ArrayList<List<Chord>>();
		for(List<String> chordsInBar : progressionLine){
			List<Chord> barChords = new ArrayList<Chord>();
			for(String chordName : chordsInBar) {
				ChordEnum chordEnum = ChordEnum.getChordEnum(chordName);
				Chord chord = chordEnum.getChord();
				barChords.add(chord);
			}
			progressionList.add(barChords);
		}
	}

	@Override
	public BassClefScoreLine getScoreLine() throws Exception {
		BassClefScoreLine bassScore = new BassClefScoreLine();
		
		for (int i = 0 ; i < noOfBars; i++){
			Bar bar = new Bar();
			Phrase phrase = new StandardTimedComponentPhrase();
			List<Chord> barChords = progressionList.get(i);
			Chord chord1 = barChords.get(0);
			ChordComponent chordComponent1 = chord1.getChord();
			phrase.addToPhrase(new TimedComponent(chordComponent1, Duration.half));
			if (barChords.size() == 1){
				phrase.addToPhrase(timedComponent(mainNote(BasicNote.rest()), Duration.half));
			}
			else if (barChords.size() == 2){
				Chord chord2 = barChords.get(1);
				ChordComponent chordComponent2 = chord2.getChord();
				phrase.addToPhrase(new TimedComponent(chordComponent2, Duration.half));
			}
			bar.addToBar(phrase);
			
			bassScore.addBarToScoreLine(bar);
		}
		return bassScore;
	}

	@Override
	public List<List<Pair<Chord, Duration>>> getForm() {
		List<List<Pair<Chord, Duration>>> form = new ArrayList<List<Pair<Chord, Duration>>>();
		for (List<Chord> chordList : progressionList){
			double chordLength = 4.0/((double) chordList.size());
			List<Pair<Chord, Duration>> barChords = new ArrayList<Pair<Chord, Duration>>();
			for(Chord chord : chordList){
				Fraction duration = new Fraction(chordLength);
				Pair<Chord, Duration> pair = Pair.compPair(chord, Duration.getDurationEnum(duration)); //lets assume one chord per bar?
				barChords.add(pair);
			}
			form.add(barChords);
		}
		return form;
	}
	
	@Override
	public int getNumberOfBars(){
		return noOfBars;
	}
	
}
