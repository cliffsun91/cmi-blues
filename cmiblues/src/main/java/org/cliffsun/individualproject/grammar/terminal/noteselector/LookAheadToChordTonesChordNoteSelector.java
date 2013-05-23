package org.cliffsun.individualproject.grammar.terminal.noteselector;

import java.util.Collections;
import java.util.List;

import org.cliffsun.individualproject.grammar.toneclass.ChordTone;
import org.cliffsun.individualproject.lookaheadtochordtoneshelper.LookAheadToChordTonesOctaveSelector;
import org.cliffsun.individualproject.note.BasicNote;
import org.cliffsun.individualproject.note.MainNoteComponent;

public class LookAheadToChordTonesChordNoteSelector {

	LookAheadToChordTonesOctaveSelector octaveSelector;
	
	public LookAheadToChordTonesChordNoteSelector() {
		octaveSelector = new LookAheadToChordTonesOctaveSelector();
	}
	
	public MainNoteComponent convertChordToneToNote(ChordTone tone, int index, MainNoteComponent[] finishedChordToneComponents, int carriedOctaveShift){
		List<BasicNote> basicNotes = tone.getSuitableNoteList();
		//lets randomly pick our chord tones for now, perhaps in the future we may select in a certain way
		Collections.shuffle(basicNotes);
		int octave = octaveSelector.getNewOctaveForNoteIndex(index, finishedChordToneComponents, carriedOctaveShift);
		return new MainNoteComponent(basicNotes.get(0), octave);
	}
}
