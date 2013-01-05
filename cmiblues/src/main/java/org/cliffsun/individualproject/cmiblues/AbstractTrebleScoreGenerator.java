package org.cliffsun.individualproject.cmiblues;

import org.cliffsun.individualproject.duration.Duration;

public abstract class AbstractTrebleScoreGenerator implements
		TrebleScoreGenerator {

	@Override
	public int randomiseInterval() {
		double rand = Math.random();
		if(rand >= 0 && rand < 0.6){
			return 1;
		}
		else if (rand >= 0.6 && rand < 0.9){
			return 2;
		}
		else{
			return 3;
		}
	}

	@Override
	public double randomiseDuration(){
		double rand = Math.random();
		if(rand >= 0 && rand < 0.5){
			return 0.5;
		}
		else if (rand >= 0.5 && rand < 0.85){
			return 1.0;
		}
		else{
			return 0.25;
		}
	}
	
	public Duration convertDoubleDurationToDuration(double duration){
		if(duration == 1.0){
			return Duration.quarter;
		}
		else if(duration == 0.75){
			return Duration.dottedEigth;
		}
		else if(duration == 0.5){
			return Duration.eigth;
		}
		else if(duration == 0.25){
			return Duration.sixteenth;
		}
		throw new IllegalArgumentException("duration is not of standard length");
	}

}
