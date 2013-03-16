package org.cliffsun.individualproject.grammar.toneclass;


import java.util.Arrays;
import java.util.List;

import org.cliffsun.individualproject.keys.Scale;
import org.cliffsun.individualproject.note.ChromaticNoteGenerator;
import org.cliffsun.individualproject.note.MainNoteComponent;

public class ApproachTone implements Tone{

	private Scale scale;
	
	public ApproachTone(Scale scale) {
		this.scale = scale;
	}

	public List<MainNoteComponent> getApproachNotes(MainNoteComponent approachedNote) {
		if(approachedNote == null){
			throw new IllegalArgumentException("argument is null, previousNote should be a BasicNoteWithAccidental");
		}
		ChromaticNoteGenerator generator = new ChromaticNoteGenerator();
		MainNoteComponent oneUp = generator.getOneChromaticNoteUp(approachedNote);
		MainNoteComponent oneDown = generator.getOneChromaticNoteUp(approachedNote);
		
		return Arrays.asList(oneUp, oneDown);
	}

}
