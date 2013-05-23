package org.cliffsun.individualproject.grammar.toneclass;

import java.util.Arrays;
import java.util.List;

import org.cliffsun.individualproject.keys.Scale;

public class ColourTone extends AbstractMultipleNotesTone{

	private Scale scale;
	public static Integer[] intervals = {2,5,6};
	
	public ColourTone(Scale scale) {
		this.scale = scale;
	}

	@Override
	public List<Integer> getIntervals() {
		return Arrays.asList(intervals);
	}

	@Override
	public Scale getScale() {
		return scale;
	}

}
