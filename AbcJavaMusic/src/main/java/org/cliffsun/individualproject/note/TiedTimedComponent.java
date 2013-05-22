package org.cliffsun.individualproject.note;

import java.util.List;

import org.cliffsun.individualproject.duration.Duration;

public class TiedTimedComponent extends TimedComponent {

	public TiedTimedComponent(Component component) {
		super(component);
	}
	
	public TiedTimedComponent(Component component, Duration duration) {
		super(component, duration);
	}
	
	@Override
	public String getAbcRepresentation(List<MainNoteComponent> accumAccentedNotes) {
		return super.getAbcRepresentation(accumAccentedNotes) + "-";
	}

	public static TiedTimedComponent tiedTimedComponent(Component component, Duration duration){
		return new TiedTimedComponent(component, duration);
	}
}
