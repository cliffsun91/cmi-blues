package org.cliffsun.individualproject.grammar.toneclass;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.cliffsun.individualproject.keys.Scale;
import org.cliffsun.individualproject.note.ChromaticNoteGenerator;
import org.cliffsun.individualproject.note.BasicNote;
import org.cliffsun.individualproject.note.MainNoteComponent;

public class ApproachTone implements Tone{

	private Scale scale;
	
	public ApproachTone(Scale scale) {
		this.scale = scale;
	}

	@Override
	public MainNoteComponent getSuitableNote(MainNoteComponent previousNote) {
		if(previousNote == null){
			throw new IllegalArgumentException("argument is null, previousNote should be a BasicNoteWithAccidental");
		}
		ChromaticNoteGenerator generator = new ChromaticNoteGenerator();
		MainNoteComponent oneUp = generator.getOneChromaticNoteUp(previousNote);
		MainNoteComponent oneDown = generator.getOneChromaticNoteUp(previousNote);
		
		List<MainNoteComponent> oneUpOrDown = Arrays.asList(oneUp, oneDown);
		Collections.shuffle(oneUpOrDown);
		return oneUpOrDown.get(0);
	}

}
