package org.cliffsun.individualproject.grammar.terminal.noteselector;

import java.util.List;

import org.cliffsun.individualproject.cmiblues.Namer;

public class RandomListIndexPicker {

	public static int pickRandomIndex(List<? extends Object> list){
		int size = list.size();
		double interval = 1.0/(double) size;
		double prevAcc = 0;
		double acc = 0;
		
		double rand = Math.random();
		for(int i = 0; i < size; i++){
			prevAcc = acc;
			acc += interval;
			if(rand >= prevAcc && rand < acc){
				Namer.addToZerosAndOnesList(true);
				return i;
			}
			else{
				Namer.addToZerosAndOnesList(false);
			}
		}
		return 0;
	}
	
}


