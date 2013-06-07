package org.cliffsun.individualproject.lookaheadtochordtoneshelper;

import org.cliffsun.individualproject.note.MainNoteComponent;
import org.cliffsun.individualproject.utils.Utils;

public class LookAheadToChordTonesOctaveSelector {

	public int getNewOctaveForNoteIndex(int index, MainNoteComponent[] components, int carriedOctaveShift) {
		double sameOctaveProb = 0.9;
		double rand = Math.random();
		
		MainNoteComponent lastAvailableNote = null;
		for(int i = index; i >= 0; i--) {
			if (components[i] != null){
				lastAvailableNote = components[i];
				int octaveShift = readjustOctaveShift(lastAvailableNote.getOctaveShift());
				if (rand > sameOctaveProb){ //slight chance that we change the octave for the next note
					double rand2 = Math.random();
					if(octaveShift == 1){
						octaveShift += rand2 > 0.8 ? 1 : -1; //we'd rather it stay in octave 0 or 1, going to 2 is a bit too high
					}
					else if(octaveShift == 0){  //if its at 0, and we have to change the octave, we go to 1
						octaveShift++;
					}
					else if(octaveShift == 2 || octaveShift == 3){ //if octave is really high, at 2 or 3, then lower it if we have to change it
						octaveShift--;
					}
				}
				return octaveShift;
			}
		}
		//if the list of finished notes is empty most likely use the carriedOctaveShift (from previous phrase)
		//otherwise we choose between octave 2 or 1 for some variety
		if (rand > sameOctaveProb){
			return Math.random() > 0.85 ? 2 : 1;
		}
		return readjustOctaveShift(carriedOctaveShift);
	}
	
	public int readjustOctaveShift(int octaveShift){
		if(octaveShift < Utils.minTrebleOctave){
			return Utils.minTrebleOctave;
		}
		else if(octaveShift > Utils.maxTrebleOctave){
			return Utils.maxTrebleOctave;
		}
		return octaveShift;
	}
}
