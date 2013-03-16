package org.cliffsun.individualproject.grammar.terminal.durationparser;

import org.apache.commons.math3.fraction.Fraction;
import org.cliffsun.individualproject.duration.Duration;

public class DurationParser {

	
	public Duration getDurationFromString(String durationString){
		double duration;
		if(durationString.length() == 1){
			duration = Integer.parseInt(durationString);
		}
		else{ //bigger than 1
			if (durationString.length() == 2 && durationString.substring(0,1).equals("/")){  //fraction in form of /Y
				duration = 1.0/Double.parseDouble(durationString.substring(1));
			}
			else if (durationString.length() == 3 && durationString.substring(1,2).equals("/")){  //fraction in form of X/Y
				double top = Double.parseDouble(durationString.substring(0,1));
				double bottom = Double.parseDouble(durationString.substring(2,3));
				duration = top/bottom;
			}
			else{
				throw new IllegalArgumentException("The duration string is not valid: " + durationString);
			}
		}
		
		Fraction durationFraction = new Fraction(duration);

		return Duration.getDurationEnum(durationFraction);
	}
}
