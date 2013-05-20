package org.cliffsun.individualproject.grammar.terminal.toneparser;

import org.cliffsun.individualproject.grammar.toneclass.Tone;
import org.cliffsun.individualproject.keys.Scale;

public interface ToneTypeParser {

	public Tone parseToneAndReturnAppropriateType(String toneRepr, Scale scale, Tone prevTone);
}
