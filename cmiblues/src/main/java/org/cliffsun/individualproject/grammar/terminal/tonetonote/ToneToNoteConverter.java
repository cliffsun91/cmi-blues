package org.cliffsun.individualproject.grammar.terminal.tonetonote;

import java.util.List;

import org.cliffsun.individualproject.duration.Duration;
import org.cliffsun.individualproject.grammar.toneclass.Tone;
import org.cliffsun.individualproject.phrase.Phrase;

public interface ToneToNoteConverter {

	public Phrase generatePhrase(List<Tone> toneList,
			List<Duration> durationList) throws Exception;

}