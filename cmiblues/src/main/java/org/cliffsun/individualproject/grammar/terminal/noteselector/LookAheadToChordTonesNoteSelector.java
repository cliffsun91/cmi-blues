package org.cliffsun.individualproject.grammar.terminal.noteselector;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.cliffsun.individualproject.grammar.toneclass.AbstractMultipleNotesTone;
import org.cliffsun.individualproject.grammar.toneclass.ApproachTone;
import org.cliffsun.individualproject.grammar.toneclass.ColourTone;
import org.cliffsun.individualproject.grammar.toneclass.ScaleTone;
import org.cliffsun.individualproject.grammar.toneclass.Tone;
import org.cliffsun.individualproject.note.BasicNote;
import org.cliffsun.individualproject.note.ChromaticNoteGenerator;
import org.cliffsun.individualproject.note.MainNoteComponent;
import org.cliffsun.individualproject.note.SurroundingOctaveNoteGenerator;

public class LookAheadToChordTonesNoteSelector extends AbstractIntervalNoteSelector{

	public LookAheadToChordTonesNoteSelector() throws FileNotFoundException, IOException {
		super();
	}
	
	public MainNoteComponent[] convertColourAndScaleTonesToNotes(HashMap<Integer, ArrayList<Integer>> chordToOtherTones, 
														 MainNoteComponent[] chordNotes, 
														 List<Tone> toneList) {
		
		MainNoteComponent[] finishedChordColourAndScaleNotes = chordNotes.clone();
		for (int chordToneIndex: chordToOtherTones.keySet()){
			MainNoteComponent chordMainNote;
			if (chordToneIndex == -1) {//not assigned to a chord tone as there isnt one in this bar
				//bit of a hack, maybe we should look at the last known chord note, including previous bars
				chordMainNote = new MainNoteComponent(toneList.get(0).getScale().getTonic(), 0);
			}
			else {
				chordMainNote = chordNotes[chordToneIndex];
			}
			ArrayList<Integer> otherToneIndexList = chordToOtherTones.get(chordToneIndex);
			for (int otherToneIndex: otherToneIndexList){
				Tone tone = toneList.get(otherToneIndex);
				SurroundingOctaveNoteGenerator surroundingNoteGenerator = new SurroundingOctaveNoteGenerator();
				if (tone instanceof ColourTone || tone instanceof ScaleTone){
					AbstractMultipleNotesTone multipleNotesTone = (AbstractMultipleNotesTone) tone;
					List<BasicNote> basicNoteList = multipleNotesTone.getSuitableNoteList();
					List<MainNoteComponent> suitableNotes = 
							surroundingNoteGenerator.generateSurroundingMainNotesForTrebleClef(basicNoteList, 
																							   chordMainNote.getOctaveShift());
					List<MainNoteComponent> suitableIntervalNotes = new ArrayList<MainNoteComponent>();
					int finalIntervalLimit = getFinalIntervalLimit();
					for (MainNoteComponent mainNote : suitableNotes) {
						if (mainNote.getAbsInterval(chordMainNote) < finalIntervalLimit){
							suitableIntervalNotes.add(mainNote);
						}
					}
					//randomly pick one, really should have a function which has higher probability of picking a closer note
					Collections.shuffle(suitableIntervalNotes);
					//should put this for block in a seperate class
					finishedChordColourAndScaleNotes[otherToneIndex] = suitableIntervalNotes.get(0);
				}
				else {
					throw new IllegalArgumentException("Only ColourTones and ScaleTones are allowed in " +
													   "this method, this tone is of instance: " + tone.getClass().getSimpleName());
				}
			}
		}
		return finishedChordColourAndScaleNotes;
	}
	
	
	public MainNoteComponent[] convertApproachTonesToNotes(MainNoteComponent[] partiallyCompletedNotes, List<Tone> toneList){
		//Now do approach tones
		ChromaticNoteGenerator generator = new ChromaticNoteGenerator();
		MainNoteComponent[] fullyCompletedNotes = partiallyCompletedNotes.clone();
		
		for (int i = 0; i < fullyCompletedNotes.length; i++) {
			Tone tone = toneList.get(i);
			if (tone instanceof ApproachTone) {
				MainNoteComponent approachNote;
				if (i == fullyCompletedNotes.length-1){ 
					//if the approach tone is at the end of a phrase then we use the previous note to work out the 
					//next approach tone
					MainNoteComponent previousNote = fullyCompletedNotes[i-1];
					List<MainNoteComponent> approachNotes = generator.getChromaticUpAndDown(previousNote);
					Collections.shuffle(approachNotes);
					approachNote = approachNotes.get(0);
				}
				else if (i == 0) {
					//if the approach tone is at the beginning, then we can only use the next note
					MainNoteComponent nextNote = fullyCompletedNotes[i+1];
					List<MainNoteComponent> approachNotes = generator.getChromaticUpAndDown(nextNote);
					Collections.shuffle(approachNotes);
					approachNote = approachNotes.get(0);
				}
				else {
					//else we look at the next note always, but we can use the previous note to decided where we want to approach from
					MainNoteComponent previousNote = fullyCompletedNotes[i-1];
					MainNoteComponent nextNote = fullyCompletedNotes[i+1];
					if (previousNote.isLowerThan(nextNote)){
						approachNote = generator.getOneChromaticNoteDown(nextNote);
					}
					else {
						approachNote = generator.getOneChromaticNoteUp(nextNote);
					}
				}
				//BUT, WHAT IF THE PREVIOUS NOTE IS ALSO AN APPROACH TONE? 
				//	Maybe we should only do the ones that are adjacent to previous tones first and then recurse 
				//	until we have no more tones to convert?
				//		Example: lets say we have: C, G, appTone, appTone, D
				//				 on first pass we should do the first appTone after G
				//				 on second pass we should do the second appTone based on the first one
				//				 Not very efficient, and really we shouldn't have appTones next to each other
				//				 could change generator so that we make sure if we have 2 appTone next to each other,
				//				 one of them needs to be changed to a colour tone
				fullyCompletedNotes[i] = approachNote;
			}
		}
		return fullyCompletedNotes;
	}

}
