package org.cliffsun.individualproject.grammar.terminal.tonetonote;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

import org.cliffsun.individualproject.duration.Duration;
import org.cliffsun.individualproject.grammar.terminal.noteselector.LookAheadToChordTonesNoteSelector;
import org.cliffsun.individualproject.grammar.toneclass.ApproachTone;
import org.cliffsun.individualproject.grammar.toneclass.ChordTone;
import org.cliffsun.individualproject.grammar.toneclass.ColourTone;
import org.cliffsun.individualproject.grammar.toneclass.RestTone;
import org.cliffsun.individualproject.grammar.toneclass.ScaleTone;
import org.cliffsun.individualproject.grammar.toneclass.Tone;
import org.cliffsun.individualproject.note.BasicNote;
import org.cliffsun.individualproject.note.MainNoteComponent;
import org.cliffsun.individualproject.note.TimedComponent;
import org.cliffsun.individualproject.phrase.Phrase;
import org.cliffsun.individualproject.phrase.StandardTimedComponentPhrase;

public class LookAheadToChordTonesToneToNoteConverter implements ToneToNoteConverter{
	
	LookAheadToChordTonesNoteSelector selector;
	
	public LookAheadToChordTonesToneToNoteConverter() throws FileNotFoundException, IOException {
		selector = new LookAheadToChordTonesNoteSelector();
	}
	
	@Override
	public Phrase generatePhrase(List<Tone> toneList, List<Duration> durationList) throws Exception {
		int noOfTones = toneList.size();
		TimedComponent[] finishedChordToneComponents = new TimedComponent[noOfTones];
		TimedComponent[] finishedOtherComponents = new TimedComponent[noOfTones];
		
		//do all the ChordTones first and the RestTones as they are always the same
		for (int i = 0; i < noOfTones; i++) {
			Tone tone = toneList.get(i);
			if (tone instanceof ChordTone){
				ChordTone chordTone = (ChordTone) tone;
				List<BasicNote> basicNotes = chordTone.getSuitableNoteList();
				//lets randomly pick our chord tones for now, perhaps in the future we may select in a certain way
				Collections.shuffle(basicNotes);
				int octave = getNewOctaveForNoteIndex(i, finishedChordToneComponents);
				MainNoteComponent mainNote = new MainNoteComponent(basicNotes.get(0), octave);
				finishedChordToneComponents[i] = new TimedComponent(mainNote, durationList.get(i));
			}
		}
		
		//now do the other tones (except approach tones which we will do last) based on the chord tones
		for (int i = 0; i < noOfTones; i++) {
			Tone tone = toneList.get(i);
			//tone, i, components can be parameters to help in finding the appropriate note
			//TODO complete note selection for colour tones and scale tones
			if (tone instanceof ColourTone) {
				ColourTone colorTone = (ColourTone) tone;
			}
			else if (tone instanceof ScaleTone) {
				ScaleTone scaleTone = (ScaleTone) tone;
			}
			else if (tone instanceof RestTone){
				RestTone restTone = (RestTone) tone;
				finishedOtherComponents[i] = new TimedComponent(restTone.getRestNote(), durationList.get(i));
			}
		}
		
		Phrase phrase = new StandardTimedComponentPhrase();
		
		for (int i = 0; i < noOfTones; i++){
			if (finishedOtherComponents[i] != null){
				phrase.addToPhrase(finishedOtherComponents[i]);
			}
			else if (finishedChordToneComponents[i] != null){
				phrase.addToPhrase(finishedChordToneComponents[i]);
			}
			else{
				throw new Exception("Note missing: for index: " + i);
			}
		}
		
		return phrase;
	}

	private int getNewOctaveForNoteIndex(int index, TimedComponent[] components) {
		double sameOctaveProb = 0.9;
		double rand = Math.random();
		
		TimedComponent lastAvailableNote = null;
		for(int i = index; i >= 0; i--) {
			if (components[i] != null){
				lastAvailableNote = components[i];
				MainNoteComponent mainNote = (MainNoteComponent) lastAvailableNote.getComponent();
				int octaveShift = mainNote.getOctaveShift();
				if (rand > sameOctaveProb){
					double rand2 = Math.random();
					if(octaveShift == 1){
						octaveShift += rand2 > 0.5 ? 1 : -1;
					}
					else if(octaveShift == 0){
						octaveShift++;
					}
					else if(octaveShift == 2 || octaveShift == 3){
						octaveShift--;
					}
				}
				return octaveShift;
			}
		}
		//if the list of finished notes is empty then lets just 
		//use the random number to pick an octave (0 - 1 - 2 is the ranking of probabilites)
		if (rand > sameOctaveProb){
			return Math.random() > 0.7 ? 2 : 1;
		}
		return 0;
	}

}
