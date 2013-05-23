package org.cliffsun.individualproject.grammar.toneclass;

import java.util.Arrays;
import java.util.List;

import org.cliffsun.individualproject.keys.Scale;

public class ChordTone extends AbstractMultipleNotesTone{

	private Scale scale;
	public static Integer[] intervals = {1,3,7};
	
	public ChordTone(Scale scale) {
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
