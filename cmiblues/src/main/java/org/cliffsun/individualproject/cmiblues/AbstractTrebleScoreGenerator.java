package org.cliffsun.individualproject.cmiblues;

public abstract class AbstractTrebleScoreGenerator implements
		TrebleScoreGenerator {

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

}
