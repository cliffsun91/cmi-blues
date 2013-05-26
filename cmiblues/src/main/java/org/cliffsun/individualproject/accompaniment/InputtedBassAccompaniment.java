package org.cliffsun.individualproject.accompaniment;

import static org.cliffsun.individualproject.note.MainNoteComponent.mainNote;
import static org.cliffsun.individualproject.note.TimedComponent.timedComponent;

import java.util.ArrayList;
import java.util.List;

import org.cliffsun.individualproject.bar.Bar;
import org.cliffsun.individualproject.chord.Chord;
import org.cliffsun.individualproject.chord.ChordEnum;
import org.cliffsun.individualproject.duration.Duration;
import org.cliffsun.individualproject.keys.Scale;
import org.cliffsun.individualproject.note.BasicNote;
import org.cliffsun.individualproject.note.ChordComponent;
import org.cliffsun.individualproject.note.TimedComponent;
import org.cliffsun.individualproject.phrase.Phrase;
import org.cliffsun.individualproject.phrase.StandardTimedComponentPhrase;
import org.cliffsun.individualproject.score.BassClefScoreLine;
import org.cliffsun.individualproject.utils.Pair;

public class InputtedBassAccompaniment implements BassAccompaniment{

	private List<Chord> chordList;
	private int noOfBars;
	
	public InputtedBassAccompaniment(List<String> chordNameList) {
		noOfBars = chordNameList.size(); //assume one chord per bar for now
		chordList = new ArrayList<Chord>();
		for(String chordName : chordNameList) {
			ChordEnum chordEnum = ChordEnum.getChordEnum(chordName);
			Chord chord = chordEnum.getChord();
			chordList.add(chord);
		}
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

	@Override
	public List<Pair<Scale, Duration>> getForm() {
		List<Pair<Scale, Duration>> form = new ArrayList<Pair<Scale, Duration>>();
		for (Chord chord : chordList){
			Pair<Scale, Duration> pair = Pair.compPair(chord.getAccompanyingScale(), Duration.whole); //lets assume one chord per bar?
			form.add(pair);
		}
		return form;
	}
	
}
