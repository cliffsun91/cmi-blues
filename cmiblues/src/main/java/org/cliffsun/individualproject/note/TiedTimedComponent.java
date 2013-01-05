package org.cliffsun.individualproject.note;

import org.cliffsun.individualproject.duration.Duration;

public class TiedTimedComponent extends TimedComponent {

	public TiedTimedComponent(Component component) {
		super(component);
	}
	
	public TiedTimedComponent(Component component, Duration duration) {
		super(component, duration);
	}
	
	@Override
	public String getAbcRepresentation() {
		return super.getAbcRepresentation() + "-";
	}

	public static TiedTimedComponent tiedTimedComponent(Component component, Duration duration){
		return new TiedTimedComponent(component, duration);
	}
}
