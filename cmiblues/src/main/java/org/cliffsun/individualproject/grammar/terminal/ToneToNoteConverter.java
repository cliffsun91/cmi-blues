package org.cliffsun.individualproject.grammar.terminal;

import java.util.ArrayList;
import java.util.List;

import org.cliffsun.individualproject.duration.Duration;
import org.cliffsun.individualproject.grammar.toneclass.Tone;
import org.cliffsun.individualproject.note.BasicNote;
import org.cliffsun.individualproject.note.MainNoteComponent;
import org.cliffsun.individualproject.note.TimedComponent;
import org.cliffsun.individualproject.phrase.Phrase;
import org.cliffsun.individualproject.phrase.StandardTimedComponentPhrase;

public class ToneToNoteConverter {

	public List<Phrase> generateTimedComponentList(List<Tone> toneList, List<Duration> durationList) {
		if (toneList.size() != durationList.size()) {
			throw new IllegalArgumentException("tone list and duration list must be of the same size, " +
										   "tone list is: " + toneList.size() +
										   " and duration list is: " + durationList.size());
		} 
		
		MainNoteComponent prevNote = new MainNoteComponent(BasicNote.defaultNote());
		List<Phrase> phraseList = new ArrayList<Phrase>();
		
		
		for (int i = 0; i < toneList.size(); i++) {
			StandardTimedComponentPhrase phrase = new StandardTimedComponentPhrase();
			
			Tone tone = toneList.get(i);
			Duration duration = durationList.get(i);
			MainNoteComponent suitableNote = tone.getSuitableNote(prevNote);
			
			TimedComponent timedNote = new TimedComponent(suitableNote, duration);
			
			phrase.addtoComponentList(timedNote);
			phraseList.add(phrase);
			
			prevNote = suitableNote;
		}
		
		return phraseList;
	}

}
