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

public class SimpleTwelveBarBluesAccompaniment implements BassAccompaniment {

	Chord chordI;
	Chord chordIV;
	Chord chordV;
	final int noOfBars = 12;
	
	public SimpleTwelveBarBluesAccompaniment(Chord chordI, Chord chordIV, Chord chordV) {
		this.chordI = chordI;
		this.chordIV = chordIV;
		this.chordV = chordV;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Pair<Chord, Duration>> getForm(){
		Pair<Chord, Duration> pairI = Pair.compPair(chordI, Duration.whole);
		Pair<Chord, Duration> pairIV = Pair.compPair(chordIV, Duration.whole);
		Pair<Chord, Duration> pairV = Pair.compPair(chordV, Duration.whole);
		return Arrays.asList(pairI, pairI, pairI, pairI,
						     pairIV, pairIV, pairI, pairI,
						     pairV, pairIV, pairI, pairI);
	}
	
	@Override
	public BassClefScoreLine getScoreLine() throws Exception {
		BassClefScoreLine bassScore = new BassClefScoreLine();
		List<Pair<Chord, Duration>> form = getForm();
		
		for (int i = 0 ; i < noOfBars; i++){
			Bar bar = new Bar();
			Phrase phrase = new StandardTimedComponentPhrase();
			Pair<Chord, Duration> pair = form.get(i);
			ChordComponent chord = pair.getLeft().getChord();
			phrase.addToPhrase(new TimedComponent(chord, Duration.half));
			phrase.addToPhrase(timedComponent(mainNote(BasicNote.rest()), Duration.half));
			
//			TimedComponent timedComponent;
//			if ((i < 4) || (i >= 6 && i < 8) || (i >= 10)){
//				timedComponent = new TimedComponent(chordScaleI.getChordBassAccompaniment(), Duration.half);
//			}
//			else if ((i >= 4 && i < 6) || i == 9){
//			    timedComponent = new TimedComponent(chordScaleIV.getChordBassAccompaniment(), Duration.half);
//			}
//			else {
//			    timedComponent = new TimedComponent(chordScaleV.getChordBassAccompaniment(), Duration.half);
//			}
//			phrase.addToPhrase(timedComponent);
//			phrase.addToPhrase(new TimedComponent(new MainNoteComponent(BasicNote.rest()), Duration.half));
			
			bar.addToBar(phrase);
			bassScore.addBarToScoreLine(bar);
		}
		return bassScore;
	}

}
