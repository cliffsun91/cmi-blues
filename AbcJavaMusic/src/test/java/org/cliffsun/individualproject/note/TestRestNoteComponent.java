package org.cliffsun.individualproject.note;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.cliffsun.individualproject.note.Component;
import org.cliffsun.individualproject.note.RestNoteComponent;
import org.junit.Test;

public class TestRestNoteComponent {

	@Test
	public void testAbcRepresentationForARestComponentReturnsCorrectly(){
		Component note = new RestNoteComponent();
		assertThat(note.getAbcRepresentation(null), equalTo("z"));
	}
}
