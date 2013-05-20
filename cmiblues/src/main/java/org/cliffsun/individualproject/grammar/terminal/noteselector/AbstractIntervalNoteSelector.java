package org.cliffsun.individualproject.grammar.terminal.noteselector;

import java.io.FileNotFoundException;
import java.io.IOException;

public abstract class AbstractIntervalNoteSelector extends AbstractNoteSelector{

	public AbstractIntervalNoteSelector() throws FileNotFoundException,	IOException {
		super();
	}
	
	public int getFinalIntervalLimit(){
		int intervalLimit = getIntegerPropertyForAttribute("intervalLimit");
		double intervalLimitProb = getDoublePropertyForAttribute("intervalLimitProb");
		int maxIntervalLimit = getIntegerPropertyForAttribute("maxIntervalLimit");
		
		double rand = Math.random();
		return rand < intervalLimitProb ? intervalLimit : maxIntervalLimit;
	}

}
