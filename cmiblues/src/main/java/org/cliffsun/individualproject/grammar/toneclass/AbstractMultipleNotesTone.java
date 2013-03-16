package org.cliffsun.individualproject.grammar.toneclass;

import java.util.ArrayList;
import java.util.List;

import org.cliffsun.individualproject.keys.Scale;
import org.cliffsun.individualproject.note.BasicNote;

public abstract class AbstractMultipleNotesTone implements Tone {
	
	public List<BasicNote> getSuitableNoteList(){
		List<BasicNote> scaleList = getScale().getScaleAsList();
		List<Integer> intervalsList = getIntervals();
		
		List<BasicNote> suitableNotes = new ArrayList<BasicNote>(); 
		for(Integer i: intervalsList){
			suitableNotes.add(scaleList.get(i-1));
		}
		return suitableNotes;
	}
	
	public abstract List<Integer> getIntervals();
	
	public abstract Scale getScale();
}
