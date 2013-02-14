package org.cliffsun.individualproject.grammar.toneclass;

import java.util.ArrayList;
import java.util.List;

import org.cliffsun.individualproject.keys.Scale;
import org.cliffsun.individualproject.note.BasicNote;
import org.cliffsun.individualproject.note.MainNoteComponent;

public abstract class AbstractRandomChoiceTone implements Tone {
	
	public List<MainNoteComponent> getSuitableNoteList(int octaveShift){
		List<BasicNote> scaleList = getScale().getScaleAsList();
		List<Integer> intervalsList = getIntervals();
		
		List<MainNoteComponent> suitableNotes = new ArrayList<MainNoteComponent>(); 
		for(Integer i: intervalsList){
			suitableNotes.add(new MainNoteComponent(scaleList.get(i-1), octaveShift));
		}
		return suitableNotes;
	}
	
	public abstract List<Integer> getIntervals();
	
	public abstract Scale getScale();
}
