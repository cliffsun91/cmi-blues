package org.cliffsun.individualproject.accompaniment;

import org.cliffsun.individualproject.bar.Bar;
import org.cliffsun.individualproject.duration.Duration;
import org.cliffsun.individualproject.keys.Scale;
import org.cliffsun.individualproject.note.BasicNote;
import org.cliffsun.individualproject.note.MainNoteComponent;
import org.cliffsun.individualproject.note.TimedComponent;
import org.cliffsun.individualproject.phrase.Phrase;
import org.cliffsun.individualproject.phrase.StandardTimedComponentPhrase;
import org.cliffsun.individualproject.score.BassClefScoreLine;

public class SimpleTwelveBarBluesAccompaniment implements BassAccompaniment {

	Scale chordScaleI;
	Scale chordScaleIV;
	Scale chordScaleV;
	final int noOfBars = 12;
	
	public SimpleTwelveBarBluesAccompaniment(Scale chordScaleI, Scale chordScaleIV, Scale chordScaleV) {
		this.chordScaleI = chordScaleI;
		this.chordScaleIV = chordScaleIV;
		this.chordScaleV = chordScaleV;
	}
	
	@Override
	public BassClefScoreLine getScoreLine() throws Exception {
		BassClefScoreLine bassScore = new BassClefScoreLine();
		for (int i = 0 ; i < noOfBars; i++){
			Bar bar = new Bar();
			Phrase phrase = new StandardTimedComponentPhrase();
			TimedComponent timedComponent;
			if ((i < 4) || (i >= 6 && i < 8) || (i >= 10)){
				timedComponent = new TimedComponent(chordScaleI.getChordBassAccompaniment(), Duration.half);
			}
			else if ((i >= 4 && i < 6) || i == 9){
			    timedComponent = new TimedComponent(chordScaleIV.getChordBassAccompaniment(), Duration.half);
			}
			else {
			    timedComponent = new TimedComponent(chordScaleV.getChordBassAccompaniment(), Duration.half);
			}
			phrase.addToPhrase(timedComponent);
			phrase.addToPhrase(new TimedComponent(new MainNoteComponent(BasicNote.rest()), Duration.half));
			bar.addToBar(phrase);
			bassScore.addBarToScoreLine(bar);
		}
		return bassScore;
	}

}
