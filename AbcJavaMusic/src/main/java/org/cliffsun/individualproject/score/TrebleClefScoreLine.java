package org.cliffsun.individualproject.score;

import java.util.ArrayList;

import org.cliffsun.individualproject.bar.Bar;


public class TrebleClefScoreLine extends ScoreLine{

	public TrebleClefScoreLine(){
		super();
	}
	
	public TrebleClefScoreLine(ArrayList<Bar> barList) {
		super(barList);
	}
	
	@Override
	public String getScoreLineHeader() {
		return "[V:1] ";
	}
	
	public static TrebleClefScoreLine emptyScore(){
		return new TrebleClefScoreLine();
	}

}
