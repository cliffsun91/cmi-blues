package org.cliffsun.individualproject.grammar.terminal.tonetonote;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
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
import org.cliffsun.individualproject.note.ChromaticNoteGenerator;
import org.cliffsun.individualproject.note.MainNoteComponent;
import org.cliffsun.individualproject.note.TimedComponent;
import org.cliffsun.individualproject.phrase.Phrase;
import org.cliffsun.individualproject.phrase.StandardTimedComponentPhrase;

public class LookAheadToChordTonesToneToNoteConverter implements ToneToNoteConverter{
	
	LookAheadToChordTonesNoteSelector selector;
	HashMap<Integer, ArrayList<Integer>> chordToOtherTones;
	
	public LookAheadToChordTonesToneToNoteConverter() throws FileNotFoundException, IOException {
		selector = new LookAheadToChordTonesNoteSelector();
		chordToOtherTones = new HashMap<Integer, ArrayList<Integer>>();
	}
	
	@Override
	public Phrase generatePhrase(List<Tone> toneList, List<Duration> durationList) throws Exception {
		int noOfTones = toneList.size();
		MainNoteComponent[] finishedChordToneComponents = new MainNoteComponent[noOfTones];
		MainNoteComponent[] finishedRestComponents = new MainNoteComponent[noOfTones];
		
		//do all the ChordTones first
		for (int i = 0; i < noOfTones; i++) {
			Tone tone = toneList.get(i);
			if (tone instanceof ChordTone){
				ChordTone chordTone = (ChordTone) tone;
				List<BasicNote> basicNotes = chordTone.getSuitableNoteList();
				//lets randomly pick our chord tones for now, perhaps in the future we may select in a certain way
				Collections.shuffle(basicNotes);
				int octave = getNewOctaveForNoteIndex(i, finishedChordToneComponents);
				finishedChordToneComponents[i] = new MainNoteComponent(basicNotes.get(0), octave);
			}
		}
		
		//now do the other tones (except approach tones which we will do last) based on the chord tones
		for (int i = 0; i < noOfTones; i++) {
			Tone tone = toneList.get(i);
			//tone, i, components can be parameters to help in finding the appropriate note
			//TODO complete note selection for colour tones and scale tones
			//we should assign these two tones to particular chord tones, maybe have a map for chord tone -> list of other tones (colour, scale and approach)
			//this way we can create smaller phrases and perhaps use probabilistic methods to generate variations and repeats in a passage
			if (tone instanceof ColourTone || tone instanceof ScaleTone) {
				int chordIndex = getNextChordToneIndexForOtherTone(i, finishedChordToneComponents);
				addToChordToOtherTonesMap(chordIndex, i);
				//now we need to use the mapping and the finishedChordToneComponents array and the toneList to extract the actual objects
				//then we convert the other tones to appropriate notes which fit around the already generated chord tone
				//chordToOtherTones, toneList, finishedChordToneComponents are needed
			}
			else if (tone instanceof RestTone){
				RestTone restTone = (RestTone) tone;
				finishedRestComponents[i] = restTone.getRestNote();
			}
		}
		
		//chordToOtherTones mapping has been filled out and now we can call the selector to select colour and scale components
		
		MainNoteComponent[] finishedCombinedToneComponents = selector.convertColourAndScaleTonesToNotes(chordToOtherTones, 
																						 		  finishedChordToneComponents, 
																						 		  toneList);
		
		//combine the finishedCombinedToneComponents and the finishedRestComponents together
		
		MainNoteComponent[] partiallyCompletedNotes = new MainNoteComponent[noOfTones];
		
		for (int i = 0; i < noOfTones; i++){
			if (finishedRestComponents[i] != null){
				if(finishedCombinedToneComponents[i] != null){
					throw new IllegalArgumentException("both finished tone arrays have something in the same index, this should " +
													   "not be possible. Please check array indexes are being used properly");
				}
				partiallyCompletedNotes[i] = finishedRestComponents[i];
			}
			else if (finishedCombinedToneComponents[i] != null){
				partiallyCompletedNotes[i] = finishedCombinedToneComponents[i];
			}
			//other ones should be approach tones.
		}
		
		printPartiallyCompletedArray(toneList, partiallyCompletedNotes);
		
		//convert the rest of the tones (approach tones should be remaining only) and get a list of fully completed notes
		MainNoteComponent[] fullyCompletedNotes = selector.convertApproachTonesToNotes(partiallyCompletedNotes, toneList);
		
		//check if the whole array has been filled, if there are any nulls then there is a problem
		checkPartiallyCompletedNotesArrayIsFull(fullyCompletedNotes);
		
		//combine main notes and durations to create timedcomponents
		List<TimedComponent> finalTimedComponents = new ArrayList<TimedComponent>();
		for (int i = 0; i < fullyCompletedNotes.length; i++) {
			MainNoteComponent mainNote = fullyCompletedNotes[i];
			Duration duration = durationList.get(i);
			finalTimedComponents.add(TimedComponent.timedComponent(mainNote, duration));
		}
		
		Phrase phrase = new StandardTimedComponentPhrase();
		for(TimedComponent component: finalTimedComponents) {
			phrase.addToPhrase(component);
		}
		
		return phrase;
	}

	private void printPartiallyCompletedArray(List<Tone> toneList,
			MainNoteComponent[] partiallyCompletedNotes) {
		System.out.print("Partially Completed Notes (approach tones not converted yet): ");
		for (int i = 0; i < partiallyCompletedNotes.length; i++) {
			if (partiallyCompletedNotes[i] == null) {
				if (toneList.get(i) instanceof ApproachTone) {
					System.out.print("AppTone, ");
				}
				else {
					System.out.print("ERR, ");
				}
			}
			else {
				System.out.print(partiallyCompletedNotes[i].getAbcRepresentation() + ", ");
			}
		} 
		System.out.println();
	}

	private void checkPartiallyCompletedNotesArrayIsFull(MainNoteComponent[] partiallyCompletedNotes) {
		for (int i = 0; i < partiallyCompletedNotes.length; i++) {
			if (partiallyCompletedNotes[i] == null){
				throw new ArrayStoreException("Array is not full! Element is null at index: " + i);
			}
		}
	}

	private void addToChordToOtherTonesMap(int chordIndex, int i) {
		if (chordToOtherTones.containsKey(chordIndex)) {
			ArrayList<Integer> otherTonesList = chordToOtherTones.get(chordIndex);
			otherTonesList.add(i);
		}
		else{
			ArrayList<Integer> otherTonesList = new ArrayList<Integer>();
			otherTonesList.add(i);
			chordToOtherTones.put(chordIndex, otherTonesList);
		}
	}

	private int getNewOctaveForNoteIndex(int index, MainNoteComponent[] components) {
		double sameOctaveProb = 0.9;
		double rand = Math.random();
		
		MainNoteComponent lastAvailableNote = null;
		for(int i = index; i >= 0; i--) {
			if (components[i] != null){
				lastAvailableNote = components[i];
				int octaveShift = lastAvailableNote.getOctaveShift();
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
	
	public int getNextChordToneIndexForOtherTone(int index, MainNoteComponent[] chordNoteComponents) {
		for (int i = index; i < chordNoteComponents.length; i++) {
			if (chordNoteComponents[i] != null) {
				return i;
			}
		}
		//if we get to the end then it means we don't have an chord tones after, we can instead associate it to the one before.
		return findLastChordToneIndex(chordNoteComponents);
	}
	
	public int findLastChordToneIndex(MainNoteComponent[] chordNoteComponents) {
		for (int i = chordNoteComponents.length-1; i >=0; i--) {
			if (chordNoteComponents[i] != null) {
				return i;
			}
		}
		return -1; //-1 means theres are no chord tone
	}

}
