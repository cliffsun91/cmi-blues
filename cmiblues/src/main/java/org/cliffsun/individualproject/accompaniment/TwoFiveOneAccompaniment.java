package org.cliffsun.individualproject.accompaniment;

import static org.cliffsun.individualproject.note.MainNoteComponent.mainNote;
import static org.cliffsun.individualproject.note.TimedComponent.timedComponent;

import java.util.Arrays;
import java.util.List;

import org.cliffsun.individualproject.bar.Bar;
import org.cliffsun.individualproject.duration.Duration;
import org.cliffsun.individualproject.keys.Scale;
import org.cliffsun.individualproject.note.BasicNote;
import org.cliffsun.individualproject.note.ChordComponent;
import org.cliffsun.individualproject.note.TimedComponent;
import org.cliffsun.individualproject.phrase.Phrase;
import org.cliffsun.individualproject.phrase.StandardTimedComponentPhrase;
import org.cliffsun.individualproject.score.BassClefScoreLine;
import org.cliffsun.individualproject.utils.Pair;

public class TwoFiveOneAccompaniment implements BassAccompaniment {

	Scale chordScaleII;
	Scale chordScaleV;
	Scale chordScaleI;
	final int noOfBars = 12;
	
	public TwoFiveOneAccompaniment(Scale chordScaleII, Scale chordScaleV, Scale chordScaleI) {
		this.chordScaleII = chordScaleII;
		this.chordScaleV = chordScaleV;
		this.chordScaleI = chordScaleI;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Pair<Scale, Duration>> getForm(){
		Pair<Scale, Duration> pairII = Pair.compPair(chordScaleII, Duration.whole);
		Pair<Scale, Duration> pairV = Pair.compPair(chordScaleV, Duration.whole);
		Pair<Scale, Duration> pairI = Pair.compPair(chordScaleI, Duration.whole);
		return Arrays.asList(pairII, pairV, pairI, pairI,
						     pairII, pairV, pairI, pairI,
						     pairII, pairV, pairI, pairI);
	}
	
	@Override
	public BassClefScoreLine getScoreLine() throws Exception {
		BassClefScoreLine bassScore = new BassClefScoreLine();
		List<Pair<Scale, Duration>> form = getForm();
		
		for (int i = 0 ; i < noOfBars; i++){
			Bar bar = new Bar();
			Phrase phrase = new StandardTimedComponentPhrase();
			Pair<Scale, Duration> pair = form.get(i);
			ChordComponent chord = pair.getLeft().getChordBassAccompaniment();
			phrase.addToPhrase(new TimedComponent(chord, Duration.half));
			phrase.addToPhrase(timedComponent(mainNote(BasicNote.rest()), Duration.half));
			
			bar.addToBar(phrase);
			bassScore.addBarToScoreLine(bar);
		}
		return bassScore;
	}

}
