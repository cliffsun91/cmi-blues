package org.cliffsun.individualproject.grammar.terminal.noteselector;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
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
				
				//System.out.println("chordMainNote is: " + chordMainNote.getAbcRepresentation(new ArrayList<MainNoteComponent>()));

				Tone tone = toneList.get(otherToneIndex);
				SurroundingOctaveNoteGenerator surroundingNoteGenerator = new SurroundingOctaveNoteGenerator();
				if (tone instanceof ColourTone || tone instanceof ScaleTone){
					AbstractMultipleNotesTone multipleNotesTone = (AbstractMultipleNotesTone) tone;
					List<BasicNote> basicNoteList = multipleNotesTone.getSuitableNoteList();
					
//					System.out.print("    suitable Basic Note list: ");
//					for(BasicNote note: basicNoteList){
//						System.out.print(note.getRepresentation() + ", ");
//					}
//					System.out.println();
					
					List<MainNoteComponent> suitableNotes = 
							surroundingNoteGenerator.generateSurroundingMainNotesForTrebleClef(basicNoteList, 
																							   chordMainNote.getOctaveShift());
					
//					System.out.print("    Surrounding Main Notes: ");
//					for(MainNoteComponent note: suitableNotes){
//						System.out.print(note.getAbcRepresentation(new ArrayList<MainNoteComponent>()) + ", ");
//					}
//					System.out.println();
					
					List<MainNoteComponent> suitableIntervalNotes = new ArrayList<MainNoteComponent>();
					int finalIntervalLimit = getFinalIntervalLimit();
					//System.out.println("    final interval limit is: " + finalIntervalLimit);
					//System.out.println("    Checking to see if the suitable notes fit within the interval range:");
					for (MainNoteComponent mainNote : suitableNotes) {
						int absInterval = mainNote.getAbsInterval(chordMainNote);
//						System.out.print("        interval between " + mainNote.getAbcRepresentation(new ArrayList<MainNoteComponent>()) +
//										 " (oct: " + mainNote.getOctaveShift() + ")" +
//										 " and " + chordMainNote.getAbcRepresentation(new ArrayList<MainNoteComponent>()) +
//										 " (oct: " + chordMainNote.getOctaveShift() + ")" +
//										 " is " + absInterval);
						if (absInterval < finalIntervalLimit){
							//System.out.print(" which is valid ");
							suitableIntervalNotes.add(mainNote);
						}
						else{
							//System.out.print(" which is NOT valid");
						}
						//System.out.println();
					}
					//should put this for block in a seperate class
					if(suitableIntervalNotes.isEmpty()){
						throw new IllegalArgumentException("suitableIntervalNotes should not be empty!");
					}
					//randomly pick one, really should have a function which has higher probability of picking a closer note
					int randIndex = RandomListIndexPicker.pickRandomIndex(suitableIntervalNotes);
					finishedChordColourAndScaleNotes[otherToneIndex] = suitableIntervalNotes.get(randIndex);
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
					//this may be a rest tone in fact, which causes errors as we can't get approach tones for rest notes
					int indexDec = 1;
					MainNoteComponent previousNote = fullyCompletedNotes[i-indexDec];
					while(previousNote.getBasicNote().equals(BasicNote.rest())){
						indexDec++;
						previousNote = fullyCompletedNotes[i-indexDec];
					}
					List<MainNoteComponent> approachNotes = generator.getChromaticUpAndDown(previousNote);
					int randIndex = RandomListIndexPicker.pickRandomIndex(approachNotes);
					approachNote = approachNotes.get(randIndex);
				}
				else if (i == 0) {
					//if the approach tone is at the beginning, then we can only use the next note
					MainNoteComponent nextNote = fullyCompletedNotes[i+1];
					List<MainNoteComponent> approachNotes = generator.getChromaticUpAndDown(nextNote);
					int randIndex = RandomListIndexPicker.pickRandomIndex(approachNotes);
					approachNote = approachNotes.get(randIndex);
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
				fullyCompletedNotes[i] = approachNote;
			}
		}
		return fullyCompletedNotes;
	}

}
