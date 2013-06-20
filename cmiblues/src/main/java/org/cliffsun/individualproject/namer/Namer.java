package org.cliffsun.individualproject.namer;

import java.util.ArrayList;
import java.util.List;

public class Namer {

	public static List<Boolean> zerosAndOnes = new ArrayList<Boolean>(); 
	
	public static void clearList(){
		zerosAndOnes.clear();
	}
	
	public static void addToZerosAndOnesList(boolean bool){
		zerosAndOnes.add(bool);
	}
	
	public static void addMultipleToZerosAndOnesList(boolean ... bools){
		for(boolean val: bools){
			zerosAndOnes.add(val);
		}
	}
	
	public static long getBinaryToNumberTitle(){
		long result = 0;
		String binaryRepr = "";
		System.out.println("zerosAndOnes size: " + zerosAndOnes.size());
		int count = 0;
		for(Boolean bool: zerosAndOnes){
			if(bool){
				binaryRepr += "1";
			}
			else{
				binaryRepr += "0";
			}
			count++;
			
			if(count >= 63){
				result = result + Long.parseLong(binaryRepr, 2);
				binaryRepr = "";
				count = 0;
			}
		}
		return Math.abs(result);
	}
	
	public static List<Boolean> getZerosAndOnesList(){
		return zerosAndOnes;
	}

	public static boolean isLessThan(double rand, double prob) {
		if(rand < prob){
			zerosAndOnes.add(true);
			return true;
		}
		else{
			zerosAndOnes.add(false);
			return false;
		}
	}
	
	public static boolean isMoreThan(double rand, double prob) {
		if(rand > prob){
			zerosAndOnes.add(true);
			return true;
		}
		else{
			zerosAndOnes.add(false);
			return false;
		}
	}
}
