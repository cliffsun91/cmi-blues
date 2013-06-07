package org.cliffsun.individualproject.score;

import java.util.ArrayList;

import org.cliffsun.individualproject.bar.Bar;
import org.cliffsun.individualproject.exception.BarLengthException;

public abstract class ScoreLine {
	
	private ArrayList<Bar> barList;
	
	public ScoreLine(){
		barList = new ArrayList<Bar>();
	}
	
	public ScoreLine(ArrayList<Bar> barList){
		this.barList = barList;
	}
	
	public ArrayList<Bar> getBarList(){
		return barList;
	}
	
	public void addBarToScoreLine(Bar bar){
		barList.add(bar);
		//for our 12 bar blues each scoreLine will be 4 bars long
		//but this is not necessary nor should it be enforced in the program, 
		//it just makes the score easier to read
	}
	
	public int getNumberOfBarsInScoreLine(){
		return barList.size();
	}
	
	public String getAbcRepresentation() throws BarLengthException{
		String representation = getScoreLineHeader();
		for(Bar bar : barList){
			representation += bar.getAbcRepresentation() + "|";
		}
		representation += "|"; 
		//to make the last bar have a double bar line - signifies end of music
		return representation;
	}
	
	public abstract String getScoreLineHeader();

}
