package org.cliffsun.individualproject.note;

public class TimedComponent {

	private Component component;
	private double duration;
	
	public TimedComponent(Component component){
		this.component = component;
		this.duration = 1.0;
	}
	
	public TimedComponent(Component component, double duration){
		this.component = component;
		this.duration = duration;
	}
	
	public Component getComponent(){
		return component;
	}
	
	public double getDuration(){
		return duration;
	}

	public String getAbcRepresentation() {
		String representation = component.getAbcRepresentation();
		if(duration < 1){
			representation += "/" + (int)(1/duration);
		}
		else if(duration > 1){
			representation += (int) duration;
		}
		return representation;
	}
	
	
	public static TimedComponent timedComponent(Component component){
		return new TimedComponent(component);
	}
	
	public static TimedComponent timedComponent(Component component, int duration){
		return new TimedComponent(component, duration);
	}

}
