package org.cliffsun.individualproject.grammar.terminal.tonetonote;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.cliffsun.individualproject.duration.Duration;
import org.cliffsun.individualproject.grammar.terminal.noteselector.SimpleLookOneBehindNoteSelector;
import org.cliffsun.individualproject.grammar.toneclass.Tone;
import org.cliffsun.individualproject.note.BasicNote;
import org.cliffsun.individualproject.note.MainNoteComponent;
import org.cliffsun.individualproject.note.TimedComponent;
import org.cliffsun.individualproject.phrase.Phrase;
import org.cliffsun.individualproject.phrase.StandardTimedComponentPhrase;

public class SimpleLookOneBehindToneToNoteConverter implements ToneToNoteConverter {

	SimpleLookOneBehindNoteSelector selector;
	
	public SimpleLookOneBehindToneToNoteConverter() throws FileNotFoundException, IOException {
		super();
		selector = new SimpleLookOneBehindNoteSelector();
	}

	@Override
	public Phrase generatePhrase(List<Tone> toneList, List<Duration> durationList) throws Exception {
		if (toneList.size() != durationList.size()) {
			throw new IllegalArgumentException("tone list and duration list must be of the same size, " +
										   "tone list is: " + toneList.size() +
										   " and duration list is: " + durationList.size());
		} 
		
		MainNoteComponent prevNote = new MainNoteComponent(BasicNote.defaultNote());
		Phrase phrase = new StandardTimedComponentPhrase();
		
		//MainNoteComponent[] finishedNotes = new MainNoteComponent[toneList.size()];
		//in another tonetonoteconverter we can have an empty array of finished notes and then
		//iterate through the List of tones and give back an updated array list
		//can iterate until the list of tones is gone, but that means would need to pop it.
		
		for (int i = 0; i < toneList.size(); i++) {
			Tone tone = toneList.get(i);
			Duration duration = durationList.get(i);
			MainNoteComponent suitableNote = selector.getSuitableNoteForTone(tone, prevNote);
			
			TimedComponent timedNote = new TimedComponent(suitableNote, duration);
			phrase.addToPhrase(timedNote);
			prevNote = suitableNote;
		}
		
		return phrase;
	}

}
