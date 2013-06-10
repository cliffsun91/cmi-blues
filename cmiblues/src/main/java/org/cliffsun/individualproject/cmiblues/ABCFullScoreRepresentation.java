package org.cliffsun.individualproject.cmiblues;

import org.cliffsun.individualproject.exception.BarLengthException;
import org.cliffsun.individualproject.score.CombinedScoreLine;

public class ABCFullScoreRepresentation {
	
	private CombinedScoreLine fullScore;
	private String title;

	public ABCFullScoreRepresentation(CombinedScoreLine fullScore) {
		this.fullScore = fullScore;
		this.title = "Unnamed";
	}
	
	public ABCFullScoreRepresentation(CombinedScoreLine fullScore, String title){
		this.fullScore = fullScore;
		this.title = title;
	}
	
	public String getHeaders(){
		return  "X: 1\n" +
			    "T: Jazz Improv: " + title + "\n" + 
				"C: CMIJazz-1.0\n" +
				"L: 1/4\n" + 
				"Q: 120\n" +
				"M: C\n" + 
				"K: C\n" +
				"V: 1\n" +
				"V: 2\n";
	}

	public CombinedScoreLine getCombinedScoreLine(){
		return fullScore;
	}
	
	public String getFullScoreAsString() throws BarLengthException {
		return getHeaders() + fullScore.getAbcRepresentation();
	}
}
