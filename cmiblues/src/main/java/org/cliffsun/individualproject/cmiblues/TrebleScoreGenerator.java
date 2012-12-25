package org.cliffsun.individualproject.cmiblues;

import org.cliffsun.individualproject.exception.BarLengthException;

public interface TrebleScoreGenerator {

	String generateScore() throws BarLengthException;

	int randomiseInterval();

	double randomiseDuration();
	
}