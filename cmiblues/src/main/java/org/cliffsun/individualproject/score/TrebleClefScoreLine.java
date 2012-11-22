package org.cliffsun.individualproject.score;

import java.util.ArrayList;


public class TrebleClefScoreLine extends ScoreLine{

	public TrebleClefScoreLine(){
		super();
	}
	
	public TrebleClefScoreLine(ArrayList<Bar> barList) {
		super(barList);
	}
	
	@Override
	public String getScoreLineHeader() {
		return "[V:1]";
	}

}
