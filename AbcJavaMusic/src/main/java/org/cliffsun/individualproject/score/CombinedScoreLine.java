package org.cliffsun.individualproject.score;

import org.cliffsun.individualproject.exception.BarLengthException;

public class CombinedScoreLine {

	private TrebleClefScoreLine trebleScoreLine;
	private BassClefScoreLine bassScoreLine;
	
	public CombinedScoreLine(TrebleClefScoreLine trebleScoreLine, BassClefScoreLine bassScoreLine){
		this.trebleScoreLine = trebleScoreLine;
		this.bassScoreLine = bassScoreLine;
	}
	
	public TrebleClefScoreLine getTrebleScoreLine() {
		return trebleScoreLine;
	}
	
	public BassClefScoreLine getBassScoreLine() {
		return bassScoreLine;
	}
	
	public String getAbcRepresentation() throws BarLengthException {
		
		return trebleScoreLine.getAbcRepresentation() + "\n" +
			   bassScoreLine.getAbcRepresentation() + "\n";
	}
}
