package org.cliffsun.individualproject.lookaheadtochordtoneshelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.cliffsun.individualproject.grammar.toneclass.ColourTone;
import org.cliffsun.individualproject.grammar.toneclass.ScaleTone;
import org.cliffsun.individualproject.grammar.toneclass.Tone;
import org.cliffsun.individualproject.note.MainNoteComponent;

public class ChordToneMapping {

	public HashMap<Integer, ArrayList<Integer>> mapColourAndScaleTonesToChordTones(List<Tone> toneList, MainNoteComponent[] finishedChordToneComponents) {
			HashMap<Integer, ArrayList<Integer>> chordToOtherTones = new HashMap<Integer, ArrayList<Integer>>();
				//now do the other tones (except approach tones which we will do last) based on the chord tones
			for (int i = 0; i < toneList.size(); i++) {
				Tone tone = toneList.get(i);
				//note selection for colour tones and scale tones
				//these two tones are assigned to particular chord tones, i.e. a maping for chord tone -> list of other tones (colour and scale)
				//this way we can create smaller phrases and perhaps use probabilistic methods to generate variations and repeats in a passage
				if (tone instanceof ColourTone || tone instanceof ScaleTone) {
					int chordIndex = getNextChordToneIndexForOtherTone(i, finishedChordToneComponents);
					addToChordToOtherTonesMap(chordToOtherTones, chordIndex, i);
					//now we need to use the mapping and the finishedChordToneComponents array and the toneList to extract the actual objects
					//then we convert the other tones to appropriate notes which fit around the already generated chord tone
					//chordToOtherTones, toneList, finishedChordToneComponents are needed
				}
			}
			return chordToOtherTones;
	}
	
	private void addToChordToOtherTonesMap(HashMap<Integer, ArrayList<Integer>> chordToOtherTones, int chordIndex, int i) {
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
