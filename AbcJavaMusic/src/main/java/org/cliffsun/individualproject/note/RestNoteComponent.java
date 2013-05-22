package org.cliffsun.individualproject.note;

import java.util.List;

public class RestNoteComponent extends MainNoteComponent{

	public RestNoteComponent() {
		super(BasicNote.rest());
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String getAbcRepresentation(List<MainNoteComponent> accumAccentedNotes) {
		return "z";
	}

}
