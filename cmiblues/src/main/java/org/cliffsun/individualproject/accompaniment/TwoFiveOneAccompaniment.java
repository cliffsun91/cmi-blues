package org.cliffsun.individualproject.accompaniment;

import static org.cliffsun.individualproject.note.MainNoteComponent.mainNote;
import static org.cliffsun.individualproject.note.TimedComponent.timedComponent;

import java.util.Arrays;
import java.util.List;

import org.cliffsun.individualproject.bar.Bar;
import org.cliffsun.individualproject.chord.Chord;
import org.cliffsun.individualproject.duration.Duration;
import org.cliffsun.individualproject.note.BasicNote;
import org.cliffsun.individualproject.note.ChordComponent;
import org.cliffsun.individualproject.note.TimedComponent;
import org.cliffsun.individualproject.phrase.Phrase;
import org.cliffsun.individualproject.phrase.StandardTimedComponentPhrase;
import org.cliffsun.individualproject.score.BassClefScoreLine;
import org.cliffsun.individualproject.utils.Pair;

public class TwoFiveOneAccompaniment implements BassAccompaniment {

	Chord chordII;
	Chord chordV;
	Chord chordI;
	final int noOfBars = 12;
	
	public TwoFiveOneAccompaniment(Chord chordII, Chord chordV, Chord chordI) {
		this.chordII = chordII;
		this.chordV = chordV;
		this.chordI = chordI;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<List<Pair<Chord, Duration>>> getForm(){
		Pair<Chord, Duration> pairII = Pair.compPair(chordII, Duration.whole);
		Pair<Chord, Duration> pairV = Pair.compPair(chordV, Duration.whole);
		Pair<Chord, Duration> pairI = Pair.compPair(chordI, Duration.whole);
		return Arrays.asList(Arrays.asList(pairII), Arrays.asList(pairV), Arrays.asList(pairI), Arrays.asList(pairI),
							 Arrays.asList(pairII), Arrays.asList(pairV), Arrays.asList(pairI), Arrays.asList(pairI),
							 Arrays.asList(pairII), Arrays.asList(pairV), Arrays.asList(pairI), Arrays.asList(pairI));
	}
	
	@Override
	public BassClefScoreLine getScoreLine() throws Exception {
		BassClefScoreLine bassScore = new BassClefScoreLine();
		List<List<Pair<Chord, Duration>>> form = getForm();
		
		for (int i = 0 ; i < noOfBars; i++){
			Bar bar = new Bar();
			Phrase phrase = new StandardTimedComponentPhrase();
			List<Pair<Chord, Duration>> barChords = form.get(i);
			Pair<Chord, Duration> pair = barChords.get(0);
			ChordComponent chord = pair.getLeft().getChord();
			phrase.addToPhrase(new TimedComponent(chord, Duration.half));
			phrase.addToPhrase(timedComponent(mainNote(BasicNote.rest()), Duration.half));
			
			bar.addToBar(phrase);
			bassScore.addBarToScoreLine(bar);
		}
		return bassScore;
	}
	
	@Override
	public int getNumberOfBars() {
		return noOfBars;
	}
}
