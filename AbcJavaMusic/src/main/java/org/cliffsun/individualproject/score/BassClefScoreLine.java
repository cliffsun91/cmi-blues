package org.cliffsun.individualproject.score;

import java.util.ArrayList;

import org.cliffsun.individualproject.bar.Bar;


public class BassClefScoreLine extends ScoreLine{
	
	public BassClefScoreLine() {
		super();
	}
	
	public BassClefScoreLine(ArrayList<Bar> barList) {
		super(barList);
	}
	
	@Override
	public String getScoreLineHeader() {
		return "[V:2] ";
	}
	
	public static BassClefScoreLine emptyScore(){
		return new BassClefScoreLine();
	}

}
