package org.cliffsun.individualproject.note;

import org.apache.commons.math3.fraction.Fraction;
import org.cliffsun.individualproject.duration.Duration;

public class TimedComponent {

	private Component component;
	private Duration duration;
	
	public TimedComponent(Component component){
		this.component = component;
		this.duration = Duration.quarter;
	}
	
	public TimedComponent(Component component, Duration duration){
		this.component = component;
		this.duration = duration;
	}
	
	public Component getComponent(){
		return component;
	}
	
	public Fraction getDuration(){
		return duration.getActualDuration();
	}

	public String getAbcRepresentation() {
		String representation = component.getAbcRepresentation();
		representation += duration.getAbcRepresentation();
		return representation;
	}
	
	
	public static TimedComponent standardTimedComponent(Component component){
		return new TimedComponent(component);
	}
	
	public static TimedComponent timedComponent(Component component, Duration duration){
		return new TimedComponent(component, duration);
	}

}
