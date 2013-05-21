package org.cliffsun.individualproject.grammar.terminal.tonetonote;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.cliffsun.individualproject.duration.Duration;
import org.cliffsun.individualproject.grammar.terminal.noteselector.LookAheadToChordTonesChordNoteSelector;
import org.cliffsun.individualproject.grammar.terminal.noteselector.LookAheadToChordTonesNoteSelector;
import org.cliffsun.individualproject.grammar.toneclass.ApproachTone;
import org.cliffsun.individualproject.grammar.toneclass.ChordTone;
import org.cliffsun.individualproject.grammar.toneclass.ColourTone;
import org.cliffsun.individualproject.grammar.toneclass.RestTone;
import org.cliffsun.individualproject.grammar.toneclass.ScaleTone;
import org.cliffsun.individualproject.grammar.toneclass.Tone;
import org.cliffsun.individualproject.lookaheadtochordtones.ChordToneMapping;
import org.cliffsun.individualproject.lookaheadtochordtones.LookAheadToChordTonesOctaveSelector;
import org.cliffsun.individualproject.note.BasicNote;
import org.cliffsun.individualproject.note.ChromaticNoteGenerator;
import org.cliffsun.individualproject.note.MainNoteComponent;
import org.cliffsun.individualproject.note.TimedComponent;
import org.cliffsun.individualproject.phrase.Phrase;
import org.cliffsun.individualproject.phrase.StandardTimedComponentPhrase;

public class LookAheadToChordTonesToneToNoteConverter implements ToneToNoteConverter{
	
	LookAheadToChordTonesNoteSelector selector;
	LookAheadToChordTonesChordNoteSelector chordNoteSelector;
	ChordToneMapping chordToneMapper;
	
	public LookAheadToChordTonesToneToNoteConverter() throws FileNotFoundException, IOException {
		selector = new LookAheadToChordTonesNoteSelector();
		chordNoteSelector = new LookAheadToChordTonesChordNoteSelector();
		chordToneMapper = new ChordToneMapping();
	}
	
	@Override
	public Phrase generatePhrase(List<Tone> toneList, List<Duration> durationList) throws Exception {
		int noOfTones = toneList.size();
		MainNoteComponent[] finishedChordToneComponents = new MainNoteComponent[noOfTones];
		MainNoteComponent[] finishedRestComponents = new MainNoteComponent[noOfTones];
		
		//do all the ChordTones and RestTones first
		for (int i = 0; i < noOfTones; i++) {
			Tone tone = toneList.get(i);
			if (tone instanceof ChordTone){
				ChordTone chordTone = (ChordTone) tone;
				finishedChordToneComponents[i] = chordNoteSelector.convertChordToneToNote(chordTone, i, finishedChordToneComponents);
			}
			if (tone instanceof RestTone){
				RestTone restTone = (RestTone) tone;
				finishedRestComponents[i] = restTone.getRestNote();
			}
		}
		 
		HashMap<Integer, ArrayList<Integer>> chordToOtherTones = chordToneMapper.mapColourAndScaleTonesToChordTones(toneList, finishedChordToneComponents);		
		//chordToOtherTones mapping has been filled out and now we can call the selector to select colour and scale components
		
		MainNoteComponent[] finishedCombinedToneComponents = selector.convertColourAndScaleTonesToNotes(chordToOtherTones, 
																						 		  finishedChordToneComponents, 
																						 		  toneList);
		
		//combine the finishedCombinedToneComponents and the finishedRestComponents together
		
		MainNoteComponent[] partiallyCompletedNotes = combineChordColourScaleRestComponentsTogether(noOfTones, finishedRestComponents, finishedCombinedToneComponents);
		
		printPartiallyCompletedArray(toneList, partiallyCompletedNotes); //print is for testing
		
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

	private MainNoteComponent[] combineChordColourScaleRestComponentsTogether(
															int noOfTones, MainNoteComponent[] finishedRestComponents,
															MainNoteComponent[] finishedCombinedToneComponents) {
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
		return partiallyCompletedNotes;
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

}
