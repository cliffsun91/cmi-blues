package org.cliffsun.individualproject.note;

public class TiedTimedComponent extends TimedComponent {

	public TiedTimedComponent(Component component) {
		super(component);
	}
	
	public TiedTimedComponent(Component component, double duration) {
		super(component, duration);
	}
	
	@Override
	public String getAbcRepresentation() {
		return super.getAbcRepresentation() + "-";
	}

	public static TiedTimedComponent tiedTimedComponent(Component component, double duration){
		return new TiedTimedComponent(component, duration);
	}
}
